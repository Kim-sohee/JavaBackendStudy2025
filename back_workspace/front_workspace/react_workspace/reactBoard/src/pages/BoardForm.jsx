/*
CORS(Cross-Origin-)

*/

import { useNavigate, useParams } from "react-router-dom";
import { insertBoard,updateBoard } from "../api/api";
import { useState } from "react";

export default function BoardForm(){
    const {boardId} = useParams();

    //넘겨받은 boardId 있다면 수정, 없으면 새로운 글쓰기
    const isEdit = !!boardId;      //ture이면 수정, false이면 새글 작성
    const navigate = useNavigate();

    //이 컨포넌트에서 관리되어질 상태값들..
    const [title, setTitle] = useState('');
    const [writer, setWriter] = useState('');
    const [content, setContent] = useState('');

    //함수 정의
    const saveBoard=async()=>{
        console.log("글쓰기 요청");
        const data = {title, writer, content};

        //글 쓰기
        if(isEdit == false){
            await insertBoard(data);  //jquery ajax 처럼 JSON.stringfy()할 필요X
        }else{
            //글 수정
            await updateBoard(boardId, data);
        }
        //쓰기, 수정 성공되면 목록으로 이동
        navigate('/');
    }
    
    return (
        <div>
            <h2>{isEdit ?'글 수정': '글 새로 작성'}</h2>
            <form>
                <div>
                    <input type="text" value={title} onChange={(e)=>setTitle(e.target.value)} placeholder="제목 입력"/>
                </div>
                <div>
                    <input type="text" value={writer} onChange={(e)=>setWriter(e.target.value)} placeholder="작성자 입력"/>
                </div>
                <div>
                    <textarea value={content} onChange={(e)=>setContent(e.target.value)} placeholder="내용 입력"></textarea>
                </div>
                <button type="button" onClick={saveBoard}>
                    {isEdit ?'수정하기': '등록하기'}
                </button>
                <button type="button" onClick={()=>navigate('/')}>목록보기</button>
            </form>
        </div>
    )
}