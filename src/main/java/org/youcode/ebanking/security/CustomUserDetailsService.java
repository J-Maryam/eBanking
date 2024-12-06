package org.youcode.ebanking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.youcode.ebanking.models.AppUser;
import org.youcode.ebanking.repositories.UserRepository;

import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));


        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(appUser.getRole().getName().toString()));
        System.out.println("here : " + appUser.getRole().getName().toString());
        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                authorities);
    }
}
