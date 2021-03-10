package com.example.springsecuritybasic;

import com.example.springsecuritybasic.common.engine.security.config.CustomAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthProvider customAuthProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //사용자 요청 중 /resources/로 시작하는 요청은 제외
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(customAuthProvider)//로그인 요청시 AuthenticationProvider로 요청이 전달됨
                .authorizeRequests() //요청을 어떻게 보안을 걸 것이냐 설정하는 것 (가장 중요)
                    .antMatchers("/","/home").permitAll() //css, js, img  파일 더 있을 것
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProcess") //사용자 로그인 화면에서 아이디/비밀번호 입력후 전송되는 url, form태그의 action과 매핑된다.
                    .usernameParameter("userName")
                    .passwordParameter("password")
                    .permitAll() //모두 들어올 수 있게
                    .and()
                .csrf()
                    .disable()
                .logout()
                    .logoutSuccessUrl("/") //로그아웃 성공 후 url
                    .permitAll();


    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                    .username("user")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")
                    .build();

        return new InMemoryUserDetailsManager(user);

    }

//    백기선님
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests() //요청을 어떻게 보안을 걸 것이냐 설정하는 것 (가장 중요)
//                .antMatchers("/","/home").permitAll() //css, js, img  파일 더 있을 것
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll() //모두 들어올 수 있게
//                .and()
//            .csrf()
//                .disable()
//            .logout()
//                .logoutSuccessUrl("/") //로그아웃 성공 후 url
//                .permitAll();
//    }
}
