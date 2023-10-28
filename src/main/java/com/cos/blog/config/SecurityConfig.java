package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cos.blog.config.auth.PrincipalDetailService;

import jakarta.servlet.DispatcherType;


// localhost:8000/auth/loginForm
@Configuration // 빈등록 IOC관리
@EnableWebSecurity // 시큐리티 필터 등록 가능
@EnableMethodSecurity
public class SecurityConfig {
	
	// 아래 [1], [2]번 Principal 메서드가 없어도 스프링이 처리해주는 듯??
	// [1] @Autowired
	// 		  private PrincipalDetailService principalDetailService;
	
	// [2]  시큐리티가 대신 로그인해주면서 password를 가로채기하는데
	// 		해당 password가 뭘로 해시되어 회원가입이 되었는지 알아야 
	//			같은 해시로 암호화해서 DB에 있는 해시랑 비교할 수 있음
	// 		public void 
	// 		authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
	//			auth.userDetailsService(principalDetailService).passwordEncoder(encode());
	// 		}
	
	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				
        http
        		.csrf(AbstractHttpConfigurer :: disable) // 토큰 비활성화, 
                .authorizeHttpRequests(
                        auth -> auth
                        		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 없으면 리디렉션 발생 
                        		.requestMatchers(new AntPathRequestMatcher("/"),
                        										new AntPathRequestMatcher("/auth/**"), 
                        										new AntPathRequestMatcher("/js/**"),
                        										new AntPathRequestMatcher("/css/**"),
                        										new AntPathRequestMatcher("/image/**"),
                        										new AntPathRequestMatcher("/dummy/**"))
                        		.permitAll() // new AntPath 없으면 부트 작동 안됨
                                .anyRequest().authenticated()
                                )
              .formLogin(
                		login -> login
              					.loginPage("/auth/loginForm")
              					.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 로그인을 가로챈다.
              					.defaultSuccessUrl("/")
              					.permitAll()
               					);
                
        		return http.build();
    }
}


