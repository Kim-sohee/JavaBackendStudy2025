class Hero extends GameObject{
    //생성자를 명시하기만 해도 생성자 내에서 super()명시
    //부모님의 생성자에 매개변수가 있다면, 그에 맞게 호출
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY);
    }

    //부모가 완성하지 못했던 메서드를 자식이 자신의 상황에 맞게 커스텀하는 것을
    //메서드 재정의(오버라이딩)라 한다.
    tick(){
        this.x += this.velX;
        this.y += this.velY; 
    }

    rander(){
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
}