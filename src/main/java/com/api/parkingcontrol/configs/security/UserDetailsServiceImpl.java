package com.api.parkingcontrol.configs.security;

import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Foud with username: " + username));
        return new User(userModel.getUsername(), userModel.getPassword(), userModel.isEnabled(), userModel.isAccountNonExpired(), userModel.isCredentialsNonExpired(), userModel.isAccountNonLocked(), userModel.getAuthorities());
    }
}
