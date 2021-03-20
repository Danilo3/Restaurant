package ru.koryakin.dacha2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import ru.koryakin.dacha2.services.impl.DachaUserDetailsServiceImpl;

@EnableWebSecurity
@ComponentScan(basePackages = {"ru.koryakin.dacha2.services"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    private DachaUserDetailsServiceImpl userDetailsService;

    @Autowired
    public void setUserDetailsService(DachaUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/send");
        web.ignoring().antMatchers("/reserve");
        web.ignoring().antMatchers(HttpMethod.POST, "/subscribe/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/order/**");
        web.ignoring().antMatchers("/gallery-images/**");
        web.ignoring().antMatchers("/blog-images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().antMatchers("/manage/**", "/manage.html", "/api/**").authenticated();
        http.formLogin().loginPage("/login.html").permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/manage.html", true)
                .failureUrl("/login.html?error=true")
                .usernameParameter("username")
                .passwordParameter("password");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        if (activeProfile.trim().equalsIgnoreCase("api-tests")) {
            http.csrf().disable();
        }
        http.authorizeRequests().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/swagger-ui/**").authenticated();
    }
}
