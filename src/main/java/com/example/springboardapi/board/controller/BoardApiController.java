package com.example.springboardapi.board.controller;


import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.model.Mail;
import com.example.springboardapi.board.service.MailService;
import com.example.springboardapi.board.vo.BoardInsertReqVo;
import com.example.springboardapi.board.service.BoardService;
import com.example.springboardapi.board.vo.BoardUpdateReqVo;
import com.example.springboardapi.exception.CommonResponse;
import com.example.springboardapi.exception.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;

@Log4j2
@Validated
@Api(tags = {"Board API Test"})
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
    @ApiImplicitParam(name = "boardId", value = "게시글 ID", required = true, dataType = "int", paramType = "path", defaultValue = "78")
    @GetMapping(path = "one/{boardId}")
    public ResponseEntity<CommonResponse> one(@PathVariable @Min(value = 1, message = "VALID_NOT_NULL")
                                                  @NotNull(message = "VALID_NOT_NULL") int boardId) {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.selectOne(boardId)), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 저장", notes = "게시글을 저장합니다.")
    // @ApiImplicitParam(name = "reqVo", required = true, dataTypeClass = BoardInsertReqVo.class, paramType = "body")
    @PostMapping(path = "add")
    public ResponseEntity<CommonResponse> save(@RequestBody @Valid BoardInsertReqVo reqVo) {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.save(reqVo)), HttpStatus.OK);
    }

    // 게시글 업데이트
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    // @ApiImplicitParam(name = "reqVo", required = true, dataTypeClass = BoardUpdateReqVo.class, paramType = "body")
    @PutMapping(path = "update")
    public ResponseEntity<CommonResponse> update(@RequestBody @Valid BoardUpdateReqVo reqVo) {
        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.update(reqVo)), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제여부를 업데이트 합니다.")
    @ApiImplicitParam(name = "boardId", value = "게시글 ID", required = true, dataType = "int", paramType = "path", defaultValue = "55")
    @DeleteMapping(path = "fake-delete/{boardId}")
    public ResponseEntity<CommonResponse> delete(@PathVariable @Min(value = 1, message = "VALID_NOT_NULL")
                                                     @NotNull(message = "VALID_NOT_NULL") int boardId) throws Exception {
        Board board = boardService.selectOne(boardId);

        DateTimeFormatter dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String subject = "[게시글 삭제] “" + board.getTitle() + "” 이(가) 삭제 되었습니다.";
        String text = dateTime.print(new DateTime()) + "에 해당 게시글이 삭제되었습니다.";

        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setText(text);

        mailService.sendMail(mail);

        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.delete(boardId)), HttpStatus.OK);
    }

    // 게시글 삭제
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제 합니다.")
    @ApiImplicitParam(name = "boardId", value = "게시글 ID", required = true, dataType = "int", paramType = "path", defaultValue = "45")
    @DeleteMapping(path = "delete/{boardId}")
    public ResponseEntity<CommonResponse> realDelete(@PathVariable @Min(value = 1, message = "VALID_NOT_NULL")
                                                     @NotNull(message = "VALID_NOT_NULL") int boardId) throws Exception {
        Board board = boardService.selectOne(boardId);

        DateTimeFormatter dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String subject = "[게시글 삭제] “" + board.getTitle() + "” 이(가) 삭제 되었습니다.";
        String text = dateTime.print(new DateTime()) + "에 해당 게시글이 삭제되었습니다.";

        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setText(text);

        mailService.sendMail(mail);

        return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.realDelete(boardId)), HttpStatus.OK);
    }

    @ApiOperation(value = "엑셀 데이터 업로드", notes = "엑셀데이터를 업로드 합니다.")
    @PostMapping(path = "upload/excel", consumes = {"multipart/form-data"})
    public ResponseEntity<CommonResponse> uploadExcel(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) throws Exception {
        String message = "";
        int code = 0;
        HttpStatus httpStatus = null;
        if (uploadFile.isEmpty()) {
           message = ErrorCode.FILE_EMPTY.getMessage();
           code = ErrorCode.FILE_EMPTY.getStatus();
           httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<CommonResponse>(new CommonResponse(message, code), httpStatus);
        } else {
            String originalFilename = uploadFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            log.info(ext);
            if (!ext.equals("xlsx") && !ext.equals("xls")) {
                message = ErrorCode.FILE_EXT_ERROR.getMessage();
                code = ErrorCode.FILE_EXT_ERROR.getStatus();
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                return new ResponseEntity<CommonResponse>(new CommonResponse(message, code), httpStatus);
            } else {
                return new ResponseEntity<CommonResponse>(new CommonResponse(boardService.uploadExcel(uploadFile)), HttpStatus.OK);
            }

        }
    }

    @ApiOperation(value = "게시글 리스트 엑셀 다운로드", notes = "게시글을 엑셀파일로 다운로드 합니다.")
    @GetMapping(path = "download/excel")
    public void downloadExcel(HttpServletResponse res){
        List<Board> list = boardService.selectList();
        boardService.downloadExcel(list, res);
    }
}
