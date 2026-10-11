package com.example.demo.controller;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PerformanceReviewDto;
import com.example.demo.service.PerformanceReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class PerformanceReviewController {

    private final PerformanceReviewService service;

    public PerformanceReviewController(
            PerformanceReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PerformanceReviewDto> create(
            @RequestBody PerformanceReviewDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createOrUpdate(null, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReviewDto> update(
            @PathVariable Long id,
            @RequestBody PerformanceReviewDto dto) {
        return ResponseEntity.ok(
                service.createOrUpdate(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PerformanceReviewDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReviewDto> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}