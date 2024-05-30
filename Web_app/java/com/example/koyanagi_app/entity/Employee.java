package com.example.koyanagi_app.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社員情報を扱うエンティティ。<br>
 * <テーブル情報><br>
 * 	テーブル名：employee<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	/**
	 * 社員ID<br>
	 * カラム名：employee_id　型：serial
	 */
	private Integer employeeId;
	
	/**
	 * 社員名<br>
	 * カラム名：employee_name 型：varchar
	 */
	private String employeeName;
	
	/**
	 * 社員名のフリガナ<br>
	 * カラム名：employee_name_kana 型：varchar：
	 */
	private String employeeNameKana;
	
	/**
	 * 社員の年齢<br>
	 * カラム名：employee_age 型：Integer
	 */
	private Integer employeeAge;
	
	/**
	 * 社員の住所<br>
	 * カラム名：employee_address 型：varchar
	 */
	private String employeeAddress;
	
	/**
	 * 社員のメールアドレス<br>
	 * カラム名：employee_mail 型：varchar
	 */
	private String employeeMail;
	
	/**
	 * 社員の携帯番号<br>
	 * カラム名：employee_phone 型：varchar
	 */
	private String employeePhone;
	
	/**
	 * 入社日<br>
	 * カラム名：employee_hiredate 型：date
	 */
	private LocalDate employeeHiredate;
	
	/**
	 * 部署ID<br>
	 * カラム名：dept_id 型：char
	 */
	private String departmentId;
	
	/**
	 * 部署情報<br>
	 * テーブル名：Department
	 */
	private Department department;

}
