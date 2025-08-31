package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.StoreDTO;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.exception.StoreNotFoundException;
import com.sinse.electroshop.model.store.StoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    //로그인 폼 요청 처리
    @GetMapping("/store/loginform")
    public String loginform(){
        return "store/login";
    }

    //상점의 로그인 요청 처리
    @PostMapping("/store/login")
    @ResponseBody
    public ResponseEntity<String> login(StoreDTO storeDTO, HttpSession session) {
        log.debug("login storeDTO {}", storeDTO);

        //파라미터가 담겨있는 객체는 DTO 이므로, Model 객체인 Store로 옮기자
        Store store = new Store();
        store.setBusinessId(storeDTO.getId());
        store.setPassword(storeDTO.getPwd());
        store.setStoreName(storeDTO.getStoreName());

//        storeService.regist(store);
        Store obj = storeService.login(store);

        //세션에 담기
        session.setAttribute("store", obj);

        return ResponseEntity.ok("success");
    }

    //상점 관리자 메인 요청
    @GetMapping("/store/main")
    public String main(){
        return "store/index";
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<String> handleException(StoreNotFoundException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
