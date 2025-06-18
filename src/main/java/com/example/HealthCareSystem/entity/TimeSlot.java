package com.example.HealthCareSystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeSlot {
    private String date;
    private String startTime;
    private String endTime;
    private boolean isAvailable;
} 