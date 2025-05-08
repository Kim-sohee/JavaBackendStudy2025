class Bullet{
    constructor(container, x, y, size, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.velX = velX;
        this.velY = velY;

        this.img.src = "../../../images/image/cat.png";
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        this.container.appendChild(this.img);
    }

    move(){
        if(this.x >=1500){
            this.container.removeChild(this.img);
            let index = bulletArray.indexOf(this);
            bulletArray.splice(index, 1);
            console.log("현재 총알 수는", bulletArray.length);
        }

        this.x += this.velX;
        this.img.style.left = this.x + "px";
    }
}