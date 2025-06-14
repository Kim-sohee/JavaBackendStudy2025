package com.sinse.shopadmin.product.view;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JDialog;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.util.FileUtil;

public class UploadDialog extends JDialog{
	ProductPage productPage;
	public UploadDialog(ProductPage productPage) {
		this.productPage = productPage;
		JDialog dialog = new JDialog(this, "업로드 정보", true);
		dialog.setLocationRelativeTo(productPage);
		dialog.setLayout(new FlowLayout());
		
		//우리가 미리보기한 사진의 수만큼 배열을 확보
		productPage.newFiles = new File[productPage.files.length];
		
		//커스텀 된 바를 임시로 6개 화면에 부착해보자.
		for(int i=0; i<productPage.files.length; i++) {
			//어디에 저장할 지 디렉토리 결정
			File dest = FileUtil.createFile(Config.PRODUCT_IMG_PATH, FileUtil.getExt(productPage.files[i].getName()));
			productPage.newFiles[i] = dest;		//상품 등록 폼에 파일 정보를 대입해주기 위함
			MyBar bar = new MyBar(productPage.files[i], dest);
			Thread thread = new Thread(bar);
			thread.start();
			
			dialog.add(bar);
		}

		dialog.setSize(700, 600);
		dialog.setVisible(true);
	}
}
