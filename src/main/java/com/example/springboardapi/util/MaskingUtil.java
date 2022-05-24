package com.example.springboardapi.util;

public class MaskingUtil {
    /**
     * 이름 마스킹 처리
    */
    public static String nameMasking(String name) {
        if(name == null || name.trim().isEmpty()) {
            return "";
        }

        int startIndex = 1;
        int endIndex = 0;
        if(name.trim().length() > 2) {
            endIndex = name.trim().length() - 1;
        } else {
            endIndex = name.trim().length();
        }

        // String은 변경 불가능한 문자열을 생성하지만 StringBuilder는 변경 가능한 문자열을 만들어 주기 때문에, String을 합치는 작업 시 하나의 대안이 될 수 있다
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = name.trim().toCharArray();
        for(int i=0; i<name.trim().length(); i++) {
            if(i >= startIndex && i < endIndex) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(chars[i]);
            }
        }

        return stringBuilder.toString();
    }
}
