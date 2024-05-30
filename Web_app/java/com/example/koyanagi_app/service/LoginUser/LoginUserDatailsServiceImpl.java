package com.example.koyanagi_app.service.LoginUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.koyanagi_app.entity.AdminUser;
import com.example.koyanagi_app.entity.LoginUser;
import com.example.koyanagi_app.entity.Role;
import com.example.koyanagi_app.repository.adminuser.AdminUserMapper;

import lombok.RequiredArgsConstructor;

/**
 * カスタム認証のサービス
 */
@Service
@RequiredArgsConstructor
public class LoginUserDatailsServiceImpl implements UserDetailsService {

	private final AdminUserMapper adminUserMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		//認証テーブルからデータを取得
		AdminUser adminUser = adminUserMapper.findByUserId(userId);

		//テーブルに対象データが存在するとき
		if(adminUser != null) {

			//UserDetailsの実装クラスの返却
			return new LoginUser(adminUser.getUserId(), 
					adminUser.getUserPassword(),
					getAdminLevelLIst( adminUser.getAdminLevel() ) 
					);

		} else {
			//対象データがない場合
			throw new UsernameNotFoundException(userId + "指定しているユーザーIDは存在しません");
		}

	}

	private List<GrantedAuthority> getAdminLevelLIst(Role role) {

		//権限リスト
		List<GrantedAuthority> adminLevel = new ArrayList<>();

		//列挙型からロールを取得
		adminLevel.add( new SimpleGrantedAuthority(role.name()) );

		// 管理者レベルがADMINの場合、USERの権限も付与
		if( role == Role.ADMIN ) {

			adminLevel.add(
					new SimpleGrantedAuthority( Role.USER.toString() )
					);
		}
		return adminLevel;
	}

}
