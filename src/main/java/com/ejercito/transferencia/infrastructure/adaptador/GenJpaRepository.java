package com.ejercito.transferencia.infrastructure.adaptador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface GenJpaRepository<T, TID extends Serializable> extends
        JpaRepository<T, TID> {

    List<T> findByActivo(boolean activo);

    Page<T> findByActivo(boolean activo, Pageable pageable);
}
