package com.br.crud.pibape.repositoty;

import com.br.crud.pibape.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,String> {

    User findByEmail(String email);
}
