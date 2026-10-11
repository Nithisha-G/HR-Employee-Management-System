package com.example.demo.dto;


import lombok.*;
import java.time.LocalDate;

import com.example.demo.enums.LeaveStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDto {

    private Long leaveId;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;
    
    private LeaveStatus lstatus;
}