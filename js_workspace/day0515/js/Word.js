//단어 정의 클래스
class Word{
    constructor(container, x, y, text, color){
        this.container = container;
        this.span = document.createElement("span"); //span은 인라인이므로 너비가 컨텐츠만큼 확보(wrapping)
        this.x = x;
        this.y = y;
        this.text = text;   //span 안 글자
        this.color = color; //글씨 색상

        //style
        this.span.style.display="inline-block";
        this.span.style.position="absolute";
        this.span.style.left=this.x+"px";
        this.span.style.top=this.y+"px";
        this.span.innerText=this.text;
        this.span.style.color=this.color;

        this.container.appendChild(this.span);
    }

    tick(){
        this.y += 5;    // 글자가 아래로 떨어지나 봄
    }

    render(){
        this.span.style.top = this.y + "px";
    }
}