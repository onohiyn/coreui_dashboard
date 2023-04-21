package com.systemcheck.controller;

import com.systemcheck.dto.ChatDto;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@RequiredArgsConstructor
public class ChatController implements ApplicationListener<SessionConnectedEvent> {
    HashMap<String,String> users = new HashMap<>();

    private final SimpMessageSendingOperations sendingOperations;


    @MessageMapping("/chat")
    public void chat(ChatDto message, SimpMessageHeaderAccessor headerAccessor){
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        String formatedNow = now.format(formatter);

        message.setDate(formatedNow);

        sendingOperations.convertAndSend("/topic/reply" , message);
    }

    @MessageMapping("/join")
    public void join (ChatDto message, SimpMessageHeaderAccessor headerAccessor){
        try{
            String userId = message.getUserName();
            String sessionId = headerAccessor.getSessionId();
            if(!users.containsValue(userId)){
                message.setMessage(userId+"님이 입장하셨습니다~!");
                LocalTime now = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
                String formatedNow = now.format(formatter);
                message.setDate(formatedNow);

                sendingOperations.convertAndSend("/topic/reply" , message);
                users.put(sessionId, userId);
            }
            HashSet<String> userList = new HashSet<>();
            if(!userId.equalsIgnoreCase(null)){
                for(String key : users.keySet()){
                    userList.add(users.get(key));
                }
            }
            sendingOperations.convertAndSend("/topic/joiner" , userList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @MessageMapping("/bye")
    public void bye (ChatDto message, SimpMessageHeaderAccessor headerAccessor){
        String userId = message.getUserName();
        String sessionId = headerAccessor.getSessionId();
        if(users.containsKey(sessionId)){
            System.out.println("remove session :" + sessionId);
            users.remove(sessionId);
        }
        HashSet<String> userList = new HashSet<>();
        for(String key : users.keySet()){
            userList.add(users.get(key));
        }
        sendingOperations.convertAndSend("/topic/joiner" , userList);
    }
    public void removeSession (String sessionId){
        if(users.containsKey(sessionId)){
            users.remove(sessionId);
        }
        HashSet<String> userList = new HashSet<>();
        for(String key : users.keySet()){
            userList.add(users.get(key));
        }
        sendingOperations.convertAndSend("/topic/joiner" , userList);
    }
    @CrossOrigin(origins="*")
    @RequestMapping(value = "/byebye", method= {RequestMethod.POST, RequestMethod.GET})
    public void out (@RequestBody ChatDto param) throws Exception {
        String userId = param.getUserName();
        HashSet<String> userList = new HashSet<>();
        if(!userId.equalsIgnoreCase(null)){
            for(String key : users.keySet()){
                userList.add(users.get(key));
            }
        }
        sendingOperations.convertAndSend("/topic/joiner" , userList);
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        System.out.println("Session Connected  >>>>>>>>>>> " + event.getMessage());
    }


}