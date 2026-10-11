package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long employeeId;
    private String employeeName;
    private String email;
    private String designation;
    private Double salary;
    private Long departmentId;
}