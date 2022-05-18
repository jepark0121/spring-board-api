package com.example.springboardapi.board.controller;


import com.example.springboardapi.board.vo.BoardReqVo;
import com.example.springboardapi.board.service.BoardService;
import com.example.springboardapi.exception.CommonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(path = "/board/")
public class BoardApiController {

    private final BoardService boardService;

    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 조회
    @GetMapping(path = "list")
    public ResponseEntity<CommonResponse> list() throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectList()), HttpStatus.OK);
    }

    // 게시글 하나 조회
    @GetMapping(path = "one/{boardId}")
    public ResponseEntity<CommonResponse> one(@PathVariable int boardId) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectOne(boardId)), HttpStatus.OK);
    }

    // 게시글 저장
    @PostMapping(path = "add")
    public ResponseEntity<CommonResponse> save(@RequestBody BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.save(reqVo)), HttpStatus.OK);
    }

    // 게시글 업데이트
    @PutMapping(path = "update")
    public ResponseEntity<CommonResponse> update(@RequestBody BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.update(reqVo)), HttpStatus.OK);
    }

    // 게시글 삭제(업데이트)
    @DeleteMapping(path = "fake-delete/{boardId}")
    public ResponseEntity<CommonResponse> delete(@PathVariable int boardId) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.delete(boardId)), HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping(path = "delete/{boardId}")
    public ResponseEntity<CommonResponse> realDelete(@PathVariable int boardId) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.realDelete(boardId)), HttpStatus.OK);
    }
}
