//당구공 정의
class Ball {
    //생성자 정의
    constructor(container, x, y, width, heigth, velX, velY, bg){
        this.container = container //이 요소를 어디에 붙일지 결정
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.velX = velX;   //x축으로의 속도
        this.velY = velY;   //y축으로의 속도
        this.bg = bg;       //공의 색상

        //style
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        
        this.div.style.width = this.width + "px";
        this.div.style.height = this.heigth + "px";

        this.div.style.background = this.bg;
        this.div.style.borderRadius = "50%";

        this.container.appendChild(this.div);
    }
    //공의 움직임 메서드
    move(){
        this.x += this.velX;
        this.y += this.velY;

        //각 4면을 만날 때마다 velX, velY의 양수 혹은 음수로 부여할 지 결정
        if(this.y <= 0 || this.y >= (600-this.heigth)){
            this.velY = -this.velY;
        }
        if(this.x <= 0 || this.x >= (600-this.width)){
            this.velX = -this.velX;
        }

        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }
}