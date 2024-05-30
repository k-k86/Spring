package com.example.koyanagi_app.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.koyanagi_app.department.DepartmentForm;
import com.example.koyanagi_app.employee.EmployeeForm;
import com.example.koyanagi_app.employee.EmployeeHelper;
import com.example.koyanagi_app.entity.Department;
import com.example.koyanagi_app.entity.Employee;
import com.example.koyanagi_app.service.department.DepartmentService;
import com.example.koyanagi_app.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;
/**
 * 社員情報の更新に関するコントローラー
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/update")
public class updateEmployeeController {

	private final EmployeeService empService;
	private final DepartmentService deptService;

	/**
	 * 更新するページに遷移するメソッド。<br>
	 */
	@GetMapping("/update_page/{id}")
	public String showUpdateForm(
			@PathVariable Integer id, Model model, DepartmentForm form, RedirectAttributes attributes) {

		form.setIsFlag(false);

		//更新対象のデータを取得
		Employee updateTarget = empService.selectByIdEmployee(id);

		//データが取得できなかったとき
		if(updateTarget == null) {

			attributes.addFlashAttribute("message", "対象データの取得に失敗しました");
			return "redirect:/employee/list";
		}

		//更新対象をエンティティに変換する
		EmployeeForm employeeForm = EmployeeHelper.conversionEmployeeForm(updateTarget);

		model.addAttribute("employeeForm", employeeForm);
		model.addAttribute("employee", updateTarget);
		model.addAttribute("deptAllList", deptService.selectDepartmentByNot000());

		return "/employee/insert/form";
	}

	/**
	 * 更新する部署情報をチェックし、確認フォームへ遷移するメソッド<br>
	 * 部署IDと部署名が入力されているかつ、部署IDと部署名が登録されているデータと一致しない場合にのみ、
	 * 確認ページに遷移する。<br>
	 * それ以外はエラーメッセージをmodelに格納し入力フォームへ遷移する。<br>
	 */
	@PostMapping("/update_check")
	public String updateDepartmentCheck(
			@ModelAttribute @Validated EmployeeForm form, BindingResult bindingResult,Model model) {

		//社員情報をIDで取得
		model.addAttribute( "employee", empService.selectByIdEmployee( form.getEmpId()) );
		//プルダウン表示のため取得
		model.addAttribute("deptAllList", deptService.selectDepartmentAll());

		//入力チェック
		if( bindingResult.hasErrors() ) {

			//フォームへ戻す
			return "/employee/insert/form";
		}
		
		//部署IDを条件に部署情報を取得
		Department dept = deptService.selectDepartmentById( form.getDeptId() );

		//部署名が取得できたとき
		if (dept != null) {
			model.addAttribute("deptName", dept.getDepartmentName());
		}

		return "/employee/insert/form_check";
	}


	/**
	 * 送られてきた情報を更新処理し一覧ページへ遷移するメソッド<br>
	 * 戻り値は部署一覧ページ<br>
	 */
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute EmployeeForm form, RedirectAttributes attributes) {
		
		//更新時に0で登録するとエラーが起きるためnullに置換
		form.setDeptId(  EmployeeHelper.nullCheck(form.getDeptId())  );

		//エンティティに変換
		Employee employee = EmployeeHelper.conversionEmployee(form);
		//更新実行
		Integer result = empService.updateEmployee(employee);
		
		if(result == 0) {
			
			attributes.addFlashAttribute("message", "更新に失敗しました。もう一度お試しください。");
			
			return "redirect:/employee/select/list";
		}

		return "redirect:/employee/update/update_completion";
	}

	/**
	 * 更新完了ページの表示
	 */
	@GetMapping("/update_completion")
	public String showCreateCompletion() {

		return "/employee/update/update_completion";
	}




}
