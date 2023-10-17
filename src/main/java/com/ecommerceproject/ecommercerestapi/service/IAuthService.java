package com.ecommerceproject.ecommercerestapi.service;

import com.ecommerceproject.ecommercerestapi.model.dto.LoginDTO;
import com.ecommerceproject.ecommercerestapi.model.dto.RegisterDTO;

public interface IAuthService {
    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
