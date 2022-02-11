package com.systemcheck.controller;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SyscheckController {
    @CrossOrigin(origins="*")
    @RequestMapping(value = "/check", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONObject systemcheck(@RequestParam Map map) throws Exception {
        System.out.println(map.values());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", "SUCCESS");
        System.out.println(jsonObject);
        return jsonObject;
    }

}
