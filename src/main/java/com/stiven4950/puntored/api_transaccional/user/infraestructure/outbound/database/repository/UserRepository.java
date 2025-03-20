package com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.repository;

import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByEmail(String email);
}
