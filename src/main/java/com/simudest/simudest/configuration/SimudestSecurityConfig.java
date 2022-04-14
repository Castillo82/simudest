package com.simudest.simudest.configuration;

import com.simudest.simudest.repository.UsuarioRepository;
import com.simudest.simudest.service.SimudestUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SimudestSecurityConfig extends WebSecurityConfigurerAdapter {

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/")
                    .permitAll()
                .and()
                .logout()
                    .permitAll();
    }
*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .regexMatchers("/admin/.*").hasRole("ADMIN")
                .regexMatchers("/private/.*").authenticated()
                .regexMatchers("/").authenticated()
            //    .anyRequest()
           //         .permitAll()

                //Autenticacion
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                ;
    }


    @Autowired
    public SimudestUserDetailsService simudestUserDetailsService(UsuarioRepository usuarioRepository) throws Exception {
        return new SimudestUserDetailsService(usuarioRepository);
    }

}