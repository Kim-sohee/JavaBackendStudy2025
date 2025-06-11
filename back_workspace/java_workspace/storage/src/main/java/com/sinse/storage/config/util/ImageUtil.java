package com.sinse.storage.config.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageUtil {
	
	/*---------------------------------------
	 *  아이콘 얻기
	 * ---------------------------------------*/
	public Icon getIcon(String filename, int width, int height) {
		Class myClass = getClass();
		
		URL url = myClass.getClassLoader().getResource(filename);
		ImageIcon icon = null;
		
		try {
			BufferedImage buffrImg;
			buffrImg = ImageIO.read(url);
			Image image = buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
}
