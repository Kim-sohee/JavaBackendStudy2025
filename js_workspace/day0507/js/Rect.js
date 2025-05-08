//사각형 막대를 정의
class Rect {
    constructor(container, x, y, width, height, bg){
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;

        this.targetH = 450;

        //style
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";

        this.div.style.background = this.bg;
        this.div.style.border = "1px solid gray";

        //부모에 부착
        this.container.appendChild(this.div);

        this.move();
    }

    move(){
        this.div.style.height = parseFloat(this.div.style.height) + 0.1*(this.targetH - parseFloat(this.div.style.height))+"px"; 
        // this.div.style.top = parseInt(this.div.style.top) + 0.1*(this.targetH - parseInt(this.div.style.top))+"px";

        setTimeout(()=>{
            this.move();
        }, 10);
    }
}