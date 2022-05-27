package com.example.springboardapi.board.controller;


import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.service.MailService;
import com.example.springboardapi.board.vo.BoardReqVo;
import com.example.springboardapi.board.service.BoardService;
import com.example.springboardapi.exception.CommonResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(path = "/board/")
public class BoardApiController {

    private final BoardService boardService;

    private final MailService mailService;

    public BoardApiController(BoardService boardService, MailService mailService) {
        this.boardService = boardService;
        this.mailService = mailService;
    }

    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회합니다.")
    @GetMapping(path = "list")
    public ResponseEntity<CommonResponse> list() throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectList()), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 단건 조회", notes = "게시글 단건을 조회합니다.")
    @GetMapping(path = "one/{boardId}")
    public ResponseEntity<CommonResponse> one(@PathVariable int boardId) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectOne(boardId)), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 저장", notes = "게시글을 저장합니다.")
    @PostMapping(path = "add")
    public ResponseEntity<CommonResponse> save(@RequestBody BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.save(reqVo)), HttpStatus.OK);
    }

    // 게시글 업데이트
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @PutMapping(path = "update")
    public ResponseEntity<CommonResponse> update(@RequestBody BoardReqVo reqVo) throws Exception {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.update(reqVo)), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제여부를 업데이트 합니다.")
    @DeleteMapping(path = "fake-delete/{boardId}")
    public ResponseEntity<CommonResponse> delete(@PathVariable int boardId) throws Exception {
        Board board = boardService.selectOne(boardId);
        mailService.sendMail(board.getTitle());

        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.delete(boardId)), HttpStatus.OK);
    }

    // 게시글 삭제
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제 합니다.")
    @DeleteMapping(path = "delete/{boardId}")
    public ResponseEntity<CommonResponse> realDelete(@PathVariable int boardId) throws Exception {
        Board board = boardService.selectOne(boardId);
        mailService.sendMail(board.getTitle());

        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.realDelete(boardId)), HttpStatus.OK);
    }
}
