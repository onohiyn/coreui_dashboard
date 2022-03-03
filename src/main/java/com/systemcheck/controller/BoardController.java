package com.systemcheck.controller;

import com.systemcheck.entity.BoardEntity;
import com.systemcheck.entity.FileEntity;
import com.systemcheck.entity.JwtRequest;
import com.systemcheck.repository.BoardRepository;
import com.systemcheck.repository.FileRepository;
import com.systemcheck.repository.UserRepository;
import com.systemcheck.service.BoardService;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @RequestMapping(value = "/filedown", method= {RequestMethod.POST, RequestMethod.GET}, produces = "application/octet-stream; charset=UTF-8")
    public void fileDown (@RequestBody FileEntity param, HttpServletResponse response) throws Exception {
        String fileFullPath = fileLocation + param.getUuid();
        try{
            Path filePath = Paths.get(fileFullPath);
            FileEntity fileEntity = fileRepository.findByUuid(param.getUuid());
            response.setContentType("application/octet-stream"); //IOS image file download error
            response.setHeader("Content-Disposition", "attachment; fileName=" + URLEncoder.encode(fileEntity.getFileName(),"UTF-8"));
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader( "Access-Control-Expose-Headers","Content-Disposition");
            byte[] fileByte = FileUtils.readFileToByteArray(new File(fileFullPath));

            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch(Exception e){

        }
    }
}
