package com.example.koyanagi_app.repository.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.example.koyanagi_app.entity.Employee;

/**
 * 社員情報のCRUD処理を提供するインターフェース<br>
 */
@Mapper
public interface EmployeeMapper {
	
	/**
	 * 社員情報の抽出件数COUNT(*)を取得するメソッド<br>
	 * @return 社員情報の抽出件数<br>
	 */
	Integer countAll();
	
	/**
	 * 社員情報の抽出件数COUNT(*)を取得するメソッド<br>
	 * 条件は部署IDと社員名(部分一致)<br>
	 * @return 社員情報の抽出件数<br>
	 */
	Integer countByDeptIdAndEmpName(@Param("departmentId")String departmentId, @Param("employeeName")String employeeName);
	
	/**
	 * 社員情報を全件取得するメソッド<br>
	 * 戻り値は全件取得した社員情報<br>
	 * @param pageable：ページネーションのためPageableを渡す
	 * @return 社員情報のリスト<br>
	 */
	List<Employee> pageFindAllEmployee(@Param("pageable") Pageable pageable);

	/**
	 * 社員情報と部署情報を1:1の関係で全件取得してくるメソッド<br>
	 * 戻り値は社員情報+部署情報<br>
	 * @return List<Employee>
	 */
	List<Employee> findAllEmployee();

	/**
	 * 社員情報をIDを条件に取得するメソッド<br>
	 * 引数は社員ID。戻り値はIDによって取得した社員情報と部署情報<br>
	 * 取得できなかった場合はnull<br>
	 * @param Integer id(社員ID)
	 * @return employee(社員情報)
	 */
	Employee findByIdEmployee(Integer id);
	
	/**
	 * 社員情報を部署IDを条件に取得するメソッド<br>
	 * 引数は部署ID、戻り値は部署IDによって取得した社員情報<br>
	 * 取得できなかった場合はnull<br>
	 * @param String deptId(部署ID)
	 * @return employee(社員情報)
	 */
	List<Employee> findBydepartmentIdEmployee(String deptId);
	
	/**
	 * 社員情報を部署IDと社員名(部分一致)で検索するメソッド<br>
	 * 引数は検索ボックスから送られてきた部署IDと社員名<br>
	 * 戻り値は条件に合致した社員情報<br>
	 * @param empName:社員名
	 * @param deptId:部署ID
	 * @param pageable:ページネーションの為、Pageableを渡す
	 * @return 検索条件に合致したList型の社員情報
	 */
	List<Employee> pageFindByDeptIdAndEmpName(
			@Param("departmentId")String departmentId, @Param("employeeName")String employeeName, @Param("pageable") Pageable pageabl);
	
	/**
	 * 社員情報を登録するメソッド<br>
	 * 引数はフォームから受け取った社員の登録情報<br>
	 */
	void insertEmployee(Employee employee);

	/** 社員情報を更新するメソッド<br>
	 * 戻り値は更新件数<br>
	 */
	Integer updateEmployee(Employee employee);

	/**
	 * 社員情報を削除するメソッド<br>
	 * 戻り値は削除された件数<br>
	 * @param Integer id(社員ID)<br>
	 * @return 削除件数<br>
	 */
	Integer deleteByIdEmployee(Integer id);

}
