package com.systemcheck.repository;


import com.systemcheck.entity.NewUserSign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewUserRepository extends MongoRepository<NewUserSign, String> {

    public NewUserSign findByUserId (String userId);

    public boolean existsByUserId (String userId);

}
