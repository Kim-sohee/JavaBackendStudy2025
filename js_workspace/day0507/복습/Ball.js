class Ball{
    constructor(container, bg, x, y, size, velX, velY){
        this.container = container;
        this.div=document.createElement("div");
        this.bg = bg;
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.velX = velX;
        this.velY = velY;

        //style
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.borderRadius = "50%";

        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        this.container.appendChild(this.div);
    }
    move(){
        this.x += this.velX;
        this.y += this.velY;

        if(this.x <= 0 || this.x >= (600-this.width))   this.velX = -this.velX;
        if(this.y <= 0 || this.y >= (600-this.height))   this.velY = -this.velY;

        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

    }
}