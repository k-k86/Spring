package com.example.koyanagi_app.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部署情報を扱うエンティティ。<br>
 * <テーブル情報><br>
 * 	テーブル名：department<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	
	/**
	 * 部署ID<br>
	 * カラム名：department_id
	 */
	private String departmentId;

	/**
	 * 部署名<br>
	 * カラム名：department_name
	 */
	private String departmentName;
	
	/**
	 * 1:Nの関係<br>
	 * 部署情報に社員情報を結合するするためのリスト<br>
	 */
	private List<Employee> EmployeeList;

}
