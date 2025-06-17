package com.sinse.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.util.StringUtil;
import com.sinse.shop.home.MainPage;
import com.sinse.shop.product.model.Product;

public class ProductItem extends JPanel{
	Product product;
	Image image;
	MainPage mainPage;	//현재 ProductItem을 생성한 주체 페이지이므로
	
	public ProductItem(MainPage mainPage, Product product) {
		this.mainPage = mainPage;
		this.product = product;
		
		try {
			image = ImageIO.read(new File("C:/public/"+product.getFilenameList().get(0)));
			image.getScaledInstance(210, 150, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//마우스 리스너 연결
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				//어떤 상품을 선택했는지 그 정보를 보관
				mainPage.product = product;
				
				mainPage.appMain.showPage(Config.PRODUCT_DETAIL_PAGE);		//상품 상세보기
			}
		});
		
		setPreferredSize(new Dimension(220, 280));
		setBackground(Color.yellow);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, 5, 5, 210, 150, this);
		
		//상품명 그리기
		g2.setFont(new Font("고딕", Font.BOLD, 20));
		g2.drawString(product.getProduct_name(), 5, 180);
		
		//가격
		g2.drawString(StringUtil.getPriceString(product.getPrice()), 5, 200);
	}
}
