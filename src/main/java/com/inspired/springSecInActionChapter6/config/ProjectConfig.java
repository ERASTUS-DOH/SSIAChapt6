package com.inspired.springSecInActionChapter6.config;

import com.inspired.springSecInActionChapter6.service.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProviderService authenticationProviderService;

//    @Autowired
//    public ProjectConfig(AuthenticationProviderService authenticationProviderService){
//      this.authenticationProviderService = authenticationProviderService;
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder(){
        return new SCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProviderService);
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin().defaultSuccessUrl("/main", true);
        httpSecurity.authorizeRequests().anyRequest().authenticated();

    }

}
