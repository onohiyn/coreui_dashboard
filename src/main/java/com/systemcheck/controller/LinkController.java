package com.systemcheck.controller;


import com.systemcheck.entity.LinkEntity;
import com.systemcheck.entity.SystemEntity;
import com.systemcheck.service.LinkService;
import com.systemcheck.service.RestService;
import com.systemcheck.service.SystemService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    @Autowired
    LinkService service;
    @CrossOrigin(origins="*")
    @RequestMapping(value = "/linkRegister", method= {RequestMethod.POST, RequestMethod.GET})
    public void registerLink(@RequestBody LinkEntity system) throws Exception {

        try{
            if(service.checkSystem(system.getUrl(), system.getSystem())){
                service.saveSystem(system);
            }else{
                LinkEntity entity = new LinkEntity();
                entity = service.getSystemBySystem(system);
                entity.setSystem(system.getSystem());
                entity.setDomain(system.getDomain());
                entity.setUrl(system.getUrl());
                service.saveSystem(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/linkList", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray linkList() throws Exception {
        return service.linkList();
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/linkDelete", method= {RequestMethod.POST, RequestMethod.GET})
    public void linkDelete(@RequestBody SystemEntity system) throws Exception {
        service.deleteSystem(system);
    }

}
