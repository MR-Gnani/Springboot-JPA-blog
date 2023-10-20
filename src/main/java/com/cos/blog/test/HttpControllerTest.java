package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 --> 응답(HTML 파일) : @Controller

// 사용자가 요청 -->  응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	// lombok Test
	// Builder 사용시 순서에 구애 받지 않고 사용 가능
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("nani@nate.com").build();
		System.out.println(TAG+"getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter : " + m.getId());
		return "lombok test 완료";
	}
	// 인터넷 브라우저에서의 요청은 무조건 get요청만 가능하다.
	// http://localhost:8080/http/get  (Select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() +"," + m.getEmail();
	}
	
	// http://localhost:8080/http/post  (Insert)
	@PostMapping("/http/post")
	public String postTest(Member m) {
		return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() +"," + m.getEmail();
	}
	
	// http://localhost:8080/http/put  (Update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	// http://localhost:8080/http/delete  (Delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
