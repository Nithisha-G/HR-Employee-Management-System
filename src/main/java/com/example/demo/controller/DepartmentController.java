package com.example.demo.controller;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Tag(name="Department APIs")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @Operation(summary = "created the department",description = "created succesfully")
    @ApiResponse(responseCode = "201",description = "sucess")
    @PostMapping
    public ResponseEntity<DepartmentDto> create(
            @RequestBody DepartmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createOrUpdate(null, dto));
    }

    @Operation(summary = "updated the departement",description = "updated succesfully")
    @ApiResponse(responseCode = "200",description = " update sucess")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> update(
            @PathVariable Long id,
            @RequestBody DepartmentDto dto) {
        return ResponseEntity
        		.ok(service.createOrUpdate(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "deleted emloyee",description = "dleted succesfully")
    @ApiResponse(responseCode = "204",description = " deleted sucess")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}