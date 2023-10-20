package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO(Data Access Object)
// 자동으로 bean 등록됨
// @Repository 생략이 가능하다.	
public interface UserRepository extends JpaRepository<User, Integer> {

}
