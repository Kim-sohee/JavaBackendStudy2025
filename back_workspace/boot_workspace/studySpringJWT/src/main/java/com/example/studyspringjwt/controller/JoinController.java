package com.example.studyspringjwt.controller;

import com.example.studyspringjwt.dto.JoinDTO;
import com.example.studyspringjwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return "ok";
    }
}
