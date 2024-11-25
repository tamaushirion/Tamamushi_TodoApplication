package com.example.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AddForm {
	
	private Integer id;
	@Size(min = 1, max = 40, message = "1文字以上、40文字以下で入力してください")
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date timeLimit;
	private Boolean isDone;
}
