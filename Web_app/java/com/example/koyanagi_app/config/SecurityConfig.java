package com.example.koyanagi_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	private final UserDetailsService userDetailsService;
//	
//	private final PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception {

		httpSec
			//HTTPリクエストのセキュリティ設定
			.authorizeHttpRequests(authz -> authz
				//ログインページのアクセスは認証を必要としない
				.requestMatchers("/login").permitAll()
				//その他のページへのリクエストは認証が必要
				.anyRequest().authenticated())
				//フォームベースのログイン設定
				.formLogin(lForm -> lForm
				//カスタムのログインページのURLを指定はここ
				.loginPage("/login")
				//ログイン処理のURLを指定
				.loginProcessingUrl("/authentication")
				//ユーザーIDの属性を指定
				.usernameParameter("userId")
				//ユーザーのパスワードの属性を指定
				.passwordParameter("userPassword")
				//ログイン成功時のリダイレクト先を指定
				.defaultSuccessUrl("/menu")
				//ログイン失敗時のリダイレクト先を指定
				.failureUrl("/login?error"))
				//----ログアウトの設定----
				.logout(logout -> logout
						//ログアウトを処理するURLを指定
						.logoutUrl("/logout")
						//ログアウト成功時のURLを指定
						.logoutSuccessUrl("/login?logout")
						//ログアウト時にセッションを無効
						.invalidateHttpSession(true)
						//クッキーの削除
						.deleteCookies("JSESSIONID")
		);
		
		return httpSec.build();
	}

}
