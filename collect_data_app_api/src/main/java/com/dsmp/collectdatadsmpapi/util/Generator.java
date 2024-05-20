package com.dsmp.collectdatadsmpapi.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {

    private final Random RANDOM = new Random();
    private final String NUMERIC = "0123456789";
    private final String NUMERIC2 = "123456789";
    public final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String CHARACTERS = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //=====================================

    public String generateGeneralId(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(new Random().nextInt(25)));
        }
        return builder.toString();
    }

    public String generateUserPassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(CHARACTERS.charAt(new Random().nextInt(9)));
        }
        return builder.toString();
    }

}
