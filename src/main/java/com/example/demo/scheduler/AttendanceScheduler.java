package com.example.demo.scheduler;



import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.EmployeeRepository;



@Component
public class AttendanceScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(AttendanceScheduler.class);

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceScheduler(
            EmployeeRepository employeeRepository,
            AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Scheduled(cron = "0 0 23 * * *")
    @Transactional
    public void markAbsentEmployees() {

        LocalDate today = LocalDate.now();

        logger.info("Attendance scheduler started for {}", today);

        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {

            boolean exists = attendanceRepository
                    .existsByEmployeeIdAndAttendanceDate(
                            employee.getEmployeeId(), today);

            if (!exists) {
                Attendance attendance = new Attendance();

                attendance.setEmployeeId(employee.getEmployeeId());
                attendance.setAttendanceDate(today);
                attendance.setStatus("ABSENT");

                attendanceRepository.save(attendance);

                logger.info("Marked employee {} as ABSENT",
                        employee.getEmployeeId());
            }
        }

        logger.info("Attendance scheduler completed for {}", today);
    }
}