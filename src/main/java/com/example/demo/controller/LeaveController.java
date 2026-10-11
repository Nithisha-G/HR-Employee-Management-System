package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeaveDto;
import com.example.demo.service.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
	
	private static final Logger logger=LoggerFactory.getLogger(LeaveController.class);

    private final LeaveService service;

    public LeaveController(LeaveService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LeaveDto> apply(
            @RequestBody LeaveDto dto) {
    	logger.info("requesting to apply a leave");
    	logger.warn("not take leave repeatedly");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createOrUpdate(null, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveDto> update(
            @PathVariable Long id,
            @RequestBody LeaveDto dto) {
    	logger.info("reuest to update the leave ");
        return ResponseEntity.ok(service.createOrUpdate(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LeaveDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @GetMapping
    public ResponseEntity<List<LeaveDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveDto> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}