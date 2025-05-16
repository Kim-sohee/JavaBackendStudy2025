package instrument;

//모두 따라야 할 지침 가이드 클래스(기준이 되는 부모)
//추상클래스란 불완전한 클래스를 말함, 불완전하다는 것은 메서드 몸체가 없는
//추상메서드를 단 한개라도 보유하고 있으면, 추상클래스가 된다.
//완전하지 않기 때문에 new 할 수 없다. (이 클래스를 상속 받는 자식을 정의하여 자식을 new)
abstract public class MusicTool {
	//볼륨을 조절하는 기능에 대한 가이드 제시
	abstract public void setVolume();		//불완전한 메서드	
}