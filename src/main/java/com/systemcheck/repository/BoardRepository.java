package com.systemcheck.repository;


import com.systemcheck.entity.BoardEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepository extends MongoRepository<BoardEntity, String> {

    public BoardEntity findByUserId (String userId);

}
