package com.inspired.springSecInActionChapter6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
    private JpaUserDetailsService jpaUserDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    @Autowired
    public AuthenticationProviderService(JpaUserDetailsService jpaUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, SCryptPasswordEncoder sCryptPasswordEncoder){
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sCryptPasswordEncoder = sCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

       CustomUserDetails user =  jpaUserDetailsService.loadUserByUsername(username);
        switch (user.getUser().getAlgorithm()){
            case BCRYPT:
                return checkPassword(user, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(user, password, sCryptPasswordEncoder);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }


    private Authentication checkPassword(CustomUserDetails userDetails, String password, PasswordEncoder passwordEncoder){
        if(passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                                           userDetails.getPassword(),
                                                           userDetails.getAuthorities());
        }else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
