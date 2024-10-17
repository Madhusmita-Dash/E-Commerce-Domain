package com.domain.jwt.service;

import com.domain.jwt.dao.UserDao;
import com.domain.jwt.entity.JwtRequest;
import com.domain.jwt.entity.JwtResponse;
import com.domain.jwt.entity.User;
import com.domain.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    private final UserDao userDao;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtService(UserDao userDao, JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName= jwtRequest.getUserName();
        String userPassword= jwtRequest.getUserpassword();
        authenticate(userName,userPassword);

        final UserDetails userDetails=loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userDao.findById(userName).get();
        return new JwtResponse(newGeneratedToken, user);  // Token first, then user
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).orElse(null);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        } else {
            throw new UsernameNotFoundException("username is not valid");
        }
    }

    private Set getAuthorities(User user){
        Set authorities=new HashSet();

        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
       }
    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }catch (DisabledException e) {
            throw new Exception("User is Disabled");
        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("BadCredentials from the user");
        }

    }
}
