package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveDto;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.exceptionhandler.ResourceNotFoundException;
import com.example.demo.repository.LeaveRepository;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository repository;

    public LeaveService(LeaveRepository repository) {
        this.repository = repository;
    }

    private LeaveDto toDto(LeaveRequest l) {
        return new LeaveDto(l.getLeaveId(), l.getEmployeeId(),
                l.getStartDate(), l.getEndDate(),
                l.getReason(), l.getStatus(),l.getLstatus());
    }

    public LeaveDto createOrUpdate(Long id, LeaveDto dto) {
        LeaveRequest l;

        if (id != null) {
            l = repository.findById(id)
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Leave not found: " + id));
        } else {
            l = new LeaveRequest();
            l.setStatus("PENDING");
        }

        l.setEmployeeId(dto.getEmployeeId());
        l.setStartDate(dto.getStartDate());
        l.setEndDate(dto.getEndDate());
        l.setReason(dto.getReason());

        return toDto(repository.save(l));
    }

    public LeaveDto updateStatus(Long id, String status) {
        LeaveRequest l = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Leave not found: " + id));

        if (!List.of("APPROVED", "REJECTED")
                .contains(status.toUpperCase())) {
            throw new IllegalArgumentException(
                    "Status must be APPROVED or REJECTED");
        }

        l.setStatus(status.toUpperCase());
        return toDto(repository.save(l));
    }

    public List<LeaveDto> getAll() {
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }

    public LeaveDto getById(Long id) {
        LeaveRequest l = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Leave not found: " + id));
        return toDto(l);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(
                    "Leave not found: " + id);
        repository.deleteById(id);
    }
}