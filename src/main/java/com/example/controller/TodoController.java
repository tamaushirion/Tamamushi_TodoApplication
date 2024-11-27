package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.example.domain.user.model.TodoEntity;
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
		List<TodoEntity> completedTodoList = service.selectCompletedTodoList();
		
		//未完了取得
		List<TodoEntity> incompleteTodoList = service.selectIncompleteTodoList();
		
		model.addAttribute("completedTodoList", completedTodoList);
		model.addAttribute("incompleteTodoList", incompleteTodoList);
		
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
		
		//タイトル重複チェックのためリスト全権取得
		List<TodoEntity> allTodoList = service.selectAllTodoList();
		
		//現在の日付取得
		Date nowdate = new Date();
        // SimpleDateFormatをオブジェクト化し、任意のフォーマットを設定
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        //バリデーションチェック
      	if(bindingResult.hasErrors()) {
      	
      		return getAddTodo(model, form);
      	}
        
      	//現在日時と入力日時のチェック
		try {
			if(form.getTimeLimit().before(dateFormat.parse(dateFormat.format(nowdate)))) {
				
				return getAddTodo(model, form);
			}
			
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//タイトル重複チェック
		for(TodoEntity todo : allTodoList) {
			if(todo.getTitle().equals(form.getTitle())) {
				
				return getAddTodo(model, form);
			}
		}
		
		TodoEntity todo = modelMapper.map(form, TodoEntity.class);
		
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
		
		//タイトル重複チェックのためリスト全権取得
		List<TodoEntity> allTodoList = service.selectAllTodoList();
		
		//現在の日付取得
		Date nowdate = new Date();
        // SimpleDateFormatをオブジェクト化し、任意のフォーマットを設定
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		//バリデーションチェック
		if(bindingResult.hasErrors()) {
			return "html/detail";
		}
		
		//現在日時と入力日時のチェック
		//日時のNulチェック
		try {
			if(form.getTimeLimit() == null || form.getTimeLimit().before(dateFormat.parse(dateFormat.format(nowdate)))) {
				
				return "html/detail";
			}
			
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//タイトル重複チェック
		for(TodoEntity todo : allTodoList) {
			if(todo.getTitle().equals(form.getTitle())) {
				//変更するTodoのタイトルに関しては変更前と重複しても良い
				if(todo.getId() != form.getId()) {
					
					return "html/detail";
				}
			}
		}
		
		service.updateOne(form.getId(), form.getTitle(), form.getTimeLimit(), form.getIsDone());
		
		return "redirect:/todos";
	}
	
	//削除処理
	@PostMapping(value="/todos", params="delete")
	public String deleteTodo(Model model) {
		//完了Todoリスト取得
		List<TodoEntity> completedTodoList = service.selectCompletedTodoList();
		
		//完了Todoリストを展開
		for(TodoEntity completedTodo : completedTodoList) {
			//完了済のリストを一件ずつ削除
			//完了済のTodo分処理を実行
			service.deleteCompletedTodoList(completedTodo.getIsDone());
			
		}
		
		return "redirect:/todos";
	}
	
}
