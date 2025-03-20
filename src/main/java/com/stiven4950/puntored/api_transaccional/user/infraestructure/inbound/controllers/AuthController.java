package com.stiven4950.puntored.api_transaccional.user.infraestructure.inbound.controllers;

import com.stiven4950.puntored.api_transaccional.config.JwtUtil;
import com.stiven4950.puntored.api_transaccional.user.application.dto.LoginRequestDTO;
import com.stiven4950.puntored.api_transaccional.user.application.dto.ResponseLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody LoginRequestDTO loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        String jwt = this.jwtUtil.create(loginDto.getUsername());
        ResponseLogin response = ResponseLogin.builder().accessToken(jwt).build();

        return ResponseEntity.ok(response);
    }
}
