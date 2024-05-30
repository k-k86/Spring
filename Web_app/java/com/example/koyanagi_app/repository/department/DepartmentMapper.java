package com.example.koyanagi_app.repository.department;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.koyanagi_app.entity.Department;

/**
 * 部署情報に関するリポジトリ。
 * 部署情報に関するCRUD処理を提供するインターフェース。
 */
@Mapper
public interface DepartmentMapper {

	/**
	 * 部署情報を全件取得するメソッド。<br>
	 * 戻り値は部署情報が全て入ったリスト。<br>
	 * @return 全件取得した部署情報のリスト。
	 */
	List<Department> findAllDepartment();
	
	/**
	 * 部署情報を無所属の部署以外を取得するメソッド<br>
	 * 戻り値は無所属以外の部署情報<br>
	 */
	List<Department> findByNot000Department();

	/**
	 * 引数で渡された部署名で部署情報を取得するメソッド<br>
	 * 戻り値は部署名(部分一致)で取得した部署情報<br>
	 * 取得できなかった場合はNULL
	 * @param 部署名
	 * @return 部署情報
	 */
	List<Department> findByNameAmbiguousDepartment(String name);
	
	/**
	 * 部署IDを条件に取得するメソッド。<br>
	 * 戻り値は複数の部署情報。<br>
	 * 取得できなかった場合はnull。
	 */
	Department findByIdDepartment(String id);
	
	/**
	 * 部署名を条件に取得するメソッド。<br>
	 * 戻り値は複数の部署情報。<br>
	 * 取得できなかった場合はnull。
	 */
	Department findByNameDepartment(String name);
	
	/**
	 * 部署情報と社員情報を取得するメソッド。<br>
	 * 引数は部署ID。戻り値はリスト<br>
	 */
	List<Department> findAllDepartmentAndEmployee(String id);

	/**
	 * 引数で渡された部署情報をデータベースに登録するメソッド。<br>
	 * 引数はWebフォームから送られてきた登録データ。<br>
	 */
	void createDepartment(Department department);

	/**
	 * データベースに保存されている部署情報を更新するメソッド。<br>
	 * 引数はwebフォームから送られてきた部署情報<br>
	 */
	void updateDepartment(Department department);
	
	/**
	 * 部署情報を削除するメソッド<br>
	 * 戻り値は削除件数<br>
	 */
	Integer deleteDepartmentById(String id);
	

}
