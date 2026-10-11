package com.example.demo.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name="Employee APIs")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    
    
    @Operation(summary="employee created",description="created")
    @ApiResponse(responseCode = "201",description = "created successfully")
    @PostMapping
    public ResponseEntity<EmployeeDto> create(
            @RequestBody EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createOrUpdate(null, dto));
    }

    
    @Operation(summary="updated",description="updated using id")
    @ApiResponse(responseCode = "200",description = "created successfully")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(
            @PathVariable Long id,
            @RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(
                service.createOrUpdate(id, dto));
    }

    
    @Operation(summary="employee getbyid",description="Fetched using id")
    @ApiResponse(responseCode = "200",description = "Fetched successfully")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(
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