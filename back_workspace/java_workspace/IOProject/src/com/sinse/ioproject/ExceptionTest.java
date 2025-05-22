package com.sinse.ioproject;

public class ExceptionTest {
	/*java가 개발자에게 예외처리 할 것을 강요하는 체크 예외 vs 언체크 예외
	 * 공통점: 둘 다 코드로 해결할 수 있는 에러
	 * 체크예외: 중요한 것들만 강요
	 * 언체크예외: 개발자에게 맡김, 비정상 종료가 되지 않으려면 개발자가 적극 예외를 처리
	 * */
	
	public static void main(String[] args) {
		/*
		int[] arr = new int[3];	
		
		try {
			arr[0] = 1;
			arr[1] = 3;
			arr[2] = 6;
			arr[3] = 9;
		} catch (MyArrayException e) {
			System.out.print(e.getMessage());
		}
		*/
		
		//우리가 만든 예외를 일부러 일으켜 보기
		try {
			throw new ArrayIndexOutOfBoundsException("배열 관련 에러 발생");
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println("도달");
	}
}
