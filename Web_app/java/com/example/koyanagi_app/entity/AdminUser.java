package com.example.koyanagi_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
	
	/**
	 * ユーザーID
	 */
	private String userId;
	
	/**
	 * ユーザーパスワード
	 */
	private String userPassword;
	
	/**
	 * 権限
	 */
	private Role adminLevel;

}
