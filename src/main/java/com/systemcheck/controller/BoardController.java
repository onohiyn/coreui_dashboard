package com.systemcheck.controller;


import com.systemcheck.entity.BoardEntity;
import com.systemcheck.entity.FileEntity;
import com.systemcheck.entity.JwtRequest;
import com.systemcheck.repository.BoardRepository;
import com.systemcheck.repository.FileRepository;
import com.systemcheck.repository.UserRepository;
import com.systemcheck.service.BoardService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
public class BoardController {
    @Autowired
    public UserRepository userRepo;
    @Autowired
    public BoardRepository boardRepository;
    @Autowired
    public FileRepository fileRepository;
    @Autowired
    private BoardService boardService;

    @Value("${file.location}")
    private String fileLocation;

    @PostMapping(consumes = {"multipart/form-data"})
    @RequestMapping(value = "/boardWrite", method= {RequestMethod.POST, RequestMethod.GET},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<?> writeNew(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart(value = "board", required = false) BoardEntity param) throws IOException {

            JSONObject jsonObject = new JSONObject();
            FileEntity fileEntity = new FileEntity();

            String FILEUUID = UUID.randomUUID().toString();
            String newFileName = FILEUUID + file.getOriginalFilename(); // include uuid to prevent overlap
            fileEntity.setFileName(newFileName);
            fileEntity.setFileSize(file.getSize());
            fileEntity.setContentType(file.getContentType());
            fileEntity.setUuid(FILEUUID);
            File saveFile = new File(fileLocation + newFileName);
            file.transferTo(saveFile); // save file

            // save board contents
            boardService.saveBoardContents(param.getTitle(), param.getText(), newFileName, param.getUserId());

            // save file information
            boardService.saveFileDetail(fileEntity);

        System.out.println(param.getTitle() + "saved");
        System.out.println(file.getOriginalFilename() + "saved successfully");
        return ResponseEntity.ok(jsonObject);
    }

    @RequestMapping(value = "/boardcontents", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray userList(@RequestBody JwtRequest param) throws Exception {
        JSONArray jsonArray = new JSONArray();
        jsonArray = boardService.boardContents();
        return jsonArray;
    }







}
