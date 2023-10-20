package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // Board 클래스가 자동으로 MySQL에 테이블이 생성된다.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인됨.
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne // Many = Board,  One = User
	@JoinColumn(name="userId")
	private User user; // 원래 DB는 오브젝트를 저장할 수 없다. 그래서 FK를 사용. 자바는 오브젝트 저장 할 수 있다.
									// JPA(ORM)을 사용하면 오브젝트 저장이 가능해진다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다.(FK가 아니다.) DB에 컬럼을 만들지 마라.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
