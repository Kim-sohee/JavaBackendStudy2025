//게임에 등장할 모든 객체의 최상위 객체를 정의한다.
//공통적인 속성과 기능을 중복 작성하지 않기 위해(재사용)
class GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        //style
        this.img.src = this.src;
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        this.container.appendChild(this.img);
    }

    //중복되는 메서드를 부모 클래스에 정의하는 것은 올바른 개발 방식이다.
    //그러나 게임에 등장할 모든 객체들의 움직임을 부모가 예측할 수 없으므로
    //메서드 재정의가 필요

    //물리량 변화
    tick(){}; //{몸체}가 없는 메서드를 추상메서드라 함

    //변화된 물리량을 화면에 반영
    rander(){};
}