package instrument;

public class Cello extends MusicTool{
	int volume = 30;
	
	//부모의 불완전한 메서드를 정의
	public void setVolume(){
		System.out.println("첼로의 소리를 높인다.");
	}
	
	public void musicStart(){
		System.out.println("MP3 파일 재생");
	}
}
