package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exceptionhandler.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
	
	private static final Logger logger =LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    
    
     
    private EmployeeDto toDto(Employee e) {
        return new EmployeeDto(
                e.getEmployeeId(),
                e.getEmployeeName(),
                e.getEmail(),
                e.getDesignation(),
                e.getSalary(),
                e.getDepartmentId());
    }
//using create or update
    public EmployeeDto createOrUpdate( Long id, EmployeeDto dto) {

        Employee e;

        if (id != null) {
        	
        	logger.info("updating employee with Id:  "+ id);
            e = repository.findById(id)
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Employee not found: " + id));
        } else {
        	logger.info("creating ");
            e = new Employee();
        }
        

        e.setEmployeeName(dto.getEmployeeName());
        e.setEmail(dto.getEmail());
        e.setDesignation(dto.getDesignation());
        e.setSalary(dto.getSalary());
        e.setDepartmentId(dto.getDepartmentId());

        return toDto(repository.save(e));
    }

    
    //schuedler logic
    
    
    

    public List<EmployeeDto> getAll() {
    	
    	logger.trace("detailed information");
        return repository.findAll()
                .stream().map(this::toDto).toList();
    }
    
   
    
    
    @Cacheable(value = "employee", key = "#id")
    public EmployeeDto getById(Long id) {
        Employee e = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Employee not found: " + id));

        return toDto(e);
    }
    
  
    @CacheEvict(value="employees",key="#id")
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Employee not found: " + id);
        }

        repository.deleteById(id);
    }
}