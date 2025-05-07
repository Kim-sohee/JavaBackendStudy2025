/*총알 정의
    java, c#, python등 모든 클래스 안에는 단 2가지만 작성할 수 있음
    변수(상태)와 함수(동작, 상태변경)
*/
class Bullet{
    //아래의 생성자 메서드는 new 연산자 뒤에서 호출됨
    constructor(bg, y){
        //총알이 보유할 변수(속성=property)를 선언
        this.div = document.createElement("div");
        this.x = 0;     //총알의 x축 위치
        this.y = y;
        this.velX = 10; //총알의 속도
        this.bg = bg;   //매개변수로 넘어온 데이터를 클래스 변수에 보관

        //총알의 스타일 적용
        this.div.style.width = 20 + "px";
        this.div.style.height = 20 + "px";
        this.div.style.borderRadius = "50%";
        this.div.style.background = this.bg;
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        document.body.appendChild(this.div);
    }
    //총알의 위치 상태를 변경시키기 위한 메서드
    move(){
        this.x += this.velX;
        this.div.style.left = this.x + "px";
    }
}