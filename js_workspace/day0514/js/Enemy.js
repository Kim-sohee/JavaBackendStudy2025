class Enemy extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY);
    }

    //부모가 물려준, tick()과 render를 오버라이딩
    tick(){
        this.x += this.velX;
    }

    rander(){
        this.img.style.left = this.x + "px";
    }
}