package com.systemcheck.service;

import com.systemcheck.entity.NewUserSign;
import com.systemcheck.entity.SystemEntity;
import com.systemcheck.repository.SystemRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {
    @Autowired
    SystemRepository systemRepository;

    public boolean checkSystem (String url, String system) throws Exception {
        if(systemRepository.existsByUrl(url) || systemRepository.existsBySystem(system)){
            return false;
        }else{
            return true;
        }
    }
    public void saveSystem(SystemEntity entity){
        systemRepository.save(entity);
    }

    public SystemEntity getSystemBySystem(SystemEntity entity){
        SystemEntity returnEntity = systemRepository.findBySystem(entity.getSystem());
        return returnEntity;
    }

    public JSONArray systemList(){
        JSONArray jsonArray = new JSONArray();
        List<SystemEntity> list = systemRepository.findAll();
        for(int i =0; i<list.size(); i++){
            JSONObject object = new JSONObject();
                object.put("system", list.get(i).getSystem());
                object.put("url", list.get(i).getUrl());
                object.put("domain", list.get(i).getDomain());
                object.put("method", list.get(i).getMethod());
                jsonArray.add(object);
        }
        return jsonArray;
    }

    public void deleteSystem(SystemEntity entity){
        systemRepository.deleteBySystem(entity.getSystem());
    }
}
