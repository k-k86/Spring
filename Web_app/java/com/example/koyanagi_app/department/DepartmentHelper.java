package com.example.koyanagi_app.department;

import com.example.koyanagi_app.entity.Department;

/**
 * 部署情報に関するヘルパークラス。<br>
 * このクラスは部署情報に関連する文字チェックや変換行う。<br>
 */
public class DepartmentHelper {
	
	/**
	 * エンティティへ変換をするメソッド<br>
	 * 引数はDepartmentのフォーム<br>
	 * 戻り値はDepartmentのエンティティ<br>
	 */
	public static Department conversionDepartment(DepartmentForm deptForm) {
		
		Department dept = new Department();
		
		dept.setDepartmentId( deptForm.getDeptId() );
		dept.setDepartmentName( deptForm.getDeptName() );
		
		return dept;
	}
	
	/**
	 * フォームへ変換をするメソッド<br>
	* 引数はDepartmentのエンティティ<br>
	 * 戻り値はDepartmentのフォーム<br>
	 */
	public static DepartmentForm conversionDepartmentForm(Department department) {
		
		DepartmentForm form = new DepartmentForm();
		
		form.setDeptId( department.getDepartmentId() );
		form.setDeptName( department.getDepartmentName() );
		
		return form;
	}
	

}
