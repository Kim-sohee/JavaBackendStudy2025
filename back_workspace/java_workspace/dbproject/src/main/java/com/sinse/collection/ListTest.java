package com.sinse.collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	
	
	public static void main(String[] args) {
		//자바의 컬렉션 프레임웍은 java.util 패키지에서 지원하며, 
		//그 중, 순서 있는 집합을 처리하는 데 대표적인 List에 대해 알아보자.
		/*List vs 배열
		 * 공통점: 순서를 가지며 인덱스로 접근 가능
		 * 차이점: 배열은 생성 시 반드시 크기를 명시해주어야 함, 기본 자료형도 담을 수 있다.
		 * 			 컬렉션의 대상은 오직 객체만을 담는다.
		 * 컬렉션 프레임웤은 최상위 인터페이스들의 메서드를 주로 사용하기 때문에 하위의 어떠한 구현 객체를 사용하더라도
		 * 메서드 사용이 일관성이 있다.*/
		//<자료형>을 명시하면, 컴파일러가 다른 자료형을 거부한다, 즉 컴파일 타임에 자료형 체크 = 제너릭 타입
		List<String> list = new ArrayList();		//크기가 고무줄 배열(js와 동일)
		list.add("banana");
		list.add("apple");
		list.add("orange");
		list.add("pineapple");
		
		//고전적 반복문
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+"\t");
		}
		
		//자바 5부터 개선된 for(improved for)
		for(Object x: list) {
			System.out.print(x);
		}
	}
}
