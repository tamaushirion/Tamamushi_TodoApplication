package com.example.domain.user.model;

import java.util.Date;

import lombok.Data;

@Data
public class TodoEntity {
	
	private Integer id;
	private String title;
	private Boolean isDone;
	private Date timeLimit;
}
