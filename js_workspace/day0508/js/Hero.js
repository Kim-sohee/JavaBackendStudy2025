class Hero {
    //ES6의 클래스는 멤버 변수를 생성자 안에 둬야 함
    constructor(container, x, y, width, height, velX, velY){
        //외부에서 전달된 데이터를 나의 객체에 보관하는 기법을 injection이라 한다.
        this.container = container;
        this.img = document.createElement("img");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        //주인공의 sprite 이미지명 배열 선언
        this.imgArray=[];
        this.n= 0;  //이미지 배열의 index를 결정짓는 변수

        for(let i=1; i<=18; i++){
            this.imgArray.push(`../../images/hero/image${i}.png`);
        }

        //style
        this.img.src = "../../images/hero/image1.png";
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";

        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        this.container.appendChild(this.img);

        this.doIdle();  //움직이기 시작
        

    }

    //주인공 펄럭임 효과
    //게임 루프와 상관없이 자체적으로 끝없는 루프로 움직임 표현
    doIdle(){
        this.n++;
        this.img.src = this.imgArray[this.n];
        if(this.n >= 17) this.n=0;
        //화살표 함수는 this를 가질 수 없으므로, 여시거의 this는 상위스코프를 나타냄
        setTimeout(()=>{
            this.doIdle();
        }, 50);
    }

    //모든 방향에 대한 움직임 동작 정의
    move(){
        //물리적 변화량
        this.x += this.velX;
        this.y += this.velY;
        // console.log(this.x, this.y);

        //변화된 물리량을 화면에 반영(랜더링)
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
}