package com.ejercito.transferencia.infrastructure.adaptador;

import com.ejercito.transferencia.domain.model.User;
import com.ejercito.transferencia.domain.port.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long>, UserRepository {

    @Override
    Optional<User> findById(Long id);

    default void saveUser(User user) {
        save(user);
    }

}
