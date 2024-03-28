package com.obms.rest;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.obms.common.util.ServiceUtil;
import com.obms.domain.assignment.AssignmentDetails;
import com.obms.domain.assignment.AssignmentDownloadResponse;
import com.obms.domain.assignment.AssignmentUploadResponse;
import com.obms.entity.AssignmentEntity;
import com.obms.service.impl.FileStorageService;

/**
 * @author saurabh.shyam
 * @since Feb 25, 2021 5:58 PM
 */
@RestController
@RequestMapping(("gsg/file"))
public class AssignmentController {

	@Autowired
	private FileStorageService storageService;

	@PostMapping("/upload/{userId}/{assignmentType}")
	public AssignmentUploadResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long userId, @PathVariable String assignmentType) {
		String message = "";
		AssignmentUploadResponse assignmentUploadResponse = new AssignmentUploadResponse();
		try {
			storageService.store(file, userId);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			assignmentUploadResponse.setMessage(message);
			ServiceUtil.setSuccess(assignmentUploadResponse);
			return assignmentUploadResponse;
			// return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			assignmentUploadResponse.setMessage(message);
			ServiceUtil.setFailure(assignmentUploadResponse);
			return assignmentUploadResponse;
			// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/files/get/{userId}")
	public AssignmentDownloadResponse getListFiles(@PathVariable Long userId) {
		
		AssignmentDownloadResponse assignmentDownloadResponse = new AssignmentDownloadResponse();
		
		List<AssignmentDetails> files = storageService.getAllFiles(userId).stream().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(String.valueOf(dbFile.getId())).toUriString();

			return new AssignmentDetails(dbFile.getAssignmentName(), fileDownloadUri, dbFile.getFileType(),
					dbFile.getData().length);
		}).collect(Collectors.toList());

		assignmentDownloadResponse.setAssignmentDetails(files);
		return assignmentDownloadResponse;
	}

	@GetMapping("/files/get/{assignmentId}/{userId}")
	public ResponseEntity<byte[]> getFile(@PathVariable BigInteger assignmentId, @PathVariable Long userId) {
		AssignmentEntity fileDB = storageService.getFile(assignmentId, userId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getAssignmentName() + "\"")
				.body(fileDB.getData());
	}
}