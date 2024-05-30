package com.example.koyanagi_app.employee;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社員情報に関するフォーム<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForm {

	/**
	 * 社員ID<br>
	 */
	private Integer empId;

	/**
	 * 社員名<br>
	 */
	@NotBlank(message = "社員名は入力必須です")
	private String empName;

	/**
	 * 社員名のフリガナ
	 */
	@NotBlank(message = "社員名のフリガナは入力必須です")
	@Pattern(regexp="^([ア-ヶ]+)?$", message="カタカナで入力してください")
	private String empNameKana;

	/**
	 * 社員の年齢
	 */
	@Min(15)
	@Max(100)
	private Integer empAge;

	/**
	 * 社員の住所
	 */
	private String empAddress;

	/**
	 * 社員のメールアドレス
	 */
	@Pattern(regexp="^([a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,})?$"
			, message="メールアドレスの入力形式が間違っています")
	private String empMail;

	/**
	 * 社員の携帯番号
	 */
	@Pattern(regexp="^(\\d{3}-\\d{4}-\\d{4})?$", message="電話番号の形式が間違っています")
	private String empPhone;

	/**
	 * 入社日
	 */
	@PastOrPresent(message="未来の日付が入力されています")
	private LocalDate empHiredate;

	/**
	 * 部署ID
	 */
	private String deptId;
	
	/**
	 * 部署名
	 */
	private String deptName;
	
	/**
	 * 登録か更新か判別するためのフラグ
	 */
	private Boolean isFlag;
	
	/**
	 * 選択されたチェックボックスを入れるためのリスト
	 */
	private List<Integer> checkIdList;

}


