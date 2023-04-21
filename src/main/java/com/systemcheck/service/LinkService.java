package com.systemcheck.service;

import com.systemcheck.entity.LinkEntity;
import com.systemcheck.entity.SystemEntity;
import com.systemcheck.repository.LinkRepository;
import com.systemcheck.repository.SystemRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public boolean checkSystem (String url, String system) throws Exception {
        if(linkRepository.existsByUrl(url) || linkRepository.existsBySystem(system)){
            return false;
        }else{
            return true;
        }
    }
    public void saveSystem(LinkEntity entity){
        linkRepository.save(entity);
    }

    public LinkEntity getSystemBySystem(LinkEntity entity){
        LinkEntity returnEntity = linkRepository.findBySystem(entity.getSystem());
        return returnEntity;
    }

    public JSONArray linkList(){
        JSONArray jsonArray = new JSONArray();
        List<LinkEntity> list = linkRepository.findAll();
        for(int i =0; i<list.size(); i++){
            JSONObject object = new JSONObject();
                object.put("system", list.get(i).getSystem());
                object.put("url", list.get(i).getUrl());
                object.put("domain", list.get(i).getDomain());
                jsonArray.add(object);
        }
        return jsonArray;
    }

    public void deleteSystem(SystemEntity entity){
        linkRepository.deleteBySystem(entity.getSystem());
    }
}
