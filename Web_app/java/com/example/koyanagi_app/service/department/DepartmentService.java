package com.example.koyanagi_app.service.department;

import java.util.List;

import com.example.koyanagi_app.entity.Department;
import com.example.koyanagi_app.entity.Employee;

/**
 * 部署情報に関するサービス。<br>
 * このインターフェースは部署に関するサービスを提供する。<br>
 */
public interface DepartmentService {
	
	/**
	 * 部署情報を全件取得するメソッド<br>
	 * 戻り値は部署情報が全件入ったリスト<br>
	 * @return List<Department>
	 */
	List<Department> selectDepartmentAll();
	
	/**
	 * 部署情報を無所属の部署以外を取得するメソッド<br>
	 * 戻り値は無所属以外の部署情報<br>
	 */
	List<Department> selectDepartmentByNot000();
	
	/**
	 * 引数で渡された部署名(あいまい検索)で部署情報を取得するメソッド<br>
	 * 戻り値は部署名で取得した部署情報<br>
	 * 取得できなかった場合はNULL
	 * @param 部署名
	 * @return Department
	 */
	List<Department> selectDepartmentByAmbiguousName(String name);
	
	/**
	 * 部署IDを条件に取得するメソッド<br>
	 * 戻り値は単一のデータ(部署情報)<br>
	 * 取得できなかった場合はnull<br>
	 */
	Department selectDepartmentById(String id);
	
	/**
	 * 部署IDを条件に取得するメソッド。<br>
	 * 戻り値は複数の部署情報。<br>
	 * 取得できなかった場合はnull。
	 */
	List<Department> selectDepartmentByListId(List<String> id);
	
	/**
	 * 部署名を条件に取得するメソッド。<br>
	 * 戻り値は複数の部署情報。<br>
	 * 取得できなかった場合はnull。
	 */
	Department selectDepartmentByName(String name);
	
	/**
	 * 引数で渡された部署IDで部署を検索し、
	 * その部署IDに所属している社員情報を取得するメソッド。<br>
	 */
	List<Department> selectAllDepartmentAndEmployee(String id);
	
	/**
	 * 引数で渡された部署情報を登録するメソッド。<br>
	 */
	void insertDepartment(Department dept);

	/**
	 * 部署情報を更新するメソッド<br>
	 * 戻り値は更新件数<br>
	 * 引数は更新する部署情報<br>
	 */
	void departmentUpdate(Department department);
	
	/**
	 * 部署情報を削除するメソッド<br>
	 * 引数は部署名<br>
	 * 戻り値は削除件数<br>
	 */
	Integer deleteDepartmentById(List<String> id);
	
	/**
	 * 部署情報の条件に対して部署IDで取得できた場合と、部署名で取得できた場合と、
	 * 両方取得できた場合の判別を提供するサービス<br>
	 * 引数はフォームから取得したIDと名前<br>
	 * 戻り値はその条件にあった文字列<br>
	 * <ul>●戻り値の種類
	 * <li>部署IDと部署名で取得：IdAndNameOut</li>
	 * <li>部署IDで取得：IdOut</li>
	 * <li>部署名で取得：NameOut</li>
	 * </ul>
	 */
	String departmentIdAndNameCheck(String id, String name);
	
	/**
	 * 部署情報を削除する際に無所属になる社員情報の部署IDを所属なしに置換するメソッド<br>
	 * 引数は所属している部署を削除される社員情報<br>
	 */
	void updateEmployeeByDeleteDepartment(Employee emp);
	
}
