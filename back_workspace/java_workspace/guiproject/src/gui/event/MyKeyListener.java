package gui.event;
/*OS를 거쳐 JVM으로부터 전달되는 키보드 이벤트를 청취하기 위한 객체인 KeyListener를 재정의*/
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyKeyListener implements KeyListener{
	//
	public void keyTyped(KeyEvent e){
		
	}
	
	//키보드 눌렀을 때 (keydown)
	public void keyPressed(KeyEvent e){
		System.out.println("눌렀어?");
	}
	
	//키보드 눌렀다 뗐을 때 (keyup)
	public void keyReleased(KeyEvent e){
		System.out.println("눌렀다가 떼었어?");
	}
}
