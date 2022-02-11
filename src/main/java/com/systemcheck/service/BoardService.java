package com.systemcheck.service;

import com.systemcheck.entity.BoardEntity;
import com.systemcheck.entity.FileEntity;
import com.systemcheck.repository.BoardRepository;
import com.systemcheck.repository.FileRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    public FileRepository fileRepository;
    @Autowired
    public BoardRepository boardRepository;

    public void saveBoardContents(String title, String text, String newFileName, String userId){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setUserId(userId);
        boardEntity.setTitle(title);
        boardEntity.setText(text);
        boardEntity.setFileUUID(newFileName);
        boardEntity.setDeleteYN("N");
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

    public JSONArray boardContents(){
        JSONArray jsonArray = new JSONArray();
        List<BoardEntity> list = boardRepository.findAll();
        for(int i =0; i<list.size(); i++){
            JSONObject object = new JSONObject();
            if(list.get(i).getDeleteYN().equalsIgnoreCase("N")) {
                object.put("userId", list.get(i).getUserId());
                object.put("title", list.get(i).getTitle());
                object.put("id", list.get(i).get_id());
                jsonArray.add(object);
            }
        }
        return jsonArray;
    }



}
