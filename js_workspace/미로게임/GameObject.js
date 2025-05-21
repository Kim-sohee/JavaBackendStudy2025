class GameObject{
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        this.container = container;
        //이미지로 개발하지 못하는 이유 -> 이미지는 부모 기능이 없음
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.bg = bg;
        this.border = border;
        this.borderColor = borderColor;

        //style 및 조립
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.border = `${this.border}px solid ${this.borderColor}`;

        this.container.appendChild(this.div);
    }

    //오브젝트의 물리량 계산
    tick(){}

    //오브젝트 랜더링
    render(){}
}