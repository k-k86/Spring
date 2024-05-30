package com.example.koyanagi_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * パスワードに関するコンフィグ
 */
@Configuration
public class PasswordConfig {
	
	/**
	 * パスワードをハッシュ化するするメソッド<br>
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
