package com.farmstory.security;

import com.farmstory.oauth2.MyOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final MyOauth2UserService myOauth2UserService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //로그인 설정
        http.formLogin(login -> login
                .loginPage("/user/UserLogin")
                .defaultSuccessUrl("/")//컨트롤러 요청 주소
                .failureUrl("/user/UserLogin?success=100")
                .usernameParameter("uid")
                .passwordParameter("pass"));

        //로그아웃 설정
        http.logout(logout -> logout
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/?success=101"));


        //Oauth2 설정
        http.oauth2Login(login -> login
                .loginPage("/user/login")
                .userInfoEndpoint(endpoint -> endpoint.userService(myOauth2UserService)));

        // 인가 설정
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/introduction/**").permitAll()
                .requestMatchers("/userinfo/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/crop/**").permitAll()
                .requestMatchers("/community/**").permitAll()
                .requestMatchers("/crop/CropWrite").authenticated()
                .anyRequest().permitAll());

        //보안 설정
        http.csrf(configure -> configure.disable());


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
