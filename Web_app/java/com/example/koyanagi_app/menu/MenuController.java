package com.example.koyanagi_app.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニューに関するコントローラー
 */
@Controller
public class MenuController {
	
	/**
	 * menuを表示するコントローラー
	 * 戻り値はメニューページ
	 */
	@GetMapping("/menu")
	public String showMenu() {
		
		return "/menu";
	}

}
