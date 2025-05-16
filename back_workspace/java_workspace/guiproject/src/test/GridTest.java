package test;
import java.awt.*;

/*
GridLayout: 행과 열을 지원하는 배치방법
*/

class GridTest {
	public static void main(String[] args) {
		Frame f = new Frame("그리드 배치");
		
		f.setLayout(new GridLayout(3, 4));
		
		for(int i=0; i<3; i++){	//층수
			for(int j=0; j<4; j++){	//호수
				Button bt = new Button((3-i)+"층"+ (j+1) + "호수");
				bt.setBackground(Color.YELLOW);
				f.add(bt);	
			}
		}
		f.setSize(400, 300);
		f.setVisible(true);
	}
}
