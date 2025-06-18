package com.example.HealthCareSystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Review {
    private String id;
    private String patientId;
    private String patientName;
    private int rating;
    private String comment;
    private String date;
} 