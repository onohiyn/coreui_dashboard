package com.systemcheck.repository;


import com.systemcheck.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserId(String userId);
    public boolean existsByUserId (String userId);

}
