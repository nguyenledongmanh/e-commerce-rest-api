package com.ecommerceproject.ecommercerestapi.service.impl;

import com.ecommerceproject.ecommercerestapi.exception.ECommerceAPIException;
import com.ecommerceproject.ecommercerestapi.exception.ResourceNotFoundException;
import com.ecommerceproject.ecommercerestapi.model.dto.LoginDTO;
import com.ecommerceproject.ecommercerestapi.model.dto.RegisterDTO;
import com.ecommerceproject.ecommercerestapi.model.entity.Role;
import com.ecommerceproject.ecommercerestapi.model.entity.User;
import com.ecommerceproject.ecommercerestapi.repository.IRoleRepository;
import com.ecommerceproject.ecommercerestapi.repository.IUserRepository;
import com.ecommerceproject.ecommercerestapi.security.JWTokenProvider;
import com.ecommerceproject.ecommercerestapi.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IAuthServiceImpl
        implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTokenProvider jwTokenProvider;

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);

        return jwTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        if (iUserRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        if (iUserRepository.existsByEmail(registerDTO.getEmail())) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = iRoleRepository.findByName("ROLE_USER")
                                      .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "ROLE_USER"));
        roles.add(userRole);
        user.setRoles(roles);

        iUserRepository.save(user);

        return "User register successfully!.";
    }
}
