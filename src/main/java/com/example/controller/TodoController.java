package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.user.model.UserEntity;
import com.example.domain.user.service.TodoService;
import com.example.form.AddForm;
import com.example.form.DetailForm;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//一覧画面
	@GetMapping("/todos")
	public String getTodoList(Model model) {
		
		//完了取得
		List<UserEntity> trueList = service.selectTrue();
		
		//未完了取得
		List<UserEntity> falseList = service.selectFalse();
		
		model.addAttribute("trueList", trueList);
		model.addAttribute("falseList", falseList);
		
		return "html/list";
	}
	
	//追加画面
	@GetMapping("/add")
	public String getAddTodo(Model model, AddForm form) {
		
		
		return "html/add";
	}
	
	//追加処理
	@PostMapping("/add")
	public String postAddTodo(@ModelAttribute @Validated AddForm form, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return getAddTodo(model, form);
		}
		
		UserEntity todo = modelMapper.map(form, UserEntity.class);
		
		service.insertOne(todo);
		
		return "redirect:/todos";
	}
	
	//詳細画面
	@GetMapping("/todos/{id:.+}")
    public String getDetail(Model model, @Validated DetailForm form, @PathVariable("id") Integer id) {
		
		DetailForm detailForm = modelMapper.map(service.selectOne(id), DetailForm.class);
		
		model.addAttribute("detailForm", detailForm);
		
		return "html/detail";
	}
	
	//更新処理
	@PostMapping("/update")
	public String updateTodo(@ModelAttribute @Validated DetailForm form, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "html/detail";
		}
		
		service.updateOne(form.getId(), form.getTitle(), form.getTimeLimit(), form.getIsDone());
		
		return "redirect:/todos";
	}
	
	//削除処理
	@PostMapping(value="/todos", params="delete")
	public String deleteTodo(Model model) {
		
		List<UserEntity> trueList = service.selectTrue();
		
		for(UserEntity trues : trueList) {
			
			service.deleteTodo(trues.getIsDone());
			
		}
		
		return "redirect:/todos";
	}
	
}
