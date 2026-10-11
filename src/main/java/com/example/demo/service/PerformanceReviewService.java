package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.dto.PerformanceReviewDto;
import com.example.demo.entity.PerformanceReview;
import com.example.demo.exceptionhandler.ResourceNotFoundException;
import com.example.demo.repository.PerformanceReviewRepository;

import java.util.List;

@Service
public class PerformanceReviewService {

    private final PerformanceReviewRepository repository;

    public PerformanceReviewService(
            PerformanceReviewRepository repository) {
        this.repository = repository;
    }

    private PerformanceReviewDto toDto(PerformanceReview p) {
        return new PerformanceReviewDto(p.getReviewId(),
                p.getEmployeeId(), p.getComments(),
                p.getRating(), p.getReviewDate());
    }

    public PerformanceReviewDto createOrUpdate(
            Long id, PerformanceReviewDto dto) {

        PerformanceReview p;

        if (id != null) {
            p = repository.findById(id)
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Review not found: " + id));
        } else {
            p = new PerformanceReview();
        }

        p.setEmployeeId(dto.getEmployeeId());
        p.setComments(dto.getComments());
        p.setRating(dto.getRating());
        p.setReviewDate(dto.getReviewDate());

        return toDto(repository.save(p));
    }

    public List<PerformanceReviewDto> getAll() {
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }

    public PerformanceReviewDto getById(Long id) {
        PerformanceReview p = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Review not found: " + id));
        return toDto(p);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(
                    "Review not found: " + id);
        repository.deleteById(id);
    }
}