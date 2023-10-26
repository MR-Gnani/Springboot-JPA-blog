package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO(Data Access Object)
// 자동으로 bean 등록됨
// @Repository 생략이 가능하다.	
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}


//JPA 네이밍 쿼리 전략
	// Select * From user where username = ?1 password = ?2;
	// User findByUsernameAndPassword(String username, String password);
	
	// @Query(value="Select * From user where username = ?1 password = ?2", nativeQuery = true)
	// User login(String username, String password);