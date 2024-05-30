package com.example.koyanagi_app.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.koyanagi_app.login.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	/**
	 * ログインページに遷移
	 */
	@GetMapping
	public String showLogin(@ModelAttribute LoginForm lForm) {
		
		return "login/login";
		
	}

}
