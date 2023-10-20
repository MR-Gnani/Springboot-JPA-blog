package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 자동으로 MySQL에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
// @DynamicInsert // insert시 null 인 필드를 제외시켜준다.
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따른다.
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length=30)
	private String username; // id
	
	@Column(nullable = false, length=100) // 해쉬로 비밀번호 암호화
	private String password;
	
	@Column(nullable = false, length=50)
	private String email;
	
	// @ColumnDefault("user")
	// DB는 Role Type이라는게 없다. --> Enumerated 사용
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER
	
	@CreationTimestamp // 시간이 자동으로 입력
	private Timestamp createDate;
}
