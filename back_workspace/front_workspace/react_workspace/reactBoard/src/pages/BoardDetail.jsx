import { useState, useEffect } from "react"
import {Link, useParams} from "react-router-dom"
import { getBoard } from "../api/api";

export default function BoardDetail(){

    /*
      URL 패턴이 /view/:boardId라고 정의되어 있을 경우
      useParams()가 URL을 분석하여, {boardId:5}와 같은 객체를 반환

    */
    const {boardId} = useParams();

    const [board, setBoard] = useState(null);

    useEffect(()=>{getDetail(boardId)}, [boardId]);

    const getDetail=async()=>{
        const res = await getBoard(boardId);
        //화면에 반영
        setBoard(res.data);
    }

    if(!board) return <p>Now Loading...</p>

    return (
        <div>
            <h2>{board.title}</h2>
            <p>작성자: {board.writer}</p>
            <p>내용: {board.content}</p>
            <p>등록일: {board.regdate}</p>
            <p>조회수: {board.hit}</p>
            <button type="button">삭제</button>
            <Link to={`/edit/${board.boardId}`}>수정</Link>
            <Link to={'/'}>목록</Link>
        </div>
    )
}