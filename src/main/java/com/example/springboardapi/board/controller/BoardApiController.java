package com.example.springboardapi.board.controller;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardReqVo;
import com.example.springboardapi.board.service.BoardService;
import com.example.springboardapi.board.vo.BoardResVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping(path = "/board/")
public class BoardApiController {

    private final BoardService boardService;

    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 조회
    @GetMapping(path = "list")
    public ResponseEntity<BoardResVo> list(BoardReqVo reqVo) {
        BoardResVo resVo = new BoardResVo();
        resVo.setData(boardService.selectList(reqVo));
        return ResponseEntity.ok(resVo);
    }

    // 게시글 하나 조회
    @GetMapping(path = "one")
    public ResponseEntity<Board> one(BoardReqVo reqVo) {
        return ResponseEntity.ok(boardService.selectOne(reqVo));
    }

    // 게시글 저장
    @PostMapping(path = "add")
    public ResponseEntity<String> save(BoardReqVo reqVo) {
        ResponseEntity<String> response = null;
        try {
            boardService.save(reqVo);
            response = new ResponseEntity<String>("sucess", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    // 게시글 업데이트
    @PutMapping(path = "update")
    public ResponseEntity<String> update(BoardReqVo reqVo) {
        ResponseEntity<String> response = null;
        try {
            boardService.update(reqVo);
            response = new ResponseEntity<String>("sucess", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    // 게시글 삭제
    @DeleteMapping(path = "delete")
    public ResponseEntity<String> delete(BoardReqVo reqVo) {
        ResponseEntity<String> response = null;
        try {
            boardService.delete(reqVo);
            response = new ResponseEntity<String>("sucess", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
