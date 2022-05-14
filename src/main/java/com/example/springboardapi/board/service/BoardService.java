package com.example.springboardapi.board.service;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardReqVo;
import com.example.springboardapi.board.mapper.BoardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BoardService {

    final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public ArrayList<Board> selectList(BoardReqVo reqVo) {
        return boardMapper.selectBoardList();
    }

    public Board selectOne(BoardReqVo reqVo) {
        return boardMapper.selectBoardOne(reqVo);
    }

    @Transactional
    public void save(BoardReqVo reqVo) {
        // 게시글 저장
        boardMapper.insertBoard(reqVo);
        // 게시글 태그 저장
        boardMapper.insertBoardTags(reqVo);
    }

    @Transactional
    public void update(BoardReqVo reqVo) {
        // 게시글 수정
        boardMapper.updateBoard(reqVo);
        // 태그 수정 추가 필요
    }

    @Transactional
    public void delete(BoardReqVo reqVo) {
        // 게시글 삭제
        boardMapper.deleteBoard(reqVo);
    }


}
