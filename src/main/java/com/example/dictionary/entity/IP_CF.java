package com.example.dictionary.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class IP_CF {
    private String ip;

    IP_CF()
    {
        ip="";
    }

}
