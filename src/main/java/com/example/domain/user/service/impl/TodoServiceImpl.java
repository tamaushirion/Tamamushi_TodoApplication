package com.example.domain.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.TodoEntity;
import com.example.domain.user.service.TodoService;
import com.example.repository.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoMapper mapper;
	//Todo一覧取得
	public List<TodoEntity> selectAllTodoList() {
		return mapper.selectAllTodoList();
	}
	
	//完了リスト取得
	public List<TodoEntity> selectCompletedTodoList() {
		return mapper.selectCompletedTodoList();
	}
	
	//未完了リスト取得
	public List<TodoEntity> selectIncompleteTodoList() {
		return mapper.selectIncompleteTodoList();
	}
	
	//Todo一件取得
	public TodoEntity selectOne(Integer id) {
		return mapper.selectOne(id);
	}
	
	//Todo一件登録
	public void insertOne(TodoEntity todo) {
		mapper.insertOne(todo);
	}
	
	//Todo一件更新
	public void updateOne(Integer id, String title, Date TimeLimit, Boolean isDone) {
		mapper.updateOne(id, title, TimeLimit, isDone);
	}
	
	//完了済みTodo削除
	public void deleteCompletedTodoList(Boolean isDone) {
		int count = mapper.deleteCompletedTodoList(isDone);
	}
}
