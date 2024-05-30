package com.example.koyanagi_app.department;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Webから部署情報に関するデータを受け取るためのフォーム
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentForm {
	
	/**
	 * 部署ID
	 */
	@NotBlank(message = "部署IDは入力必須です")
//	@Size(min= 1, max = 3, message = "部署IDは1～3桁を入力してください")
	@Pattern(regexp = "^[0-9]{1,3}$", message="部署IDは数字3桁で入力してください")
	private String deptId;
	
	/**
	 * 部署名
	 */
	@NotBlank(message = "部署名は入力必須です")
	private String deptName;
	
	/**
	 * 登録ページか更新ページを表示ためのフラグ
	 */
	private Boolean isFlag;
	
	/**
	 * チェックボックスの値を保存するためのリスト
	 */
	private List<String> checkBoxList;

}
