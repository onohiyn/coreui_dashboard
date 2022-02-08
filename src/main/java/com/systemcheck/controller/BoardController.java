package com.systemcheck.controller;


import com.systemcheck.entity.BoardEntity;
import com.systemcheck.repository.BoardRepository;
import com.systemcheck.repository.FileRepository;
import com.systemcheck.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class BoardController {
    @Autowired
    public UserRepository userRepo;
    @Autowired
    public BoardRepository boardRepository;
    @Autowired
    public FileRepository fileRepository;

    @RequestMapping(value = "/boardWrite", method= {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<?> writeNew(@RequestBody BoardEntity param) throws Exception {
        JSONObject jsonObject = new JSONObject();
        System.out.println(param.getTitle());
        System.out.println(param.getText());

        return ResponseEntity.ok(jsonObject);
    }







}
