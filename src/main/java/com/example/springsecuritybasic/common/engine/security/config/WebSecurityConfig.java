package com.example.springsecuritybasic.common.engine.security.config;


import com.example.springsecuritybasic.business.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MemberService memberService;

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
    public void configure(HttpSecurity http) throws Exception {
        log.info("configure(HttpSecurity http)");
        http.authorizeRequests()
                .antMatchers("/","/home").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                //.defaultSuccessUrl("/hello")
                .permitAll();

        http.csrf()
                .disable();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/denied");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    //백기선님
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password(passwordEncoder().encode("password"))
//                    .roles("USER")
//                    .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

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
