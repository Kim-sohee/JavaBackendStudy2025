class Cell{
    constructor(container, x, y, width, height, bg, borderColor, date){
        //다이어리 관련 내용
        this.year;
        this.month;
        this.date = date;
        this.icon;

        //UI 관련 내용
        this.container = container;
        this.div = document.createElement("div");
        this.numDiv = document.createElement("div");    //달력 날짜 출력할 영역
        this.titleDiv = document.createElement("div");  //다이어리 제목
        this.iconDiv = document.createElement("div");    //아이콘이 위치할 영역
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.borderColor = borderColor;

        //cell div style
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.border = `1px solid ${this.borderColor}`;
        this.div.style.borderRadius = 5 +"px";
        this.div.style.boxSizing = "border-box";    //border, margin, padding에 의한 박스의 크기가 바깥쪽으로 커지지 않게함.(inside)
        
        //날짜 출력 div style
        this.numDiv.style.width = 100 + "%";
        this.numDiv.style.height = 25 + "px";
        this.numDiv.style.textAlign = "right";
        this.numDiv.style.padding = "0px 5px 0px 0px";  //top-right-bottom-left(시계방향)
        this.numDiv.style.boxSizing = "border-box";

        //다이어리 제목 DIV STYLE
        this.titleDiv.style.width = 100 + "%";
        this.titleDiv.style.height = 25 +"px";

        //아이콘 영역 div 스타일
        this.iconDiv.style.width = 100 + "%";
        this.iconDiv.style.height = 50 + "px";

        //셀에 3층 div 각각 부착
        this.div.appendChild(this.numDiv);
        this.div.appendChild(this.titleDiv);
        this.div.appendChild(this.iconDiv);

        this.container.appendChild(this.div);

        //현재 이 셀에 클릭 이벤트 구현
        //화살표 함수는 this를 가질 수 없으므로, 바깥쪽 상위 스코프인 객체를 가리키려면 화살표 함수를 사용
        this.div.addEventListener("click", ()=>{
            //창을 띄울거임(전역함수 접근)
            openDialog(this);
        });
    }

    //셀에 보여질 날짜를 수시로 변경해야 하므로, 메서드의 대상이 되리 수 있음
    setDate(year, month, date){
        this.year = year;
        this.month = month;
        this.date = date;

        //랜더링 처리
        this.numDiv.innerText = this.date;
    }
    //셀이 자신이 보유한 아이콘 그리기
    renderIcon(src, width){    //어떤 이미지를 원하는지는 호출자가 결정
        this.icon = document.createElement("img");
        this.icon.src = src;
        //이미지의 크기 조정
        this.icon.style.width = width+"px";
        this.iconDiv.appendChild(this.icon);
    }
}