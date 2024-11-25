package com.example.domain.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.UserEntity;
import com.example.domain.user.service.TodoService;
import com.example.repository.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoMapper mapper;
	
	public List<UserEntity> selectAll() {
		return mapper.selectAll();
	}
	
	//完了取得
	public List<UserEntity> selectTrue() {
		return mapper.selectTrue();
	}
	
	//未完了取得
	public List<UserEntity> selectFalse() {
		return mapper.selectFalse();
	}
	
	//ユーザー一件取得
	public UserEntity selectOne(Integer id) {
		return mapper.selectOne(id);
	}
	
	//Todo一件登録
	public void insertOne(UserEntity todo) {
		mapper.insertOne(todo);
	}
	
	//Todo一件更新
	public void updateOne(Integer id, String title, Date TimeLimit, Boolean isDone) {
		mapper.updateOne(id, title, TimeLimit, isDone);
	}
	
	//完了済みTodo削除
	public void deleteTodo(Boolean isDone) {
		int count = mapper.deleteTodo(isDone);
	}
}
