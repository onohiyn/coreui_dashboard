package com.systemcheck.repository;

import com.systemcheck.entity.CoffeeOrder;
import com.systemcheck.entity.NewUserSign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderRepository extends MongoRepository<CoffeeOrder, String> {
}
