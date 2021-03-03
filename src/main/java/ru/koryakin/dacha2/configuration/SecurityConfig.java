package ru.koryakin.dacha2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.koryakin.dacha2.services.DachaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    DachaUserDetailsService userDetailsService;

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
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/send");
        web.ignoring().antMatchers("/reserve");
    }
}
