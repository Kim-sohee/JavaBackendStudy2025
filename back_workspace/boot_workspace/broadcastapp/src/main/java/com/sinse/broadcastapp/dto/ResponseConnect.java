package com.sinse.broadcastapp.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ResponseConnect {
    private String responseType;
    private Set<String> data;
}
