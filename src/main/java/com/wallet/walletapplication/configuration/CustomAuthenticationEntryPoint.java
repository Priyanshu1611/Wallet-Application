package com.wallet.walletapplication.configuration;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

 @Override
 public void commence(
     HttpServletRequest request,
     HttpServletResponse response,
     AuthenticationException authException) throws IOException {

     response.setContentType("application/json");
     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     response.getOutputStream().println("{ \"error\": \"Invalid username or password\" }");
 }
}
 
