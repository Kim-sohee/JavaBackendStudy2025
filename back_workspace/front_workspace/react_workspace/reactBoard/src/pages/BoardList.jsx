//나 아닌 다른 파일에서도 이 함수를 사용하도록 허용해주는 명령어

import { useEffect, useState } from "react"
import { useNavigate, Link, useParams } from "react-router-dom";
import { getBoards } from "../api/api";

//ES6에서는 클래스뿐 아니라 함수를 통해서도 컴포넌트를 정의할 수 있다.
export default function BoardList(){
    /*useState는 react의 데이터 상태(state) 훅(hook)으로 BoardList 컴포넌트안에서
    ㄱ상태 관리를 담당함
    boards: 현재의 상태값
    setBoards: 상태를 변경할 수 있는 함수 */
    const [boards, setBoards] = useState([1,1,1,1]);

    const navigate = useNavigate();

    //사이드 이펙트 Hook
    /*부수 효과를 관리하는 훅
      사이드 이펙트가 호출되는 시점은 3가지 경우이다
      1) 매 랜더링 마다(이 컴포넌트가 화면에 그려질 때마다)
            -> userEffect()에 매개변수로 배열이 존재하지 않을 경우 (거의 안씀)
      2) 단 1회만 호출되는 경우
            -> userEffect()에 매개변수에 빈 배열이 존재할 경우
      3) 특정 값이 바뀔 때 호출되는 경우
            -> useState에 의한 특정 상태값이나 props 값이 변경될 때
    */
    useEffect(()=>{load()}, []);

    //서버와의 통신을 비동기로 진행
    //aysnc로 선언된 함수는 반드시 내부에 await 명시해야 함
    //await가 명시된 함수 호출은, 해당 함수 호출이 완료되어야 그 다음 코드가 수행됨(동기방식의 수행)
    const load = async()=>{
        const res = await getBoards();     //서버로부터 가져온 json 배열 res에 보관
        // alert("목록을 가져왔습니다.");
        console.log(res);
        setBoards(res.data);
    }

    return(
        <table>
            <thead>
                <tr>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                {boards.map(b=>(
                <tr key={b.boardId}>
                    <td><Link to={`/view/${b.boardId}`}>{b.title}</Link></td>
                    <td>{b.writer}</td>
                    <td>{b.regdate}</td>
                    <td>{b.hit}</td>
                </tr>
                ))}
                <tr>
                    <td colSpan="4">
                        <button type="button" onClick={()=>navigate('/new')}>글쓰기</button>
                    </td>
                </tr>
            </tbody>
        </table>
    )
}