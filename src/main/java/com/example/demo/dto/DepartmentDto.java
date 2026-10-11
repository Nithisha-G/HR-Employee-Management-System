package com.example.demo.dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long departmentId;
    private String departmentName;
    private String location;
}