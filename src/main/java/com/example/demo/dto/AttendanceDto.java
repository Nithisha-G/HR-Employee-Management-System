package com.example.demo.dto;


import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {

    private Long attendanceId;
    private Long employeeId;
    private LocalDate attendanceDate;
    private String status;
}