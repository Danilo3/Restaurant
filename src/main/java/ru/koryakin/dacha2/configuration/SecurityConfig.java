package ru.koryakin.dacha2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.koryakin.dacha2.services.DachaUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackages = {"ru.koryakin.dacha2.services"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DachaUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();// .and().csrf().disable();
        http.authorizeRequests().antMatchers("/manage/**", "/manage.html").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/blog").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/manage/menu/").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/manage/messages/*").authenticated();
        http.formLogin().loginPage("/login.html").permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/manage.html",true)
                .failureUrl("/login.html?error=true")
                .usernameParameter("username")
                .passwordParameter("password");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/send");
        web.ignoring().antMatchers("/reserve");
    }
}
