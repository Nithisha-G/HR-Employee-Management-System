package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.dto.AttendanceDto;
import com.example.demo.entity.Attendance;
import com.example.demo.exceptionhandler.ResourceNotFoundException;
import com.example.demo.repository.AttendanceRepository;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    private AttendanceDto toDto(Attendance a) {
        return new AttendanceDto(a.getAttendanceId(),
                a.getEmployeeId(), a.getAttendanceDate(),
                a.getStatus());
    }

    public AttendanceDto createOrUpdate(Long id, AttendanceDto dto) {
        Attendance a;

        if (id != null) {
            a = repository.findById(id)
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Attendance not found: " + id));
        } else {
            a = new Attendance();
        }

        a.setEmployeeId(dto.getEmployeeId());
        a.setAttendanceDate(dto.getAttendanceDate());
        a.setStatus(dto.getStatus());

        return toDto(repository.save(a));
    }

    public List<AttendanceDto> getAll() {
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }

    public AttendanceDto getById(Long id) {
        Attendance a = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Attendance not found: " + id));
        return toDto(a);
    }

    public List<AttendanceDto> getByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId)
                .stream().map(this::toDto).toList();
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(
                    "Attendance not found: " + id);
        repository.deleteById(id);
    }
}