package com.example.domain.user.service;

import java.util.Date;
import java.util.List;

import com.example.domain.user.model.UserEntity;


public interface TodoService {
	
	//DBから一覧取得
	public List<UserEntity> selectAll();
	
	//完了取得
	public List<UserEntity> selectTrue(); 
	
	//未完了取得
	public List<UserEntity> selectFalse();
	
	//ユーザー一件取得
	public UserEntity selectOne(Integer id);
	
	//Todo一件登録
	public void insertOne(UserEntity todo);
	
	//Todo一件更新
	public void updateOne(Integer id, String title, Date TimeLimit, Boolean isDone);
	
	//完了済みTodo削除
	public void deleteTodo(Boolean isDone);
}
