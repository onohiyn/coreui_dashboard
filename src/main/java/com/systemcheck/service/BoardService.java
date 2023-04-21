package com.systemcheck.service;

import com.systemcheck.entity.BoardEntity;
import com.systemcheck.entity.FileEntity;
import com.systemcheck.repository.BoardRepository;
import com.systemcheck.repository.FileRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    public FileRepository fileRepository;
    @Autowired
    public BoardRepository boardRepository;

    public void saveBoardContents(String title, String text, String newFileName, String userId){
        BoardEntity boardEntity = new BoardEntity();
        // find current date and time to further to find contents by date and time
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("YYYYMMdd");
        String formatedTime = localTime.format(formatterTime);
        String formatedDate = localDate.format(formatterDate);
        boardEntity.setUserId(userId);
        boardEntity.setTitle(title);
        boardEntity.setText(text);
        boardEntity.setFileUUID(newFileName);
        boardEntity.setDeleteYN("N");
        boardEntity.setDate(formatedDate);
        boardEntity.setTime(formatedTime);
        boardRepository.save(boardEntity);
    }
    public void saveFileDetail(FileEntity file){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileSize(file.getFileSize());
        fileEntity.setFileName(file.getFileName());
        fileEntity.setFileSize(file.getFileSize());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setUuid(file.getUuid());
        fileRepository.save(fileEntity);
    }

    public void deleteContents(String id){
        BoardEntity entity = new BoardEntity();
        entity = boardRepository.findBy_id(id);
        entity.setDeleteYN("Y");
        boardRepository.save(entity);
    }

    public JSONArray boardContents(){
        JSONArray jsonArray = new JSONArray();
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        List<BoardEntity> list = boardRepository.findAll(sort);
        for(int i =0; i<list.size(); i++){
            JSONObject object = new JSONObject();
            if(list.get(i).getDeleteYN().equalsIgnoreCase("N")) {
                object.put("userId", list.get(i).getUserId());
                object.put("title", list.get(i).getTitle());
                object.put("id", list.get(i).get_id());
                object.put("date", list.get(i).getDate());
                jsonArray.add(object);
            }
        }
        return jsonArray;
    }

    public JSONObject boardDetail(String contentId){
        JSONObject object = new JSONObject();
        BoardEntity entity = new BoardEntity();
        FileEntity fileEntity = new FileEntity();
        entity = boardRepository.findBy_id(contentId);
        if(!entity.getFileUUID().isEmpty()) {
            fileEntity = fileRepository.findByUuid(entity.getFileUUID());
            object.put("filename", fileEntity.getFileName()); //original file name
            object.put("fileuuid", entity.getFileUUID());
        }else{
            object.put("filename", "파일없음");
        }
        object.put("title", entity.getTitle());
        object.put("date", entity.getDate());
        object.put("text", entity.getText());
        return object;
    }


}
