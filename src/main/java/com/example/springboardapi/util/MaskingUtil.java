package com.example.springboardapi.util;

public class MaskingUtil {
    public static String masked(String name) {
        String masked = "";
        String middleName = "";

        // 이름이 3자 이상이면
        if(name.length() > 2) {
            middleName = name.substring(1, name.length()-1);
            System.out.println("middleName {}" + middleName);
        } else {
            middleName = name.substring(1);
        }

        String star = "";
        for(int i=0; i<middleName.length(); i++){
            star += "*";
        }

        if(name.length() > 2) {
            masked = name.substring(0, 1) + star + name.substring(name.length()-1, name.length());
        } else {
            masked = name.substring(0, 1) + star;
        }

        return masked;
    }
}
