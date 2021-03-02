package ru.koryakin.dacha2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         // .csrf().disable();
        http.authorizeRequests().antMatchers("/manage/**", "/manage.html").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/blog").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/manage/messages/*").authenticated();
        http.formLogin().loginPage("/login.html").permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/manage.html",true)
                .failureUrl("/login.html?error=true")
                .usernameParameter("username")
                .passwordParameter("password");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .authorities("ROLE_ADMIN")
                .and()
                .withUser("user")
                .password(encoder.encode("user"))
                .authorities("ROLE_USER");
        //TODO:change auth type to more secure
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/send");
        web.ignoring().antMatchers("/reserve");
    }
}
