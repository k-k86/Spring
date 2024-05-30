package com.example.koyanagi_app.service.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.koyanagi_app.entity.Employee;

/**
 * 部署情報に関するサービス。<br>
 * このインターフェースは部署に関するサービスを提供する。<br>
 */
public interface EmployeeService {

	/**
	 * 社員情報を全件取得するメソッド<br>
	 * 戻り値は社員情報が全件入ったリスト<br>
	 */
	List<Employee> selectAllEmployee();
	
	/**
	 * ページングのための社員情報取得メソッド<br>
	 */
	Page<Employee> selectAllEmployeePage(Pageable pageable);

	/**
	 * IDを条件に社員情報を取得するメソッド<br>
	 * 戻り値は社員情報<br>
	 */
	Employee selectByIdEmployee(Integer id);

	/**
	 * 社員IDを条件に社員情報を複数取得するメソッド。<br>
	 * 戻り値は複数の社員情報。<br>
	 * 取得できなかった場合はnull。
	 */
	List<Employee> selectEmployeeByListById(List<Integer> empId);
	
	/**
	 * 部署IDを条件に社員情報を取得するメソッド。<br>
	 * 戻り値は複数の社員情報。<br>
	 * 取得できなかった場合はnull。
	 */
	List<Employee> selectEmployeeListByDepartmentId(String deptId);
	
	/**
	 * 部署IDや社員名で絞り込み社員情報を取得するメソッド<br>
	 * 引数は部署IDと社員名(部分一致)<br>
	 * 戻り値は条件によって絞り込まれた社員情報<br>
	 * @param 部署ID<br>
	 * @param 社員名<br>
	 * @return 条件によって取得した社員名<br>
	 */
	Page<Employee> selectEmployeeBYDepartmentIdOrEmployeeName(String departmentId, String employeeName, Pageable pageable);

	/**
	 * 社員情報を登録するメソッド<br>
	 * 引数はフォームから送られてきた社員情報
	 */
	void insertEmployee(Employee employee);

	/** 社員情報を更新するメソッド<br>
	 *  戻り値は更新件数<br>
	 */  
	Integer updateEmployee(Employee employee);

	/**
	 * 社員情報を削除するメソッド<br>
	 * 戻り値は削除された件数<br>
	 * @param Integer id(社員ID)<br>
	 * @return 削除件数<br>
	 */
	Integer deleteByIdEmployee(List<Integer> ids);

}
