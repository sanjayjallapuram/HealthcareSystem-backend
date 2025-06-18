package com.example.HealthCareSystem.service;

import com.example.HealthCareSystem.entity.*;
import com.example.HealthCareSystem.repository.AppointmentRepository;
import com.example.HealthCareSystem.repository.DoctorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void saveEntry(Doctors entry){
        doctorRepository.save(entry);
    }

    public List<Doctors> getAll(){
        return doctorRepository.findAll();
    }

    public Optional<Doctors> getDoctorById(ObjectId id){
        return doctorRepository.findById(id);
    }

    public DoctorProfileDTO getDoctorProfile(String doctorId) {
        try {
            System.out.println("Fetching doctor profile for ID: " + doctorId);
            Optional<Doctors> doctorOpt = doctorRepository.findById(new ObjectId(doctorId));
            
            if (!doctorOpt.isPresent()) {
                System.out.println("Doctor not found for ID: " + doctorId);
                return null;
            }

            Doctors doctor = doctorOpt.get();
            System.out.println("Found doctor: " + doctor.toString());
            
            DoctorProfileDTO profile = new DoctorProfileDTO();
            
            // Map basic information
            profile.setId(doctor.getId());
            profile.setUsername(doctor.getUsername());
            profile.setFullName(doctor.getFullName() != null ? doctor.getFullName() : doctor.getUsername());
            profile.setEmail(doctor.getEmail());
            profile.setSpecialty(doctor.getSpecialty());
            profile.setQualification(doctor.getQualification());
            profile.setYearsOfExperience(doctor.getYearsOfExperience());
            profile.setBio(doctor.getBio());
            // profile.setImageUrl(doctor.getImageUrl());
            profile.setLanguages(doctor.getLanguages() != null ? doctor.getLanguages() : new ArrayList<>());
            profile.setWorkingHours(doctor.getWorkingHours() != null ? doctor.getWorkingHours() : new ArrayList<>());
            profile.setAverageRating(doctor.getAverageRating());
            profile.setNumberOfReviews(doctor.getNumberOfReviews());
            profile.setAvailable(doctor.isAvailable());
            profile.setPhoneNumber(doctor.getPhoneNumber());
            profile.setAddress(doctor.getAddress());
            profile.setCertifications(doctor.getCertifications() != null ? doctor.getCertifications() : new ArrayList<>());
            
            // Set default values for null fields
            if (profile.getSpecialty() == null) profile.setSpecialty("General Medicine");
            if (profile.getQualification() == null) profile.setQualification("MBBS");
            if (profile.getBio() == null) profile.setBio("No bio available");
            if (profile.getImageUrl() == null) profile.setImageUrl("/default-avatar.png");
            
            System.out.println("Mapped profile: " + profile.toString());
            return profile;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ObjectId format: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error in getDoctorProfile: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // private List<TimeSlot> calculateAvailableTimeSlots(Doctors doctor) {
    //     List<TimeSlot> availableSlots = new ArrayList<>();
    //     LocalDate currentDate = LocalDate.now();
        
    //     // Get all appointments for next 7 days
    //     LocalDateTime weekFromNow = currentDate.plusDays(7).atTime(23, 59);
    //     List<Appointments> existingAppointments = appointmentRepository.findByDoctorIdAndStartTimeBetween(
    //         doctor.getId(),
    //         currentDate.atStartOfDay(),
    //         weekFromNow
    //     );

    //     // For each day
    //     for (int day = 0; day < 7; day++) {
    //         LocalDate date = currentDate.plusDays(day);
    //         String dayOfWeek = date.getDayOfWeek().toString();

    //         // Find working hours for this day
    //         Optional<WorkingHours> workingHoursOpt = doctor.getWorkingHours().stream()
    //             .filter(wh -> wh.getDayOfWeek().equals(dayOfWeek))
    //             .findFirst();

    //         if (workingHoursOpt.isPresent()) {
    //             WorkingHours wh = workingHoursOpt.get();
    //             LocalTime startTime = LocalTime.parse(wh.getStartTime());
    //             LocalTime endTime = LocalTime.parse(wh.getEndTime());

    //             // Create 30-minute slots
    //             while (startTime.plusMinutes(30).isBefore(endTime)) {
    //                 LocalDateTime slotStart = date.atTime(startTime);
    //                 LocalDateTime slotEnd = date.atTime(startTime.plusMinutes(30));

    //                 // Check if slot is available
    //                 boolean isAvailable = existingAppointments.stream()
    //                     .noneMatch(apt -> 
    //                         (apt.getStartTime().isBefore(slotEnd) && apt.getEndTime().isAfter(slotStart))
    //                     );

    //                 if (isAvailable && slotStart.isAfter(LocalDateTime.now())) {
    //                     TimeSlot slot = new TimeSlot();
    //                     slot.setDate(date.format(DateTimeFormatter.ISO_DATE));
    //                     slot.setStartTime(startTime.format(DateTimeFormatter.ISO_TIME));
    //                     slot.setEndTime(startTime.plusMinutes(30).format(DateTimeFormatter.ISO_TIME));
    //                     slot.setAvailable(true);
    //                     availableSlots.add(slot);
    //                 }

    //                 startTime = startTime.plusMinutes(30);
    //             }
    //         }
    //     }

    //     return availableSlots;
    // }

    // public ResponseEntity<?> addReview(String doctorId, Review review) {
    //     try {
    //         Optional<Doctors> doctorOpt = doctorRepository.findById(new ObjectId(doctorId));
    //         if (!doctorOpt.isPresent()) {
    //             return ResponseEntity.notFound().build();
    //         }

    //         Doctors doctor = doctorOpt.get();
            
    //         // Set review ID and date
    //         review.setId(UUID.randomUUID().toString());
    //         review.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

    //         // Add review to list
    //         if (doctor.getReviews() == null) {
    //             doctor.setReviews(new ArrayList<>());
    //         }
    //         doctor.getReviews().add(review);

    //         // Update average rating
    //         double totalRating = doctor.getReviews().stream()
    //             .mapToInt(Review::getRating)
    //             .sum();
    //         doctor.setAverageRating(totalRating / doctor.getReviews().size());
    //         doctor.setNumberOfReviews(doctor.getReviews().size());

    //         // Save updated doctor
    //         doctorRepository.save(doctor);

    //         return ResponseEntity.ok(review);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .body("Failed to add review: " + e.getMessage());
    //     }
    // }

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctors> getDoctorById(String id) {
        return doctorRepository.findById(new ObjectId(id));
    }

    public Optional<Doctors> getDoctorByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    public Doctors updateDoctorProfile(String doctorId, Doctors updatedDoctor) {
        Optional<Doctors> existingDoctor = doctorRepository.findById(new ObjectId(doctorId));
        if (existingDoctor.isPresent()) {
            Doctors doctor = existingDoctor.get();
            
            // Update fields but preserve sensitive information
            doctor.setFullName(updatedDoctor.getFullName());
            doctor.setSpecialty(updatedDoctor.getSpecialty());
            doctor.setQualification(updatedDoctor.getQualification());
            doctor.setYearsOfExperience(updatedDoctor.getYearsOfExperience());
            doctor.setBio(updatedDoctor.getBio());
            // doctor.setImageUrl(updatedDoctor.getImageUrl());
            doctor.setLanguages(updatedDoctor.getLanguages());
            doctor.setWorkingHours(updatedDoctor.getWorkingHours());
            doctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
            doctor.setAddress(updatedDoctor.getAddress());
            doctor.setCertifications(updatedDoctor.getCertifications());
            doctor.setAvailable(updatedDoctor.isAvailable());

            return doctorRepository.save(doctor);
        }
        return null;
    }

    // private boolean isValidAppointmentTime(LocalDate date, LocalTime time, String doctorId) {
    //     Optional<Doctors> doctor = doctorRepository.findById(new ObjectId(doctorId));
    //     if (!doctor.isPresent()) {
    //         return false;
    //     }
        
    //     String dayOfWeek = date.getDayOfWeek().toString();
    //     Optional<WorkingHours> workingHours = doctor.get().getWorkingHours().stream()
    //         .filter(wh -> wh.getDayOfWeek().equals(dayOfWeek))
    //         .findFirst();
        
    //     if (!workingHours.isPresent()) {
    //         return false;
    //     }
        
    //     LocalTime startTime = LocalTime.parse(workingHours.get().getStartTime());
    //     LocalTime endTime = LocalTime.parse(workingHours.get().getEndTime());
        
    //     return !time.isBefore(startTime) && !time.isAfter(endTime);
    // }
}
