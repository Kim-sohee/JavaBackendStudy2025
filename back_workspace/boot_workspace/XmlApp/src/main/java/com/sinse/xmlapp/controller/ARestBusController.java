package com.sinse.xmlapp.controller;

import com.sinse.xmlapp.domain.Item;
import com.sinse.xmlapp.model.bus.BusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ARestBusController {
    private BusService busService;
    public ARestBusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/buses")
    public List<Item> getList() throws Exception {
        return busService.parse();
    }
}
