package com.sinse.shop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sinse.shop.common.config.Config;

// 이미지와 관련된 공통 기능을 제공해주는 유틸 클래스
public class ImageUtil {
	//클래스 패스로 부터 이미지를 반환해주는 메서드
	public Image getImage(String filename, int width, int height) {
		//패키지 경로로 부터 이미지 얻어오기(URL 방식으로 얻어와야 함)
		URL url = this.getClass().getClassLoader().getResource(filename);
		Image image=null;
		try {
			//Toolkit은 이미지를 구성하는 바이트 정보에 접근 불가하므로 BufferedImage 객체를 이용한다.(훨씬 더 다양한 제어가 가능)
			BufferedImage buffrImg = ImageIO.read(url);
			image = buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);	//이미지 크기 지정
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	//클래스 패스로 부터 아이콘을 반환해주는 메서드
}
