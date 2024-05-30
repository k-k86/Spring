package com.example.koyanagi_app.employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.koyanagi_app.employee.EmployeeForm;
import com.example.koyanagi_app.entity.Employee;
import com.example.koyanagi_app.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;

/**
 * 社員情報に関する削除を担当するコントローラー<br>
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/employee/delete")
public class DeleteEmployeeController {
	
	private final EmployeeService service;
	
	/**
	 * 選択された社員情報を取得し、削除確認ページに遷移するメソッド<br>
	 * 戻り値は削除確認ページ<br>
	 */
	@PostMapping("/check_page")
	public String deleteCheckEmployee(
			@ModelAttribute EmployeeForm form, RedirectAttributes attributes, Model model) {

		//選択されている社員情報の社員IDを取得
		List<Integer> checkEmployeeIds = form.getCheckIdList();

		//チェックボックスが選択されていないとき
		if( checkEmployeeIds == null || checkEmployeeIds.isEmpty() ) {
			//メッセージを格納
			attributes.addFlashAttribute("message", "チェックボックスが選択されていません");

			return "redirect:/employee/select/list";
		}

		List<Employee> selectCheckEmployeeList = service.selectEmployeeByListById( form.getCheckIdList() );

		model.addAttribute("empCheckList", selectCheckEmployeeList);

		return "/employee/delete/delete_check";

	}
	
	/**
	 * 削除メソッドを実行し、一覧画面に遷移するメソッド<br>
	 * 戻り値は社員情報一覧ページ<br>
	 */
	@PostMapping("/execute_delete")
	public String deleteEmployee(EmployeeForm form ,RedirectAttributes attributes) {

		//選択されている社員情報を取得
		List<Integer> ids = form.getCheckIdList();

		//削除の実行
		Integer result = service.deleteByIdEmployee( ids );
		attributes.addFlashAttribute("message", result + "件の削除に成功しました");

		return "redirect:/employee/select/list";

	}


}
