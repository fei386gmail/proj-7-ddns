package com.example.dictionary.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.exec.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String.*;
import org.apache.commons.*;
import top.jfunc.common.utils.StrUtil;

@Component
public class DetectIP {

    @PostConstruct
    public void getIP()
    {
        System.out.println(DetectIP.getV4IP());
    }


    /**
     *@Function
     *@Description 得到本机外网ip
     **/
    public static String getV4IP() {
        ProcessBuilder process;
        Process p;
        StringBuilder builder;
        String ip = "";

        try {
            String[] cmds = {"curl","https://www.lddgo.net/api/GetIp"};
            process = new ProcessBuilder(cmds);
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            System.out.println(builder);
            Map map=json2Map(builder.toString());
            ip=(String)((Map)map.get("data")).get("ip");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }


    //由于返回的数据是json对象，需要转成map后取值即可
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 格式化json返回Map<String, Object>
     *
     * @return
     */
    public static Map<String, Object> json2Map(String json){
        Map map = Maps.newHashMap();

        try {
            map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }


}
