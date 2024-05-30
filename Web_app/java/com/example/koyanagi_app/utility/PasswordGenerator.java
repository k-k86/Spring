package com.example.koyanagi_app.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ハッシュ化した文字列(パスワード)を返すクラス
 */
public class PasswordGenerator {
	
	/**
	 * 送られてきた文字列をハッシュ化するメソッド
	 */
	public static void main(String[] args) {
		
		var encode = new BCryptPasswordEncoder();
		
		String rowPassword = "test";
		
		String encodePass = encode.encode(rowPassword);
		
		System.out.println("テストパス：" + encodePass);
	}

}
