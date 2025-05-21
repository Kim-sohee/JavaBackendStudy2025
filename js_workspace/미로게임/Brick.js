class Brick extends GameObject{
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        //js는 java 처럼 super() 자동으로 호출해주는 기능 없음
        super(container, x, y, width, height, velX, velY, bg, border, borderColor);

    }
}