package com.example.springboardapi.board.controller;

import com.example.exception.CommonResponse;
import com.example.springboardapi.board.vo.BoardReqVo;
import com.example.springboardapi.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/board/")
public class BoardApiController {

    private final BoardService boardService;

    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    CommonResponse res = new CommonResponse();

    // 게시글 목록 조회
    @GetMapping(path = "list")
    public ResponseEntity<CommonResponse> list(BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectList(reqVo)), HttpStatus.OK);
    }

    // 게시글 하나 조회
    @GetMapping(path = "one")
    public ResponseEntity<CommonResponse> one(BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectOne(reqVo)), HttpStatus.OK);
    }

    // 게시글 저장
    @PostMapping(path = "add")
    public ResponseEntity<CommonResponse> save(BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.save(reqVo)), HttpStatus.OK);
    }

    // 게시글 업데이트
    @PutMapping(path = "update")
    public ResponseEntity<CommonResponse> update(BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.update(reqVo)), HttpStatus.OK);
    }

    // 게시글 삭제(업데이트)
    @DeleteMapping(path = "fake-delete")
    public ResponseEntity<CommonResponse> delete(BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.delete(reqVo)), HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping(path = "delete")
    public ResponseEntity<CommonResponse> realDelete(BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.realDelete(reqVo)), HttpStatus.OK);
    }
}
