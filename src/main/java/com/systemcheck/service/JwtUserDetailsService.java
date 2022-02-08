package com.systemcheck.service;

import java.util.ArrayList;

import com.systemcheck.entity.NewUserSign;
import com.systemcheck.repository.NewUserRepository;
import com.systemcheck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public NewUserRepository newUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.systemcheck.entity.User usertmp = userRepository.findByUserId(username);
      if (usertmp.getUserId() != null) {
         return new User(usertmp.getUserId(), usertmp.getPasswd(), new ArrayList<>());
        } else {
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public String userRoleByUserId(String userId) throws UsernameNotFoundException {
        com.systemcheck.entity.User usertmp = userRepository.findByUserId(userId);
        String role = usertmp.getRole();

        if(role != null){
            return role;
        }else{
            throw new UsernameNotFoundException("User doesn't have role: " + userId);
        }
    }

}
