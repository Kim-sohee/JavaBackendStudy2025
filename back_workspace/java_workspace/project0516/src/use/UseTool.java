package use;

//import instrument.Guitar;
import instrument.Drum;
import instrument.Bass;
import instrument.Piano;
import instrument.Trumpet;
import instrument.Violin;
import instrument.Cello;
import instrument.MusicTool;
import riding.Roller;

//납품을 앞두고 모든 악기에 대해 최종 테스트
public class UseTool {
	public static void main(String[] args) {
		//베이스 볼륨 테스트
		Bass bass = new Bass();
		
		//트럼펫 볼륨 테스트
		Trumpet trumpet = new Trumpet();
		trumpet.setVolume(60);
		
		//오케스트라 협주 중 모든 악기를 일괄 볼륨 조절
		//각 악기가 보유한 메서드가 무엇인지 알 수 없음(메서드 명의 일관성X)
		//기술적으로 개발자들에게 업무 규칙을 만들어 제재를 가해야 함
		//가이드 라인 제시 -> 기준이 되는 클래스 생성
		
		//추상클래스는 인스턴스화 될 수 없다.
		//new MusicTool();
		
		//Piano p = new Piano();
		
		//다형성: 자식 객체를 부모 객체로 불러올 수 있다.
		MusicTool p = new Piano();
		p.setVolume();
		
		//인터페이스도 is a 관계로 인정받음, 따라서 형변환 가능
		Roller p2 = new Piano();
		p2.roll();
		
		Cello c = new Cello();
		c.setVolume();
	}
}
