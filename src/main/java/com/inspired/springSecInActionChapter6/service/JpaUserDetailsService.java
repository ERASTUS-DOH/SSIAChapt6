package com.inspired.springSecInActionChapter6.service;

import com.inspired.springSecInActionChapter6.dto.User;
import com.inspired.springSecInActionChapter6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> usernameException = ()-> new UsernameNotFoundException("Problem occurred during Authentication");
        User user = userRepository.findUserByUsername(username).orElseThrow(usernameException);
        return new CustomUserDetails(user);
    }
}
