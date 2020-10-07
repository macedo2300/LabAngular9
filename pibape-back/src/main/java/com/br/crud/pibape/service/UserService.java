package com.br.crud.pibape.service;

import com.br.crud.pibape.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByEmail(String email);

    User createOrUpdate(User user);

    Optional<User> findById(String id);

    void delete(String id);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);
}
