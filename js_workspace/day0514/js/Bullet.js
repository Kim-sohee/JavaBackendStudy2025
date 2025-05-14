class Bullet extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY);
    }
    //제거 메서드
    removeObject(array, target){
        //1) 화면에서 제거
        //this는 나의 인스턴스를 가리키는 대명사
        this.container.removeChild(array[array.indexOf(target)].img);

        //2) 총알이 있던 배열에서도 제거(제거 안하면 반복문이 계속 총알이 있는 줄 안다)
        array.splice(array.indexOf(target), 1);
    }
    tick(){
        this.x += this.velX;
    }

    rander(){
        //총알이 한걸음씩 나아갈 때마다, 총알과 적군과의 충돌을 체크해서 제거처리
        for(let i=0; i<enemyArr.length; i++){
            let enemy = enemyArr[i];    //적군 꺼내기

            if(collisionCheck(this.img, enemy.img)){
                this.removeObject(enemyArr, enemy);
                this.removeObject(bulletArr, this);

                setScore(10);   //10점 증가
            }
        }

        //적군에 닿지 않은 총알은 자동 제거(1000px over)
        if(this.x > 1400){
            this.removeObject(bulletArr, this);
        }

        this.img.style.left = this.x + "px";
    }
}