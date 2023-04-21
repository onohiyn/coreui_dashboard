package com.systemcheck.service;

import com.systemcheck.entity.SystemEntity;
import com.systemcheck.repository.SystemRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {
    @Autowired
    SystemRepository repository;
    @Autowired
    WebClient webClient;

    public SystemEntity callAliveGet(String url) throws Exception{
        SystemEntity entity = new SystemEntity();
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            WebClient.ResponseSpec response = webClient.get()
                    .uri(url)
                    .retrieve();
            entity.setState(response.toBodilessEntity()
                    .block()
                    .getStatusCode()
                    .toString());
            stopWatch.stop();
            entity.setMeanTime(Math.round(stopWatch.getTotalTimeSeconds() * 100)/100.0);

            webClient.get().uri(url).retrieve()
                    .bodyToMono(String.class)
                    .elapsed()  // map the stream's time into our streams data
                    .doOnNext(tuple -> System.out.println(("Milliseconds for retrieving {}" + tuple.getT1())));
            // after outputting the measurement, return the data only
        } catch (Exception e){
            e.printStackTrace();
            entity.setState("호출 에러");
            entity.setMeanTime(000);
        }

        return entity;
    }

    public SystemEntity callAlivePost(String url) throws Exception{

        SystemEntity entity = new SystemEntity();
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            WebClient.ResponseSpec response = webClient.post()
                    .uri(url)
                    .retrieve();
            entity.setState(response.toBodilessEntity()
                    .block()
                    .getStatusCode()
                    .toString());
            stopWatch.stop();
            entity.setMeanTime(Math.round(stopWatch.getTotalTimeSeconds() * 100)/100.0);

        } catch (Exception e){
            e.printStackTrace();
            entity.setState("호출 에러");
            entity.setMeanTime(000);
        }
        return entity;
    }

    public JSONArray callAlive(String system) throws Exception{
        JSONArray jsonArray = new JSONArray();
        List<SystemEntity> list = repository.findAll();

        for(int i=0; i<list.size(); i++){
            JSONObject object = new JSONObject();
            if(list.get(i).getMethod().equalsIgnoreCase("get") && list.get(i).getDomain().equalsIgnoreCase(system)) {
                    SystemEntity entity = callAliveGet(list.get(i).getUrl());
                    object.put("status", entity.getState());
                    object.put("meantime", entity.getMeanTime());
                    object.put("system", list.get(i).getSystem());
                    jsonArray.add(object);

            } if(list.get(i).getMethod().equalsIgnoreCase("post") && list.get(i).getDomain().equalsIgnoreCase(system)){
                SystemEntity entity = callAlivePost(list.get(i).getUrl());
                object.put("status", entity.getState());
                object.put("meantime", entity.getMeanTime());
                object.put("system", list.get(i).getSystem());
                jsonArray.add(object);
            }
        }
    return jsonArray;
    }

}
