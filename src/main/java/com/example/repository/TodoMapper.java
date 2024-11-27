package com.example.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.TodoEntity;

@Mapper
public interface TodoMapper {
	
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
	public void updateOne(@Param("id") Integer id, @Param("title") String title, @Param("timeLimit") Date TimeLimit, @Param("isDone") Boolean isDone);
	
	//完了済みTodo削除
	public int deleteCompletedTodoList(Boolean isDone);
}
