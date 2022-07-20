package com.systemcheck.controller;
import com.systemcheck.entity.CoffeeOrder;
import com.systemcheck.entity.JwtRequest;
import com.systemcheck.entity.User;
import com.systemcheck.repository.NewUserRepository;
import com.systemcheck.repository.UserRepository;
import com.systemcheck.service.SystemCheckService;
import com.systemcheck.service.UserRegisterService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
public class ApiController {
    @Autowired
    public UserRepository userRepo;
    @Autowired
    public NewUserRepository repo;
    @Autowired
    private UserRegisterService service;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/notAuthorized")
    public String error() {
            return "Not Authorized Request";
    }

    @RequestMapping(value = "/api/check", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONObject systemcheck(@RequestBody Map param) throws Exception {
        JSONObject jsonObject = new JSONObject();
        SystemCheckService service = new SystemCheckService();
        if(param.values().toString().equalsIgnoreCase("[test]")){
            jsonObject.put("test", "SpringBoot test");
        }else{
            jsonObject.put("test", "SpringBoot prod");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/signup", method= {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<?> signupUser(@RequestBody JwtRequest param) throws Exception {
        JSONObject jsonObject = new JSONObject();
     if(service.checkUserId(param.getUsername())){
         service.insertUserTmp(param);
         jsonObject.put("result", "success");
     }else {
         jsonObject.put("result", "fail");
     }
        return ResponseEntity.ok(jsonObject);
    }

     @RequestMapping(value = "/userlist", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray userList(@RequestBody JwtRequest param) throws Exception {
        JSONArray jsonArray = new JSONArray();
        jsonArray = service.userList();
        return jsonArray;
    }

    @RequestMapping(value = "/userprove", method= {RequestMethod.POST, RequestMethod.GET})
    public String userProve(@RequestBody User param) throws Exception {
        JSONArray jsonArray = new JSONArray();
        service.updateUser(param.getUserId(), param.getRole());
        return "jsonArray";
    }

    @RequestMapping(value = "/coffee/order", method= {RequestMethod.POST, RequestMethod.GET})
    public JSONArray coffeeOrder(@RequestBody CoffeeOrder param) throws Exception {
        JSONArray jsonArray = new JSONArray();

        for(CoffeeOrder orders : param.getOrders()){
            jsonArray.add(param);
        }
        return jsonArray;
    }


}
