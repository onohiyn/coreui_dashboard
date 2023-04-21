package com.systemcheck.controller;


import com.systemcheck.entity.SystemEntity;
import com.systemcheck.service.RestService;
import com.systemcheck.service.SystemService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SyscheckController {

    @Autowired
    RestService service;
    @Autowired
    SystemService systemService;
    @CrossOrigin(origins="*")
    @RequestMapping(value = "/check", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray systemCheck(@RequestBody SystemEntity system) throws Exception {
        return service.callAlive(system.getSystem());
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/systemRegister", method= {RequestMethod.POST, RequestMethod.GET})
    public void registerSystem(@RequestBody SystemEntity system) throws Exception {
        System.out.println(system.getSystem());

        try{
            if(systemService.checkSystem(system.getUrl(), system.getSystem())){
                systemService.saveSystem(system);
            }else{
                SystemEntity entity = new SystemEntity();
                entity = systemService.getSystemBySystem(system);
                entity.setSystem(system.getSystem());
                entity.setDomain(system.getDomain());
                entity.setUrl(system.getUrl());
                entity.setMethod(system.getMethod());
                systemService.saveSystem(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/systemList", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray systemList() throws Exception {
        return systemService.systemList();
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/systemDelete", method= {RequestMethod.POST, RequestMethod.GET})
    public void systemDelete(@RequestBody SystemEntity system) throws Exception {
        systemService.deleteSystem(system);
    }

}
