package com.ejercito.transferencia.application;

import com.ejercito.transferencia.application.dto.Encrypte;
import com.ejercito.transferencia.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Encrypte> getUserById(Long id) throws Exception;

    void createUser(User user);
}
