package com.buda.excel.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public Long getUserID(LoginDTO loginDTO) {
        return this.loginRepository.getUserID(loginDTO);
    }
}
