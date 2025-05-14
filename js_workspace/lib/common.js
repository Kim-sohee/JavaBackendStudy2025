
/*--------------------------------------------------------
    랜덤한 정수 값 구하기
    예) getRandom(7) => 0~6사이의 정수 반환
       getRandom(100) => 0~99사이의 정수 반환
*--------------------------------------------------------- */
function getRandom(max){
    return parseInt(Math.random()*(max+1)); //0~1사이 난수
}

/*--------------------------------------------------------
    한자리수 정수에 대한 0처리
*--------------------------------------------------------- */
function zeroString(n){
    //만일 n이 한자리 수 이면, 앞에 0 문자를 붙이자.
    let str = n;
    if(n>0 && n<10) str='0'+n;
    return str;
}

/*--------------------------------------------------------
    해당월의 시작 요일 구하기
    예) 2025년 5월 -> getStartDay(2025, 5)
*--------------------------------------------------------- */
function getStartDay(yy, mm){
    let d = new Date(yy, mm, 1);
    return d.getDay();  //요일을 반환
}

/*--------------------------------------------------------
    영어 또는 한국어로 요일을 변환하여 반환하기
    예) convertDay(2, "kor") || convertDay(2, "eng")
*--------------------------------------------------------- */
function convertDay(n, lang){
    let korArr = ['일', '월', '화', '수', '목', '금', '토'];
    let engArr = ["Sun", "Mon", "Tue", "Wed", "Tur", "Fri", "Sat"]
    
    let day = (lang=="kor")? korArr[n] : engArr[n];
    return day;
}

/*--------------------------------------------------------
    해당 월의 마지막 날 구하기
    예) getLastDate(원하는 연도, 원하는 월)
*--------------------------------------------------------- */
function getLastDate(yy, mm){
    let d = new Date(yy, mm+1, 0);
    return d.getDate();
}

/*--------------------------------------------------------
    충돌 체크 함수
    예) 
*--------------------------------------------------------- */
function collisionCheck(me, target){
    //나에 대한 수치계산
    let me_x = parseInt(me.style.left);
    let me_y = parseInt(me.style.top);
    let me_width = parseInt(me.style.width);
    let me_height = parseInt(me.style.height);

    let target_x = parseInt(target.style.left);
    let target_y = parseInt(target.style.top);
    let target_width = parseInt(target.style.width);
    let target_height = parseInt(target.style.height);

    return !(
        me_x + me_width < target_x || //me의 오른쪽이 타겟의 왼쪽보다 왼쪽에 있으면(충돌 전)
        me_x > target_x + target_width || //me의 왼쪽이 타겟의 우측보다 더 오른쪽에 있으면(충돌 전)
        me_y + me_height < target_y ||  //me의 아래쪽 타겟보다 위에 있으면
        me_y > target_y + target_height //me의 위쪽이 타겟의 아래보다 아래에 있으면
    )
}