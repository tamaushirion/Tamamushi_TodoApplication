package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class ListForm {
	
	private Integer id;
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date timeLimit;
	private Boolean isDone;
	
}
