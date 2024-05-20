package com.dsmp.collectdatadsmpapi.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ImageUploadGenerator {
    public final static String CHARACTORS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    //=====================================

    public String generateDevelopersStackResourceName(String name,String type, int length){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(CHARACTORS.charAt(new Random().nextInt(35)));
        }
        builder.append("-DS-");
        builder.append(type+"-");
        builder.append(name);
        return builder.toString();
    }

}
