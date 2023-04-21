package com.systemcheck.repository;

import com.systemcheck.entity.NewUserSign;
import com.systemcheck.entity.SystemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemRepository extends MongoRepository<SystemEntity, String> {
    public List<SystemEntity> findAll();
    public boolean existsBySystem(String system);
    public SystemEntity findBySystem(String system);
    public boolean existsByUrl(String url);
    public void deleteBySystem(String system);
}
