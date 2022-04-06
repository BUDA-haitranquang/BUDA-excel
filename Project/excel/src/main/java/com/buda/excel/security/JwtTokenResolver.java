package com.buda.excel.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenResolver {
    public Long getUserIDFromToken(HttpServletRequest httpServletRequest){
        return Long.valueOf(2);
    }
}
