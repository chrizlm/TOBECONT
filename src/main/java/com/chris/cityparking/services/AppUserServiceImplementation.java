package com.chris.cityparking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.LogInForm;
import com.chris.cityparking.modules.Role;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.getByEmail(username);
        if(appUser == null){
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }else{
            log.info("User found in database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(appUser.getEmail(), appUser.getPassword(), authorities);
    }



    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("saving new user {} to the database", appUser.getFirstName());
        //appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void changeUserPassword(EmailPasswordForm emailPasswordForm) {
        log.info("making changes to user details");
        AppUser user = appUserRepo.getByEmail(emailPasswordForm.getEmail());
        user.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        appUserRepo.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        AppUser appUser = appUserRepo.getByEmail(username);
        Role role = roleRepo.findByName(roleName);

        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String userName) {
        log.info("fetching user {} ", userName);
        return appUserRepo.getByEmail(userName);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("fetching all users");
        return appUserRepo.findAll();
    }

/*
    @Override
    public Map<String, String> userLogin(LogInForm logInForm) {
        String username = logInForm.getUsername();
        String password = logInForm.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
       //return authenticationManager.authenticate(authenticationToken);

       Authentication authentication = authenticationManager.authenticate(authenticationToken);
       User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                .sign(algorithm);


        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        //tokens.put("refresh_token", refresh_token);
        return tokens;
       /*

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
       /* response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);
        */
       /*
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);


    }
    */

    @Override
    public String userLogin(LogInForm logInForm) {
        String username = logInForm.getUsername();
        String password = logInForm.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //return authenticationManager.authenticate(authenticationToken);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                .sign(algorithm);


       String tokens = access_token;
        return tokens;
    }


}
