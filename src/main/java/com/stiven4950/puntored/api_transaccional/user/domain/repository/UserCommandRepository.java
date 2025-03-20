package com.stiven4950.puntored.api_transaccional.user.domain.repository;


import com.stiven4950.puntored.api_transaccional.user.application.dto.ResponseDTO;
import com.stiven4950.puntored.api_transaccional.user.domain.model.User;

public interface UserCommandRepository {
    ResponseDTO createUser(User user);
}
