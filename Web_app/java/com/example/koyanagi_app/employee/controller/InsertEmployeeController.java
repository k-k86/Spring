package com.example.koyanagi_app.employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.koyanagi_app.employee.EmployeeForm;
import com.example.koyanagi_app.employee.EmployeeHelper;
import com.example.koyanagi_app.entity.Department;
import com.example.koyanagi_app.entity.Employee;
import com.example.koyanagi_app.service.department.DepartmentService;
import com.example.koyanagi_app.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;

/**
 * 社員情報の登録に関するコントローラー
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/employee/insert")
public class InsertEmployeeController {

	private final EmployeeService empService;
	private final DepartmentService deptService;

	/**
	 * 登録ページに遷移するメソッド<br>
	 * 戻り値は登録フォームページ<br>
	 */
	@GetMapping("/create_form")
	public String createShowForm(@ModelAttribute EmployeeForm form, Model model) {
		
//		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		form.setIsFlag(true);
		List<Department> deptAllList = deptService.selectDepartmentByNot000();
		model.addAttribute("deptAllList", deptAllList);
//		model.addAttribute("dateNow", today);

		return "/employee/insert/form";
	}

	/**
	 * 登録情報確認ページへ遷移するメソッド<br>
	 * 戻り値は登録情報確認ページ<br>
	 */
	@PostMapping("/create_check")
	public String createShowCheckPage(
			@ModelAttribute @Validated EmployeeForm form, BindingResult bindingResult,Model model) {
		
		form.setIsFlag(true);
		
		model.addAttribute("deptAllList", deptService.selectDepartmentAll() );
		//入力チェック
		if( bindingResult.hasErrors() ) {
			
			//フォームへ戻す
			return "/employee/insert/form";
		}
		//部署IDを条件に部署名を取得
		Department dept = deptService.selectDepartmentById( form.getDeptId() );
		
		//部署名が取得できたとき
		if (dept != null) {
			model.addAttribute("deptName", dept.getDepartmentName());
		}
		
		
		return "employee/insert/form_check";
	}
	
	/**
	 * 確認ページから送られてきた社員情報をデータべースに登録する。<br>
	 * その後、完了ページへ遷移する。<br>
	 */
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute EmployeeForm form, RedirectAttributes attributes) {
		
		//登録時に0で登録するとエラーが起きるためnullに置換
		form.setDeptId(  EmployeeHelper.nullCheck(form.getDeptId())  );
		
		//エンティティに変換
		Employee emp = EmployeeHelper.conversionEmployee(form);

		//登録処理
		empService.insertEmployee(emp);
		
		return "redirect:/employee/insert/post_completion";
	}
	
	/**
	 * 完了ページの表示
	 */
	@GetMapping("/post_completion")
	public String showCompletion() {

		return "/employee/insert/post_completion";
	}

}
