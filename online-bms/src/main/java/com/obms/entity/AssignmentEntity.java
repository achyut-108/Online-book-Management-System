package com.obms.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author siddhartha.kumar
 */
@Entity
@Table(name = "assignment_trans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity {
    
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
	@Column(name="assignment_name")
    private String assignmentName;
	@Column(name="assignment_type")
    private String assignmentType;
	@Column(name="file_type")
    private String fileType;
	@Column(name="trainer_id")
    private Long trainerId;
	@Column(name="trainee_id")
    private Long traineeId;
    @Lob
    @Column(name="data")
    private byte[] data;
    
}