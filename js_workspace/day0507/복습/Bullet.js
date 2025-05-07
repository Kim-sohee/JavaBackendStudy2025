class Bullet {
    constructor(bg, y){
        this.div = document.createElement("div");
        this.x = 0;
        this.y = y;
        this.speed = 10;
        this.color = bg;
        
        this.div.style.width = 40 + "px";
        this.div.style.height = 40 + "px";
        this.div.style.background = this.color;
        this.div.style.position = "absolute";
        
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        document.body.appendChild(this.div);

    }
    move(){
        this.x += this.speed;
        this.div.style.left = this.x + "px";
    }
}