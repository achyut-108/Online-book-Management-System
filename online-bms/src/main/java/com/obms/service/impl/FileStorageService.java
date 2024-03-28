package com.obms.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.obms.entity.AssignmentEntity;
import com.obms.repo.AssignmentRepository;

/**
 * @author saurabh.shyam
 * @since Feb 25, 2021 5:49 PM
 */
@Service
public class FileStorageService {

	@Autowired
	private AssignmentRepository fileDBRepository;

	public AssignmentEntity store(MultipartFile file, Long user) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		AssignmentEntity assignmentEntity = new AssignmentEntity();
		assignmentEntity.setAssignmentName(fileName);
		assignmentEntity.setFileType(file.getContentType());
		assignmentEntity.setData(file.getBytes());
		assignmentEntity.setTrainerId(user);
		assignmentEntity.setAssignmentType("ASSIGNMENT");
		return fileDBRepository.save(assignmentEntity);
	}

	public AssignmentEntity getFile(BigInteger id, Long user) {
		return fileDBRepository.findByIdAndTrainerId(id, user);
	}

	public List<AssignmentEntity> getAllFiles(Long user) {
		List<AssignmentEntity> allfiles = new ArrayList<>();
		fileDBRepository.findAllByTrainerId(user).forEach(allfiles::add);
		return allfiles;
	}
}
