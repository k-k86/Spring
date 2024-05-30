package com.example.koyanagi_app.department;

import java.util.List;

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

import com.example.koyanagi_app.entity.Department;
import com.example.koyanagi_app.service.department.DepartmentService;

import lombok.RequiredArgsConstructor;

/**
 * 部署に関するコントローラー
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

	//サービスをコンテナから取得
	private final DepartmentService deptService;

//	private final EmployeeService empService;

	/**
	 * 部署情報一覧ページを表示するメソッド<br>
	 * 戻り値は部署情報一覧ページ
	 */
	@GetMapping("/list")
	public String showAllDepartment(@ModelAttribute DepartmentForm form ,Model model) {

		List<Department> deptList = deptService.selectDepartmentByNot000();


		//部署情報が取得できなかったとき
		if(deptList == null || deptList.isEmpty()) {

			//一覧表示ページで区別するために部署情報が取得できなかった時にnullをセットする
			model.addAttribute("deptAllList", null );
		} else {

			//モデルに部署情報を格納
			model.addAttribute("deptAllList", deptList );

			for(Department dept : deptList) {

				form.setDeptId(dept.getDepartmentId());
			}
		}

		return "/department/list";
	}


	/**
	 * 受け取った部署名で検索し、検索結果ページを表示するメソッド。<br>
	 * 戻り値は部署情報一覧ページ。ただし表示されるものは部署名で検索された検索結果。
	 */
	@PostMapping("/search_key")
	public String shoDepartmentByName(DepartmentForm departmentForm, Model model) {

		if( departmentForm.getDeptName().equals("") ) {

			model.addAttribute("deptAllList", deptService.selectDepartmentByNot000());

			return "/department/list";
		}

		//SELECT文で部署情報取得
		List<Department> deptByName = deptService.selectDepartmentByAmbiguousName( departmentForm.getDeptName() );

		//部署情報が取得できなかったとき
		if( deptByName == null || deptByName.isEmpty() ) {

//			model.addAttribute("errorMessage", "検索した部署はありません");

			return "/department/list";
		}

		//検索した結果をモデルにデータを格納
		model.addAttribute("deptAllList", deptByName);

		return "/department/list";
	}

	/**
	 * 部署情報の詳細ページへ遷移するメソッド。<br>
	 * 戻り値は部署の所属している社員を表示する<br>
	 */
	@GetMapping("/detail/{id}")
	public String departmentDetail(@PathVariable String id, Model model) {

		List<Department> departmentDetail = deptService.selectAllDepartmentAndEmployee(id);

		if( departmentDetail.isEmpty() ) {

			model.addAttribute("errorMessage", "所属社員はいません");

			return "/department/list_detail";
		}

		//送られてきた部署IDに所属している社員をModelに格納
		model.addAttribute("detailDepartment", departmentDetail);

		return "/department/list_detail";
	}

	/**
	 * 登録ページに遷移するメソッド<br>
	 * 戻り値は登録フォームページ<br>
	 */
	@GetMapping("/form")
	public String createDepartment(@ModelAttribute DepartmentForm form) {

		form.setIsFlag(true);

		return "/department/create_form";
	}

	/**
	 * 送られてきた部署情報を取得し確認ページへ遷移するメソッド。<br>
	 * 部署IDと部署名が入力されているかつ、部署IDと部署名が登録されているデータと一致しない場合にのみ、
	 * 確認ページに遷移する。<br>
	 * それ以外はエラーメッセージをmodelに格納し入力フォームへ遷移する。<br>
	 */
	@PostMapping("/create_check")
	public String postDepartment(
			@ModelAttribute @Validated DepartmentForm form, BindingResult bindingResult,Model model) {

		String departmentInputCheck = deptService.departmentIdAndNameCheck( form.getDeptId(), form.getDeptName() );

		//登録ページ用にフラグをセット
		form.setIsFlag(true);

		//入力チェック
		if( bindingResult.hasErrors() ) {

			//フォームへ戻す
			return "/department/create_form";
		}

		//部署IDと部署名を条件で部署情報が取得できたとき
		if( "IdAndNameOut".equals(departmentInputCheck) ) {

			//エラーメッセージをセット
			model.addAttribute("errorMessage", "入力された部署IDと部署名が重複しています。");

			//フォームへ戻す
			return "/department/create_form";

			//部署IDを条件で取得できたとき
		} else if( "IdOut".equals(departmentInputCheck) ) {

			//エラーメッセージをセット
			model.addAttribute("errorMessage", "入力された部署IDが重複しています。");

			//フォームへ戻す
			return "/department/create_form";

			//部署名を条件で取得できたとき
		} else if( "NameOut".equals(departmentInputCheck) ) {

			//エラーメッセージをセット
			model.addAttribute("errorMessage", "入力された部署名が重複しています。");

			//フォームへ戻す
			return "/department/create_form";
		}

		return "/department/form_check";
	}

	/**
	 * 確認ページから送られてきた部署情報をデータべースに登録する。<br>
	 * その後、完了ページへ遷移する。<br>
	 */
	@PostMapping("/create")
	public String saveDepartment(@ModelAttribute DepartmentForm form, RedirectAttributes attributes) {

		//登録処理
		deptService.insertDepartment( DepartmentHelper.conversionDepartment(form) );

		return "redirect:/department/post_completion";
	}

	/**
	 * 登録完了ページの表示
	 */
	@GetMapping("/post_completion")
	public String showCompletion() {

		return "/department/post_completion";
	}

	/**
	 * 更新するページに遷移するメソッド。<br>
	 */
	@GetMapping("/update_page/{id}")
	public String showUpdateForm(
			@PathVariable String id, Model model, DepartmentForm form, RedirectAttributes attributes) {
		//更新対象のデータを取得
		Department updateTarget = deptService.selectDepartmentById(id);

		//データが取得できなかったとき
		if(updateTarget == null) {

			attributes.addFlashAttribute("message", "対象データの取得に失敗しました");
			return "redirect:/department/list";
		}

		//更新対象をエンティティに変換する
		DepartmentForm department = DepartmentHelper.conversionDepartmentForm(updateTarget);

		model.addAttribute("departmentForm", department);
		model.addAttribute("department", updateTarget);

		return "/department/update_form";
	}

	/**
	 * 更新する部署情報をチェックし、確認フォームへ遷移するメソッド<br>
	 * 部署IDと部署名が入力されているかつ、部署IDと部署名が登録されているデータと一致しない場合にのみ、
	 * 確認ページに遷移する。<br>
	 * それ以外はエラーメッセージをmodelに格納し入力フォームへ遷移する。<br>
	 */
	@PostMapping("/update_check")
	public String updateDepartmentCheck(
			@ModelAttribute @Validated DepartmentForm form, BindingResult bindingResult,Model model) {

		//部署情報をIDで取得
		Department dept = deptService.selectDepartmentById( form.getDeptId() );
		model.addAttribute("department", dept);

		//入力チェック
		if( bindingResult.hasErrors() ) {

			//フォームへ戻す
			return "/department/update_form";
		}

		//部署名が条件で部署情報を取得できたとき
		if( deptService.selectDepartmentByName( form.getDeptName() ) != null ) {

			//エラーメッセージをセット
			model.addAttribute("errorMessage", "入力された部署名が重複しています");

			//フォームへ戻す
			return "/department/update_form";
		}

		return "/department/form_check";
	}

	/**
	 * 送られてきた情報を更新処理し一覧ページへ遷移するメソッド<br>
	 * 戻り値は部署一覧ページ
	 */
	@PostMapping("/update")
	public String updateDepartment(@ModelAttribute DepartmentForm form, RedirectAttributes attributes) {

		//エンティティに変換
		Department department = DepartmentHelper.conversionDepartment(form);
		//更新実行
		deptService.departmentUpdate(department);

		return "redirect:/department/update_completion";
	}

	/**
	 * 更新完了ページの表示
	 */
	@GetMapping("/update_completion")
	public String showCreateCompletion() {

		return "/department/update_completion";
	}

	/**
	 * 選択された部署情報を取得し、削除確認ページに遷移するメソッド<br>
	 * 戻り値は削除確認ページ<br>
	 */
	@PostMapping("/delete_check")
	public String deleteCheckDepartment(
			@ModelAttribute DepartmentForm form, RedirectAttributes attributes, Model model) {

		//選択されている部署情報を取得
		List<String> ids = form.getCheckBoxList();

		//チェックボックスが選択されていないとき
		if( ids == null || ids.isEmpty() ) {
			//メッセージを格納
			attributes.addFlashAttribute("message", "チェックボックスが選択されていません");

			return "redirect:/department/list";
		}
		
		if(ids.size() >= 6) {
			
			//メッセージを格納
			attributes.addFlashAttribute("message", "削除する部署の選択は5件までにしてください");

			return "redirect:/department/list";
		}

		//チェックボックスで選択された部署情報を複数件取得する
		List<Department> selectCheckList = deptService.selectDepartmentByListId( form.getCheckBoxList() );

		model.addAttribute("deptList", selectCheckList);

		return "/department/delete_check";

	}

	/**
	 * 削除メソッドを実行し、一覧画面に遷移するメソッド<br>
	 * 戻り値は部署情報一覧ページ<br>
	 */
	@PostMapping("/delete")
	public String deleteDepartment(DepartmentForm form ,RedirectAttributes attributes) {

		//選択されている部署情報の部署IDを取得
		List<String> ids = form.getCheckBoxList();

		//削除の実行
		Integer result = deptService.deleteDepartmentById( ids );
		attributes.addFlashAttribute("message", result + "件の削除に成功しました");

		return "redirect:/department/list";

	}

}
