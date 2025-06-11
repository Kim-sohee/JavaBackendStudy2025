package chat.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//개발자가 네트워크에 대한 지식이 없이도, 네트워크 연동 프로그램을 작성할 수 있도록 지원해주는 객체가 Socket 이다.
//개발자는 네트워크에 대한 직접 연동을 하지 않고, Socket을 통해 스트림을 얻어와, 결국 스트림 제어만 하면 된다.

/*1) 에코 서버와 클라이언트 모델
 * 2) 유니케스팅 모델
 * 3) 멀티케스팅 모델
 * */
public class EchoServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;	//대화용 소켓이 아니라, 접속자를 감지하고 접속이 감지되면 대화용 소켓을 반환
	Thread thread; 		//Runnable은 쓰레드가 아니므로 Runnable을 구현한다고 하여, 이 객체가 쓰레드 형이라고 오해하면 안된다.
								//따라서 별도의 Thread 객체를 사용해야 한다. 단지 해당 Thread의 run 메서드를 내가 가진것 뿐이다.
	
	//클라이언트와의 대화를 위한 스트림 준비
	BufferedReader buffr;		//듣기용
	BufferedWriter buffw;		//말하기용
	
	public EchoServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999",8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
	
		//가동 버튼에 리스너 연결
		bt.addActionListener(e->{
			thread = new Thread(EchoServer.this);
			thread.start();
		});
		
		setBounds(500, 300, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 객체 생성 및 접속 청취중... \n");
			
			Socket socket = server.accept();	//접속자가 있을 때까지 무한 대기에 빠짐
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip+"님 접속!\n");
			
			//얻어진 소켓으로부터 스트림 두 개 생성
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			while(true) {
				//클라이언트가 보낸 메시지 듣기
				String msg = buffr.readLine();
				area.append(msg+"\n"); 		//서버에 로그 남기기
				buffw.write(msg+"\n"); 			//버퍼 기반의 스트림이므로, 문자열의 끝을 알려주지 않으면 무한 대기에 빠짐
				
				buffw.flush();	//버퍼비우기
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		startServer();
	}
	
	/*메인 쓰레드의 주 역할
	 * 주의: 일반 쓰레드 아님 -> 프로그램 운영 쓰레드이다.
	 * 운영쓰레드의 주 역할(이벤트 감지, GUI 구성, 관리...)
	 * 특히 스마트폰 개발 시, 메인쓰레드 자체를 대기 상태에 넣는 것 자체가 에러(금지)
	 * */
	public static void main(String[] args) {
		new EchoServer();
	}
}
