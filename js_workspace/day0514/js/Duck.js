/**ES6 부터는 클래스 뿐 아니라 상속까지도 자바 언어와 흡사하게 지원 */
class Duck extends Bird{
    constructor(color, age){
        /**js와 java 둘 다 상속관계에서 자식의 인스턴스가 초기화 되기 전에
         * 부모의 인스턴스 초기화가 앞어야 함은 동일하지만 
         * js는 개발자가 자식의 클래스에서 생성자를 정의만 해도, 
         * 무조건 부모의 생성자 호출을 의무사항으로 명시함. 
         */

        //부모 초기화
        super(color, age);    // 부모의 constructor()를 의미
        this.color = color;
        this.age = age;
        //this.color, this.age는 자식의 변수가 없으면 부모것을 사용하고, 자식에 변수가 있으면 자식것을 사용한다.
        console.log("자식: ", this.color, this.age);
    }
    fly(){
        console.log("오리가 날아요");
    }
}