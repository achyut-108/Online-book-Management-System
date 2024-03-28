package com.obms.domain.assignment;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saurabh.shyam
 * @since Feb 25, 2021 6:12 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDetails implements Serializable {

	public static final long serialVersionUID = 1L;
	private String name;
	private String url;
	private String type;
	private long size;
}
