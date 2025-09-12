package com.sinse.apiapp.controller;

import com.sinse.apiapp.domain.Board;
import com.sinse.apiapp.model.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*글등록       POST         /api/boards
* 글목록       GET         /api/boards
* 상세보기     GET       /api/boards/{boardId}
* 수정하기      PUT     /api/boards/{boardId}
* 삭제하기      DETLETE     /api/boards/{boardId}
* */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    //목록 가져오기
    @GetMapping("/boards")
    public List<Board> getBoards() {
        return boardService.selectAll();
    }

    //글 등록하기
    @PostMapping("/boards")
    public ResponseEntity<?> createBoard(@RequestBody Board board) {
        boardService.regist(board);
        return ResponseEntity.ok(Map.of("result", "등록 성공"));
    }

    //상세보기
    @GetMapping("/boards/{boardId}")
    public Board getBoard(@PathVariable("boardId") int boardId) {
        return boardService.select(boardId);
    }

    //수정하기
    @PutMapping("/boards/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable("boardId") int boardId, @RequestBody Board board) {
        boardService.update(board);
        return ResponseEntity.ok(Map.of("result", "수정성공"));
    }

    //삭제하기
    public ResponseEntity<?> deleteBoard(@PathVariable("boardId") int boardId) {
        boardService.delete(boardId);
        return ResponseEntity.ok(Map.of("result", "삭제 성공"));
    }
}
