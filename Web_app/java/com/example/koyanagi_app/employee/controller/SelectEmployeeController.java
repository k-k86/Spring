package com.example.koyanagi_app.employee.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.koyanagi_app.employee.EmployeeForm;
import com.example.koyanagi_app.entity.Department;
import com.example.koyanagi_app.entity.Employee;
import com.example.koyanagi_app.service.department.DepartmentService;
import com.example.koyanagi_app.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;

/**
 * 社員情報の一覧や詳細情報など、SELECT文に関するコントローラー<br>
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/employee/select")
public class SelectEmployeeController {

	private final EmployeeService empService;
	
	private final DepartmentService deptService;

	/**
	 * 社員の一覧情報を表示するメソッド<br>
	 * 戻り値は社員情報一覧ページ
	 */
	@GetMapping("/list")
	public String showAllEmployee(
			@ModelAttribute EmployeeForm form ,Model model, @PageableDefault(page=0 ,size = 7)Pageable pageable) {

		//ページング
		Page<Employee> pageList = empService.selectAllEmployeePage(pageable);
		
		//部署情報が取得できなかったとき
		if(pageList == null || pageList.isEmpty()) {

			//一覧表示ページで区別するために部署情報が取得できなかった時にnullをセットする
			model.addAttribute("pageList", null );
			
		} else {

			//モデルに社員情報を格納
			model.addAttribute("pageList", pageList );
		}
		
		//プルダウン表示のために部署情報を取得
		List<Department> deptAllList = deptService.selectDepartmentAll();
		//部署情報をモデルに格納
		model.addAttribute("deptAllList", deptAllList);
		
		//ページネーションをModelに格納
		model.addAttribute("pageList", pageList);

		return "/employee/select/list";
	}
	
	/**
	 * 部署情報の詳細ページへ遷移するメソッド。<br>
	 * 戻り値は部署の所属している社員を表示する<br>
	 */
	@GetMapping("/detail/{id}")
	public String showDetailEmployee(@PathVariable Integer id, Model model) {

		Employee emp = empService.selectByIdEmployee(id);

		//社員情報が取得できなかったとき
		if( emp == null ) {

			model.addAttribute("errorMessage", "社員情報はありません");

			return "/department/list_detail";
		}

		//送られてきた部署IDに所属している社員をModelに格納
		model.addAttribute("employeeDetail", emp);

		return "/employee/select/list_detail";
	}
	
	/**
	 * 部署IDと社員名(部分一致)で社員情報を検索するメソッド<br>
	 * 戻り値は社員一覧ページ<br>
	 */
	@PostMapping("/search_emp")
	public String showEmployeeBySearch(@ModelAttribute EmployeeForm form, Model model, @PageableDefault(page=0 ,size = 7)Pageable pageable, RedirectAttributes attributes) {
		
		//プルダウンが未選択かつ、テキストボックスが空
		if(		( "0".equals(form.getDeptId()) && ( "".equals(form.getEmpName())) )	) {
			
			return "redirect:/employee/select/list";
		}
		
		//プルダウン表示用の部署情報
		model.addAttribute("deptAllList", deptService.selectDepartmentAll());
		
		//社員情報を部署IDと社員名で検索するSELECT文を実行
		Page<Employee> pageList = empService.selectEmployeeBYDepartmentIdOrEmployeeName(form.getDeptId(), form.getEmpName(), pageable);
		
		//社員情報がnullだったとき
		if( pageList.isEmpty() ) {
			
			//検索結果のメッセージをModelに格納
			attributes.addFlashAttribute("serachMessage", "検索条件にヒットした社員はいません");
			
			//社員情報をModelに格納
			model.addAttribute("pageList", null);
			
			return "redirect:/employee/select/list";
		}
		
		//社員情報をModelに格納
		model.addAttribute("pageList", pageList);
		
		return "/employee/select/list";
	}
	
	/**
	 * 直接URLで指定されてしまうと405エラーが出てしまうのでその対策<br>
	 */
	@GetMapping("/search_emp")
	public String getShowEmployeeBySearch() {
		
		return "redirect:/employee/select/list";
	}



}
