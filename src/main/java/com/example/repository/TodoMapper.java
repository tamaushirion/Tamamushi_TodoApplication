package com.example.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.UserEntity;

@Mapper
public interface TodoMapper {
	
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
	public void updateOne(@Param("id") Integer id, @Param("title") String title, @Param("timeLimit") Date TimeLimit, @Param("isDone") Boolean isDone);
	
	//完了済みTodo削除
	public int deleteTodo(Boolean isDone);
}
