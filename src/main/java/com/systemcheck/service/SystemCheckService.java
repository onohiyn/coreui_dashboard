package com.systemcheck.service;
import com.systemcheck.util.ScriptRunner;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;

@Service
public class SystemCheckService {
    ScriptRunner shRunner = new ScriptRunner();
    public String name(String type) throws IOException, InterruptedException {
        try{
            String cmd = "";
            if(type.equalsIgnoreCase("prod")){
                cmd = "sh /Users/mhw/Script/process_check.sh";
            }
            if(type.equalsIgnoreCase("dev")){
                cmd = "sh /Users/mhw/Script/process_check_test.sh";
            }
            String[] callCmd = {"/bin/bash", "-c", cmd};
            Map map = shRunner.execCommand(callCmd);

            Object result = map.get(1);
            String convertString = result.toString().replace("\n" , "<br>")
                    .replace("[32;1m","<font color = \"black\">")
                    .replace("[35;1m","")
                    .replace("[39;1m","")
                    .replace("[0m","</font>")
                    .replace("[1;31m","")
                    .replace("[5;31m","<font size=\"3em\" color=\"red\">\n");

            System.out.println(convertString);
            return convertString;

        } catch (Exception e){
            String tmp = "";
            System.out.println(e.getStackTrace());
            return tmp;
        }
    }

    }

