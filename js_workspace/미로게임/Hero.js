class Hero extends GameObject{
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        super(container, x, y, width, height, velX, velY, bg, border, borderColor);

        //4개의 센서를 보유하자(has a 관계)
        this.upSensor;
        this.rightSensor = new RightSensor(this.div, this, this.width-3, 3, 3, this.height-6, 0, 0, "blue", 0, "");
        this.downSensor;
        this.leftSensor;
    }

    //부모의 메서드 오버라이딩
    tick(){
        this.x += this.velX;
        this.y += this.velY;
    }
    render(){
        //벽돌과 나와의 충돌 검사
        for(let i=0; i<brickArray.length; i++){
            for(let a=0; a<brickArray[i].length; a++){
                let brick = brickArray[i][a];
                if(brick != 0){
                    if(collisionCheck(this.div, brick.div)){
                        //주인공 색상은 분홍색
                        this.div.style.background = "pink";
                    }
                }
            }
        }

        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }
}