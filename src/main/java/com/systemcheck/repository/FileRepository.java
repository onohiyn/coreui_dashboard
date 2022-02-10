package com.systemcheck.repository;


import com.systemcheck.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileRepository extends MongoRepository<FileEntity, String> {

    List<FileEntity> findAllBy(String uuid); //UUID로 파일 검색
}
