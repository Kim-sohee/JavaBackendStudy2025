 class Hero{
    constructor(container, x, y, size, velX, velY){
        this.container = container;
        this.img = document.createElement("img");

        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.velX = velX;
        this.velY = velY;

        this.imgArr = [];
        this.n = 0;
        for(let i=1; i<=18; i++){
            this.imgArr.push(`../../../images/hero/image${i}.png`);
        }

        //style
        this.img.src = '../../../images/hero/image1.png';
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";

        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        this.container.appendChild(this.img);

        this.idle();
    }

    idle(){
        this.n++;
        if(this.n >= 18) this.n=0;
        this.img.src = this.imgArr[this.n];
        setTimeout(()=>{
            this.idle();
        }, 50);
    }

    move(){
        this.x += this.velX;
        this.y += this.velY;

        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
 }