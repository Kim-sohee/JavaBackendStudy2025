package com.sinse.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		//컬렉션 프레임웍 중 순서가 없는 유형 중 map을 공부한다.
		Map<String, String> map = new HashMap();
		
		map.put("A1", "가나초콜릿");
		map.put("A2", "허쉬초콜릿");
		map.put("A3", "abc초콜릿");
		
		System.out.println(map.values());
		
	}
}
