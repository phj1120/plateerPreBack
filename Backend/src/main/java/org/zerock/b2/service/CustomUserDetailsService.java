package org.zerock.b2.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@Setter
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[CustomUserDetailsService]: loadUserByUsername");

        UserDetails userDetails = User.builder()
                .username(username)
                .password(passwordEncoder.encode("1111"))
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .build();
        return userDetails;
    }

}
