package com.example.domain.user.service;

import java.util.Date;
import java.util.List;

import com.example.domain.user.model.TodoEntity;


public interface TodoService {
	
	//DBからTodo一覧取得
	public List<TodoEntity> selectAllTodoList();
	
	//完了リスト取得
	public List<TodoEntity> selectCompletedTodoList(); 
	
	//未完了リスト取得
	public List<TodoEntity> selectIncompleteTodoList();
	
	//Todo一件取得
	public TodoEntity selectOne(Integer id);
	
	//Todo一件登録
	public void insertOne(TodoEntity todo);
	
	//Todo一件更新
	public void updateOne(Integer id, String title, Date TimeLimit, Boolean isDone);
	
	//完了済みTodo削除
	public void deleteCompletedTodoList(Boolean isDone);
}
