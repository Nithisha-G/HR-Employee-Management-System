package com.example.demo.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.exceptionhandler.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
	
	private static final Logger logger= LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    private DepartmentDto toDto(Department d) {
        return new DepartmentDto(d.getDepartmentId(),
                d.getDepartmentName(), d.getLocation());
    }

    public DepartmentDto createOrUpdate(
            Long id, DepartmentDto dto) {

        Department d;

        if (id != null) {
            d = repository.findById(id)
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Department not found: " + id));
        } else {
            d = new Department();
        }

        d.setDepartmentName(dto.getDepartmentName());
        d.setLocation(dto.getLocation());

        return toDto(repository.save(d));
    }

    public List<DepartmentDto> getAll() {
    	logger.info("gettingall the departments");
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }

    public DepartmentDto getById(Long id) {
        Department d = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Department not found: " + id));
        return toDto(d);
    }

    public void delete(Long id) {
    	
    	logger.warn("going to delete");
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(
                    "Department not found: " + id);

        logger.info("deleted succesfully");
        repository.deleteById(id);
    }
}


