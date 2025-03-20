package com.stiven4950.puntored.api_transaccional.user.infraestructure.inbound.controllers;

import com.stiven4950.puntored.api_transaccional.user.application.create.UserCommandUseCase;
import com.stiven4950.puntored.api_transaccional.user.application.dto.ResponseDTO;
import com.stiven4950.puntored.api_transaccional.user.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserCommandUseCase userCommandUseCase;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/registration")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody User user){
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        ResponseDTO response = this.userCommandUseCase.createUser(user);

        return ResponseEntity.status(response.getCode()).body(response);
    }
}
