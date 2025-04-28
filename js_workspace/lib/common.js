
//랜덤한 정수 값 구하기
//예: getRandom(7) => 0~6사이의 정수 반환
//    getRandom(100) => 0~99사이의 정수 반환
function getRandom(num){
    return parseInt(Math.random() * num);
}