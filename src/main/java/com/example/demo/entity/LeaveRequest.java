package com.example.demo.entity;

	import jakarta.persistence.*;
	import lombok.*;
	import java.time.LocalDate;

import com.example.demo.enums.LeaveStatus;

	@Entity
	@Table(name = "leave_requests")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class LeaveRequest {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long leaveId;

	    private Long employeeId;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private String reason;
	    private String status;
	    
	    @Enumerated(EnumType.STRING)
	    private LeaveStatus lstatus;
	}

