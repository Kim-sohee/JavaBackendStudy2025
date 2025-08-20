package com.sinse.xmlapp.model.bus;

import com.sinse.xmlapp.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    BusParser   busParser;
    public BusService(BusParser busParser) {
        this.busParser = busParser;
    }

    public List<Item> parse() throws Exception{
        return busParser.parse();
    }
}

