package com.sinse.xmlapp.controller;

import com.sinse.xmlapp.model.member.Bus;
import com.sinse.xmlapp.model.member.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestBusController {
    private BusService busService;

    public RestBusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/bus")
    public List<Bus> busList() throws Exception {
        return busService.parse();
    }
}
