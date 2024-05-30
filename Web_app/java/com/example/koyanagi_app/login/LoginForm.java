package com.example.koyanagi_app.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {
	
	/**
	 * ユーザーID
	 */
	@NotBlank(message = "ログインIDは入力必須です")
	private String userId;
	
	/**
	 * ユーザーパスワード
	 */
	@NotBlank(message = "パスワードは入力必須です")
	private String userPassword;

}
