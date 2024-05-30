package com.example.koyanagi_app.employee;

import com.example.koyanagi_app.entity.Employee;

/**
/* 社員に関するヘルパークラス。<br>
/* このクラスは社員情報に関連する文字チェックや変換行う。<br>
 */
public class EmployeeHelper {

	/**
	 * エンティティへ変換をするメソッド<br>
	 * 引数はEmployeeのフォーム<br>
	 * 戻り値はEmployeeのエンティティ<br>
	 */
	public static Employee conversionEmployee(EmployeeForm empForm) {

		Employee emp = new Employee();

		emp.setEmployeeId( empForm.getEmpId() );
		emp.setEmployeeName( empForm.getEmpName() );
		emp.setEmployeeNameKana( empForm.getEmpNameKana() );
		emp.setEmployeeAge( empForm.getEmpAge() );
		emp.setEmployeeAddress( empForm.getEmpAddress() );
		emp.setEmployeeMail( empForm.getEmpMail() );
		emp.setEmployeePhone( empForm.getEmpPhone() );
		emp.setEmployeeHiredate( empForm.getEmpHiredate() );
		emp.setDepartmentId( empForm.getDeptId() );

		return emp;
	}
	
	/**
	 * フォームへ変換するメソッド<br>
	 * 引数はEmployeeのエンティティ。
	 * 戻り値はEmployeeのフォーム<br>
	 */
	public static EmployeeForm conversionEmployeeForm(Employee employee) {
		
		EmployeeForm form = new EmployeeForm();
		
		form.setEmpId( employee.getEmployeeId() );
		form.setEmpName( employee.getEmployeeName() );
		form.setEmpNameKana( employee.getEmployeeNameKana() );
		form.setEmpAge( employee.getEmployeeAge() );
		form.setEmpAddress( employee.getEmployeeAddress() );
		form.setEmpMail( employee.getEmployeeMail() );
		form.setEmpPhone( employee.getEmployeePhone() );
		form.setEmpHiredate( employee.getEmployeeHiredate() );
		form.setDeptId( employee.getDepartmentId() );
		
		return form;
	}

	/**
	 * 0をnullに置換するためのメソッド<br>
	 * 引数はID。戻り値は0の場合はnull、それ以外の場合は引数の値<br>
	 */
	public static String nullCheck(String id) {

		//プルダウンの部分が0の時に"000"に置換する
		if( "0".equals(id) ) {

			return id = "000";
		}

		return id;
	}

}
