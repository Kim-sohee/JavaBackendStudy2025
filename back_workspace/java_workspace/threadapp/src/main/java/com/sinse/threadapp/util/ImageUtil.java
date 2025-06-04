package com.sinse.threadapp.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtil {
	public Image getImage(String filename, int width, int height) {
		URL url = this.getClass().getClassLoader().getResource(filename);
		Image image=null;
		try {
			BufferedImage buffrImg = ImageIO.read(url);
			image = buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);	//이미지 크기 지정
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
