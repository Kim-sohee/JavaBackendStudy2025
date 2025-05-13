/*다른 개발자가 만든 클래스는 어떤 변수나 함수를 가지고 있는지 알 수 없다.
해결책: 제작자로부터 클래스에 대한 설명을 들어야 한다.
정답: 클래스를 제작한 사람은 반드시 설명서를 제공할 의무가 있다.*/

//자바 개발시 사용되는 필수적이고, 중요한 클래스들은 java.lang 패키지에 들어있고, 
//이 패키지는 개발자가 명시하지 않아도 이미 import 처리되어 있음.

package use;
import test.Sinse;

class  UseTest{
	public static void main(String[] args){
		Sinse s = new Sinse();
		int time = 8;
		s.study(time);
	}
}
