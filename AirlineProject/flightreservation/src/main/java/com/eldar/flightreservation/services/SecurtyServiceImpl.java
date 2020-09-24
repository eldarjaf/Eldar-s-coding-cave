package com.eldar.flightreservation.services;

import com.eldar.flightreservation.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class SecurtyServiceImpl implements SecurityService {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public boolean login(String username, String password) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        }catch (UsernameNotFoundException ex){
            return false;
        }

        Authentication token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        boolean result = token.isAuthenticated();

     if (result) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication autentication = context.getAuthentication();
        String us = autentication.getName();
        Object principal = autentication.getPrincipal();
        Object credential= autentication.getCredentials(); //password
        Collection<? extends GrantedAuthority> authorities = autentication.getAuthorities();

        log.debug(us+" \n "+principal.toString()+" \n " +authorities.toString()+"\n"+credential.toString());
        return result;
    }
}
