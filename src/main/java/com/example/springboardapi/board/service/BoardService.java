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
import java.util.List;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Transactional(readOnly = true)
    public ArrayList<Board> selectList() {
        return boardMapper.selectBoardList();
    }

    @Transactional(readOnly = true)
    public Board selectOne(int boardId) {
        return boardMapper.selectBoardOne(boardId);
    }

    @Transactional
    public int save(BoardReqVo reqVo) {
        int count = 0;
        // 게시글 저장
        count = boardMapper.insertBoard(reqVo);
        // 게시글 태그 저장
        boardMapper.insertBoardTags(reqVo);
        return count;
    }

    @Transactional
    public int update(BoardReqVo reqVo) {
        int count = 0;
        // 게시글 수정
        count = boardMapper.updateBoard(reqVo);
        // 태그 수정
        List<String> tagList = reqVo.getTagList();
        if(tagList.size() > 0) {
            boardMapper.deleteTagList(reqVo.getBoardId());
            boardMapper.insertBoardTags(reqVo);
        }
        return count;
    }

    @Transactional
    public int delete(int boardId) {
        // 게시글 삭제
        return boardMapper.deleteBoard(boardId);
    }

    @Transactional
    public int realDelete(int boardId) {
        List<Integer> tagList = boardMapper.selectTagList(boardId);
        // 연관 태그 있는 경우 삭제
        if(tagList.size() > 0) {
            boardMapper.deleteTagList(boardId);
        }
        // 게시글 삭제
        return boardMapper.realDeleteBoard(boardId);
    }


}
