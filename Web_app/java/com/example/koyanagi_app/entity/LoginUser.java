package com.example.koyanagi_app.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ユーザーの認証情報を表すクラス
 */
public class LoginUser extends User{
	
	/**
	 * 
	 */
	public LoginUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		
		super(username , password, authorities);
		
	}

}
