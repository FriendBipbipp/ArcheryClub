package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final CustomUserDetails customUserDetails;
	public SecurityConfig(CustomUserDetails customUserDetails) {
		this.customUserDetails = customUserDetails;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
	    .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/welcome", "/register", "/register/**", "/css/**", "/js/**", "/img/**").permitAll()
	        .anyRequest().authenticated()
	    )
	    .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email") // ⬅️ เพิ่มบรรทัดนี้เข้าไป
                .defaultSuccessUrl("/adminDashboard", true) // หรือ URL ที่ต้องการไปหลังล็อกอินสำเร็จ
                .permitAll()
         )
	   .logout(logout -> logout.permitAll());

		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
