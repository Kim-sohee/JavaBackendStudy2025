/*모든 새들의 어버이격인 그냥 새를 정의*/
class Bird{
    constructor(color, age){
        this.color = color;
        this.age = age;
        console.log("Bird의 생성자 호출");
        console.log("부모: ",this.color, this.age);

    }
    eat(){
        console.log("새가 먹이를 먹어요");
    }
    fly(){
        console.log("새가 날아요");
    }
}