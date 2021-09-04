package com.example.spring_crud_boot_restFetch.config;

import com.example.spring_crud_boot_restFetch.config.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // сервис, с помощью которого тащим пользователя
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    // класс, в котором описана логика перенаправления пользователей по ролям
    private final LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public SecurityConfig(@Qualifier("appServiceImpl") UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder,
                          LoginSuccessHandler loginSuccessHandler) {
        this.passwordEncoder = passwordEncoder;
        this.loginSuccessHandler = loginSuccessHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // Декларирует, что все запросы к любой конечной точке должны быть авторизованы, иначе они должны быть отклонены
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/webjars/**").permitAll()
                .antMatchers("/api/users/*", "/api/roles").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling();

        http.formLogin()
                .loginPage("/") // указываем страницу с формой логина
                .permitAll() // даем доступ к форме логина всем
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler);

        http.logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .permitAll();
    }
}