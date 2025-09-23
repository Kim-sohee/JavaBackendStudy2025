package com.sinse.memberservice.util;

import java.util.Map;

//Provider에서 제공해주는 속성값들이 json 문자열에 들어있으므로, 원하는 key 와 value만 추출
public class UserInfoExtractor {
    /*---------------------------------------------------
    *   고유 ID 추출
    * --------------------------------------------------*/
    public static String getProviderId(String regId, Map<String, Object> attr) {
        //Provider 업체마다 key 값이 틀리므로 조건을 부여하기 위함
        String providerId = null;
        if(regId.equals("google")){
            providerId = (String) attr.get("sub");
        }else if(regId.equals("kakao")){
            providerId = String.valueOf(attr.get("id"));
        }else if(regId.equals("naver")){
            Map<String, Object> resp = (Map<String, Object>) attr.get("response");
            providerId = (String)resp.get("id");
        }
        return providerId;
    }

    /*---------------------------------------------------
    *   Email 추츨
    * --------------------------------------------------*/
    public static String getEmail(String regId, Map<String, Object> attr) {
        String email = null;
        if(regId.equals("google")){
            email = (String) attr.get("email");
        }else if(regId.equals("kakao")){
            Map<String, Object> account=(Map<String, Object>)attr.get("kakao_account");
            email = (String) account.get("email");
        }else if(regId.equals("naver")){
            Map<String, Object> account = (Map<String, Object>)attr.get("response");
            email = (String)account.get("email");
        }

        return email;
    }

    /*---------------------------------------------------
    *   name 추출(프로바이더마다 실명, 닉네임) 둘 중 지원하는 것을 사용함
    * --------------------------------------------------*/
    public static String getName(String regId, Map<String, Object> attr) {
        String name = null;
        if(regId.equals("google")){
            name = (String) attr.get("name");
        }else if(regId.equals("kakao")){
            Map<String, Object> account = (Map<String, Object>)attr.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) account.get("profile");
            name = (String) profile.get("nickname");

        }else if(regId.equals("naver")){
            Map<String, Object> response=(Map<String, Object>)attr.get("response");
            Object n = response.get("name");    //실명을 주지 않을 수도 있음...
            name = (n!=null)? n.toString(): (String) response.get("nickname");
        }
        return name;
    }
}
