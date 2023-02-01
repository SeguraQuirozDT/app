package com.seguraquirozdt.app.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.seguraquirozdt.app.entity.User;
import com.seguraquirozdt.app.repository.UserRepository;
import com.seguraquirozdt.app.entity.MyUserDetails;

public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	
            User user = userRepository.findUserActivo(email);
            
            if (user == null) {
                throw new UsernameNotFoundException("No se encontro el usuario");
            }
            
            return new MyUserDetails(user);
    }
 
}