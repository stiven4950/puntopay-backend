package com.stiven4950.puntored.api_transaccional.user.infraestructure;

import com.stiven4950.puntored.api_transaccional.user.application.dto.ResponseDTO;
import com.stiven4950.puntored.api_transaccional.user.domain.model.User;
import com.stiven4950.puntored.api_transaccional.user.domain.repository.UserCommandRepository;
import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity.UserEntity;
import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity.UserRoleEntity;
import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCommandRepositoryImpl implements UserCommandRepository {
    private final UserRepository userRepository;

    @Override
    public ResponseDTO createUser(User user) {
        if (userRepository.findFirstByEmail(user.getEmail()).isPresent()) {
            return ResponseDTO.builder()
                    .state(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("El correo ya está registrado")
                    .build();
        }

        try {
            UserEntity userEntity = UserEntity.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .password(user.getPassword())
                    .document(user.getDocument())
                    .roles(List.of(
                            UserRoleEntity.builder()
                                    .role("ADMIN")
                                    .grantedDate(LocalDateTime.now())
                                    .build()
                    ))
                    .disabled(false)
                    .locked(false)
                    .build();

            this.userRepository.save(userEntity);

            return ResponseDTO.builder()
                    .state(true)
                    .code(HttpStatus.CREATED.value())
                    .message("Usuario creado exitosamente")
                    .build();

        } catch (Exception e) {
            return ResponseDTO.builder()
                    .state(false)
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Error al crear usuario: " + e.getMessage())
                    .build();
        }
    }

}
