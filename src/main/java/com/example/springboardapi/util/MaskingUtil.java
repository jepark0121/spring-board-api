package com.example.springboardapi.util;

public class MaskingUtil {
    /**
     * 이름 마스킹 처리
    */
    public static String nameMasking(String name) {
        String masked = "";
        String middleName = "";

        if(name == null || name.trim().isEmpty()) {
            return "";
        }

        // 이름이 3자 이상이면
        if(name.trim().length() > 2) {
            middleName = name.substring(1, name.trim().length()-1);
        } else {
            middleName = name.substring(1);
        }

        masked = name.substring(0, 1);
        for(int i=0; i<middleName.length(); i++){
            masked += "*";
            if(i == middleName.length() - 1){
                if(name.trim().length() > 2) {
                    masked += name.substring(name.trim().length()-1, name.trim().length());
                }
            }
        }

        return masked;
    }
}
