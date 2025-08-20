package com.sinse.xmlapp.model.bus;

import com.sinse.xmlapp.domain.Item;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

//XML 파싱을 위한 클래스
@Component
public class BusHandler extends DefaultHandler {
    @Getter
    List<Item> itemList;
    Item item;

    boolean bstopid = false;
    boolean bstopnm = false;
    boolean arsno = false;
    boolean gpsx = false;
    boolean gpsy = false;
    boolean stoptype = false;

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        if(tag.equals("items")) {
            itemList = new ArrayList<Item>();
        }else if(tag.equals("item")) {
            item = new Item();
        }else if(tag.equals("bstopid")) {
            bstopid = true;
        }else if(tag.equals("bstopnm")) {
            bstopnm = true;
        }else if(tag.equals("arsno")) {
            arsno = true;
        }else if(tag.equals("gpsx")) {
            gpsx = true;
        }else if(tag.equals("gpsy")) {
            gpsy = true;
        }else if(tag.equals("stoptype")) {
            stoptype = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        if(bstopid) item.setBstopid(Integer.parseInt(content));
        if(bstopnm) item.setBstopnm(content);
        if(arsno) item.setArsno(content);
        if(gpsx) item.setGpsx(Double.parseDouble(content));
        if(gpsy) item.setGpsy(Double.parseDouble(content));
        if(stoptype) item.setStoptype(content);
    }

    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        if(tag.equals("item")) {
            itemList.add(item);
        }else if(tag.equals("bstopid")) {
            bstopid = false;
        }else if(tag.equals("bstopnm")) {
            bstopnm = false;
        }else if(tag.equals("arsno")) {
            arsno = false;
        }else if(tag.equals("gpsx")) {
            gpsx = false;
        }else if(tag.equals("gpsy")) {
            gpsy = false;
        }else if(tag.equals("stoptype")) {
            stoptype = false;
        }
    }
}
