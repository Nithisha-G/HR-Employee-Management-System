package com.example.demo.dto;


import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReviewDto {

    private Long reviewId;
    private Long employeeId;
    private String comments;
    private Double rating;
    private LocalDate reviewDate;
}