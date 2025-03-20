package com.stiven4950.puntored.api_transaccional.user.application.create;

import com.stiven4950.puntored.api_transaccional.user.application.dto.ResponseDTO;
import com.stiven4950.puntored.api_transaccional.user.domain.model.User;
import com.stiven4950.puntored.api_transaccional.user.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandUseCase {
    private final UserCommandRepository userCommandRepository;

    public ResponseDTO createUser(User user) {
        return this.userCommandRepository.createUser(user);
    }
}
