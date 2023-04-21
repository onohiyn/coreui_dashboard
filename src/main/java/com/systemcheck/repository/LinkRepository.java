package com.systemcheck.repository;

import com.systemcheck.entity.LinkEntity;
import com.systemcheck.entity.SystemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends MongoRepository<LinkEntity, String> {
    public List<LinkEntity> findAll();
    public boolean existsBySystem(String system);
    public LinkEntity findBySystem(String system);
    public boolean existsByUrl(String url);
    public void deleteBySystem(String system);
}
