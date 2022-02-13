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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
            MultipartFile requestedFile = null;
            try{
                requestedFile = file;
            }catch(Exception e){
                System.out.println("No file found");
            }
            JSONObject jsonObject = new JSONObject();
            FileEntity fileEntity = new FileEntity();
            String FILEUUID = UUID.randomUUID().toString();
            String newFileName = "";
            if(requestedFile != null) {
                newFileName = FILEUUID + file.getOriginalFilename(); // include uuid to prevent overlap
                fileEntity.setFileName(file.getOriginalFilename());
                fileEntity.setFileSize(file.getSize());
                fileEntity.setContentType(file.getContentType());
                fileEntity.setUuid(newFileName);
                File saveFile = new File(fileLocation + newFileName);
                file.transferTo(saveFile); // save file
                // save file information
                boardService.saveFileDetail(fileEntity);
            }
            // save board contents
            boardService.saveBoardContents(param.getTitle(), param.getText(), newFileName, param.getUserId());
        return ResponseEntity.ok(jsonObject);
    }

    @RequestMapping(value = "/boardcontents", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray userList(@RequestBody JwtRequest param) throws Exception {
        JSONArray jsonArray = new JSONArray();
        jsonArray = boardService.boardContents();
        return jsonArray;
    }

    @RequestMapping(value = "/boarddetail", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONObject userList(@RequestBody BoardEntity param) throws Exception {
        JSONObject object = new JSONObject();
        object = boardService.boardDetail(param.get_id());
        return object;
    }


    @CrossOrigin(value = {"*"})
    @RequestMapping(value = "/filedownload", method= {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<?> fileDownload (@RequestBody FileEntity param) throws Exception {
        String fileFullPath = fileLocation + param.getUuid();
        try{
            Path filePath = Paths.get(fileFullPath);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath));

            File file = new File(fileFullPath);
            HttpHeaders headers = new HttpHeaders();
            String filename = file.getName();
            filename = UriUtils.encode(filename, StandardCharsets.UTF_8); // 한글 깨짐 현상 - 진행중

            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
            headers.setAccessControlExposeHeaders(Arrays.asList("Content-Disposition")); // to axios can accept content-disposition header
            headers.setAccessControlAllowHeaders(Arrays.asList("Content-Disposition"));
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM)); // 다운로드 파일 깨짐 현상
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
