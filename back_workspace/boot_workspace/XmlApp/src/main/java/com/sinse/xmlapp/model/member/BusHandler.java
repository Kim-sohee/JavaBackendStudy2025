package com.sinse.xmlapp.model.member;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BusHandler extends DefaultHandler {
    @Getter
    List<Bus> busList;
    Bus bus;

    private boolean isBstopid = false;
    private boolean isBstopnm = false;
    private boolean isArsno = false;
    private boolean isGpsx = false;
    private boolean isGpsy = false;
    private boolean isStopstype = false;

    //문서가 시작될 때
    public void startDocument() throws SAXException {
        busList = new ArrayList<Bus>();
    }

    //시작 태그를 만났을 때
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<"+tag+">");
        if(tag.equals("item")) {
            bus = new Bus();
        }else if(tag.equals("bstopid")) {
            isBstopid = true;
        }else if(tag.equals("bstopnm")) {
            isBstopnm = true;
        }else if(tag.equals("arsno")) {
            isArsno = true;
        }else if(tag.equals("gpsx")) {
            isGpsx = true;
        }else if(tag.equals("gpsy")) {
            isGpsy = true;
        }else if(tag.equals("stoptype")) {
            isStopstype = true;
        }
    }

    //태그와 태그 사이 컨텐츠
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        log.debug(content);

        if(isBstopid) bus.setBstopid(Integer.parseInt(content));
        if(isBstopnm) bus.setBstopnm(content);
        if(isArsno) bus.setArsno(Integer.parseInt(content));
        if(isGpsx) bus.setGpsx(content);
        if(isGpsy) bus.setGpsy(content);
        if(isStopstype) bus.setStoptype(content);
    }

    //종료 태그
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</"+tag+">");
        if(tag.equals("item")) {
            busList.add(bus);
        }else if(tag.equals("bstopid")) {
            isBstopid = false;
        }else if(tag.equals("bstopnm")) {
            isBstopnm = false;
        }else if(tag.equals("arsno")) {
            isArsno = false;
        }else if(tag.equals("gpsx")) {
            isGpsx = false;
        }else if(tag.equals("gpsy")) {
            isGpsy = false;
        }else if(tag.equals("stoptype")) {
            isStopstype = false;
        }
    }

    public void endDocument() throws SAXException {
        log.debug("문서 파싱 후 담긴 데이터는 "+busList.size());
    }
}
