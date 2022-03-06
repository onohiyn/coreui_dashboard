package com.systemcheck.service;

import com.systemcheck.entity.JwtRequest;
import com.systemcheck.entity.NewUserSign;
import com.systemcheck.entity.User;
import com.systemcheck.repository.NewUserRepository;
import com.systemcheck.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserRegisterService {

    @Autowired
    public NewUserRepository newUserRepository;
    @Autowired
    public UserRepository userRepository;

    public boolean checkUserId (String userId) throws UsernameNotFoundException {
        if(newUserRepository.existsByUserId(userId) || userRepository.existsByUserId(userId)){
            return false;
        }else{
            return true;
        }
    }

    public void insertUserTmp(JwtRequest request){
        String encryptedPwd = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        NewUserSign user = new NewUserSign();
        user.setUserId(request.getUsername());
        user.setPasswd(encryptedPwd);
        user.setEmail(request.getEmail());
        user.setConfirm("N");
        newUserRepository.save(user);
    }

    public JSONArray userList(){
        JSONArray jsonArray = new JSONArray();
        List<NewUserSign> list = newUserRepository.findAll();
        for(int i =0; i<list.size(); i++){
            JSONObject object = new JSONObject();
            if(list.get(i).getConfirm().equalsIgnoreCase("N")) {
                object.put("userId", list.get(i).getUserId());
                object.put("email", list.get(i).getEmail());
                jsonArray.add(object);
            }
        }
        return jsonArray;
    }

    public void updateUser(String userId, String role){
        NewUserSign user = newUserRepository.findByUserId(userId);
        User userProve = new User();
        userProve.setRole(role);
        userProve.setUserId(userId);
        userProve.setPasswd(user.getPasswd());
        user.setConfirm("Y");
        userRepository.save(userProve);
        newUserRepository.save(user);
    }
}
