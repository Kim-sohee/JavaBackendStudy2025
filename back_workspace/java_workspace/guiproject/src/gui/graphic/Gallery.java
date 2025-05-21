package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

class Gallery extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt_prev;
	JButton bt_next;
	JLabel la_title;
	Toolkit kit; 	//시스템의 이미지를 개발자 대신 얻어다 주는 객체
	MyCanvas myCanvas;
	
	//js와는 달리 대부분의 언어는 배열이 고정배열이므로 반드시 배열 선언시 크기를 명시해야 한다.
	Image[] imgArray = new Image[9];
	int index = 0;	//이미지 배열을 접근하기 위한 인덱스
	
	//생성자
	public Gallery(){
		p_north = new JPanel();
		myCanvas = new MyCanvas();
		la_title = new JLabel("제목");
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		kit = Toolkit.getDefaultToolkit();	//툴킷의 인스턴스 얻기(클래스 메서드)
		
		//style 적용
		p_north.setLayout(new FlowLayout());
		p_north.setBackground(Color.LIGHT_GRAY);
		p_north.setPreferredSize(new Dimension(600, 50));
		myCanvas.setBackground(Color.YELLOW);
		myCanvas.setPreferredSize(new Dimension(600, 450));
		
		//조합
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		add(p_north, BorderLayout.NORTH);
		add(myCanvas);
		
		//초기 이미지 보여주기
		createImage();
		
		//패널에 초기에 보여질 이미지를 전달
		myCanvas.setImage(imgArray[index]);	//이미지 전달
		
		//이전 버튼과 리스너 연결
		bt_prev.addActionListener(this);
		//다음 버튼과 리스너 연결
		bt_next.addActionListener(this);
		
		//윈도우 띄우기
		setSize(600, 500);
		setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//멤버 변수로 선언된 이미지 배열에, 이미지 인스턴스를 준비하여 프로그램 가동과 동시에 사용자가 사용
	public void createImage(){
		String[] path = {
			"bear.jpg", "cosmos.jpg", "diver.jpg", "fish.jpg", "fox.jpg", 
			"lion.jpg", "mouse.jpg", "sunset.jpg", "turtle.jpg"
		};
		
		for(int i=0; i<path.length; i++){
			//툴킷으로부터 이미지 인스턴스 9개 생성하여 image배열에 넣어주기
			String imgUrl = "C:/lecture_workspace/images/geographic/" + path[i];
			imgArray[i] = kit.getImage(imgUrl);
		}
	}
	
	//myCanvas 영역에 이미지 출력하기
	//그래픽 프로그래밍에서 컴포넌트의 약간의 변화라도 생기면 그 그림은 지워지고 다시 그려져야 하는데
	//개발자가 처리하는 것이 아니라 시스템이 알아서 렌더링을 담당하게 된다.
	//컴포넌트에 변경이 생기면 다음의 과정을 거쳐서 그래픽이 구현된다.
	
	/*
	[AWT]
	최초 컴포넌트 등장 시 paint()에 의해 눈에 보여짐
	사용자가 컴포넌트의 그림에 변화를 주게 되면, jvm은 기존 그림을 지워야 하므로
	update() 메소드를 호출하여 그림을 깨끗이 지우고 내부적으로 paint()메서드를 호출하여
	변경된 그림을 화면에 보여준다.
	
	[Swing]
	사용자가 컴포넌트에 변화를 주게 되면 update() 메서드가 호출되는 것이 아니라
	paintComponent()를 호출하여 화면을 깨끗이 지운다.
	변경된 그림을 다시 보여주기 위해 스스로 paint()를 호출
	*/
	public void showImage(int flag){
		if(flag == 0){
			index--;
		}else if(flag == 1){
			index++;	//다음 사진일 경우 인덱스 증가
		}
		
		if(index <= 0){
			index = 0;
		}else if(index > imgArray.length){
			index = imgArray.length -1;
		}
		
		myCanvas.setImage(imgArray[index]);
		myCanvas.repaint();	//패널을 다시 그려줘.
		
		//변경된 이미지를 보기 위해서는 사용자의 윈도우 조작이 아니라
		//개발자가 프로그래밍 적으로 지우고 다시 그릴 것을 요청
		//이때 호출되는 메서드가 바로 repaint() 즉 다시 그려줄 것을 부탁하는 메서드이다.
		//개발자는 절대로 paint()를 직접 호출해선 안된다 -> 그림은 시스템이 알아서 그리기 때문
		/* 과정
		repaint() --> update(AWT)	--> paint()
		repaint() --> paintCompoent(Swing) --> paint()
		*/
	}
	
	public void actionPerformed(ActionEvent e){
		//이벤트를 일으킨 컴포넌트를 가리켜 이벤트 소스라 한다.
		Object obj = e.getSource();
		
		//버튼 인스턴스의 주소값만 비교하면 되므로, 굳이 형변환 할 필요 없음
		if(obj == bt_prev){
			showImage(0);
		}else if(obj == bt_next){
			showImage(1);
		}
	}
	
	//공부 목적 상, 프레임의 그림을 뺏어서 그려보자
/* 	public void paint(Graphics g){
			System.out.println("나 그려짐");
	}
	 */
	//메인(실행)
	public static void main(String[] args) {
		new Gallery();
	}
}
