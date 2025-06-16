package com.ejercito.transferencia.domain.port;

import com.ejercito.transferencia.domain.model.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);
    void saveUser(User user);
}
