package com.example.koyanagi_app.repository.adminuser;

import org.apache.ibatis.annotations.Mapper;

import com.example.koyanagi_app.entity.AdminUser;

/**
 * 管理テーブル用のマッパー
 */ 
@Mapper
public interface AdminUserMapper {
	
	/**
	 * ユーザーIDでログイン情報を取得
	 */
	AdminUser findByUserId(String userId);

}
