package com.naon.practice.springboot.config.auth;

import com.naon.practice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable()        //To use h2-console
                .and()
                .authorizeRequests()                                    //Must needed for antMatcher option, start point of setting for authorization
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()          //give all permission
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())        //Give authorization to someone has user auth
                .anyRequest().authenticated().and()                     //Other URL
                .logout().logoutSuccessUrl("/")
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
