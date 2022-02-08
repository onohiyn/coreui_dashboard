package com.systemcheck.repository;


import com.systemcheck.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends MongoRepository<FileEntity, String> {

}
