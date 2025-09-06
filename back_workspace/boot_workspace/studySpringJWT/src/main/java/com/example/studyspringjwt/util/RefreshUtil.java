package com.example.studyspringjwt.util;

import com.example.studyspringjwt.entity.RefreshEntity;

import java.util.Date;

public class RefreshUtil {

    public static RefreshEntity addRefreshEntity(String username, String refresh, Long expiredMs){
        Date date = new Date(System.currentTimeMillis()+expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        return refreshEntity;
    }
}
