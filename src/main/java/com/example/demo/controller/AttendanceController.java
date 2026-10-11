package com.example.demo.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AttendanceDto;
import com.example.demo.service.AttendanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@Tag(name="Attendance APIs")
public class AttendanceController {
	
	private static final Logger logger= LoggerFactory.getLogger(AttendanceController.class);

    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> create(
            @RequestBody AttendanceDto dto) {
    	logger.info("Received request to create or update employee");
    	logger.trace("tracing full details");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createOrUpdate(null, dto));
    }

    
    @Operation(summary="attendance update",description="updated")
    @ApiResponse(responseCode = "200",description = "Fetched successfully")
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> update(
            @PathVariable Long id,
            @RequestBody AttendanceDto dto) {
        return ResponseEntity.ok(service.createOrUpdate(id, dto));
    }

    
    @Operation(summary="attendance details",description="Fetched")
    @ApiResponse(responseCode = "200",description = "Fetched successfully")
    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDto>> getByEmployeeId(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(
                service.getByEmployeeId(employeeId));
    }
    
    
    @Operation(summary="deleted using id",description="deleted")
    @ApiResponse(responseCode = "204",description = "sucessfully deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	logger.warn("request to delete a attendance record");
    	logger.info("delete request sucessfull");
    	logger.trace("give the detailed information");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}