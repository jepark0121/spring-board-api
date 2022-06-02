package com.example.springboardapi.board.service;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardInsertReqVo;
import com.example.springboardapi.board.mapper.BoardMapper;
import com.example.springboardapi.board.vo.BoardUpdateReqVo;
import com.example.springboardapi.util.MaskingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Transactional(readOnly = true)
    public ArrayList<Board> selectList() {
        ArrayList<Board> list = boardMapper.selectBoardList();

        list.stream().forEach(v -> {
            v.setRegistId(MaskingUtil.nameMasking(v.getRegistId()));
        });

        return list;
    }

    @Transactional(readOnly = true)
    public Board selectOne(int boardId) {
        Board board = boardMapper.selectBoardOne(boardId);
        board.setRegistId(MaskingUtil.nameMasking(board.getRegistId()));
        return board;
    }


    public int save(BoardInsertReqVo reqVo) {
        int count = 0;
        // 게시글 저장
        count = boardMapper.insertBoard(reqVo);
        // 게시글 태그 저장
        BoardUpdateReqVo updateReqVo = new BoardUpdateReqVo();
        updateReqVo.setBoardId(reqVo.getBoardId());
        updateReqVo.setTitle(reqVo.getTitle());
        updateReqVo.setContents(reqVo.getContents());
        updateReqVo.setTagList(reqVo.getTagList());
        boardMapper.insertBoardTags(updateReqVo);

        return count;
    }

    public int update(BoardUpdateReqVo reqVo) {
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

    public int delete(int boardId) {
        // 게시글 삭제
        return boardMapper.deleteBoard(boardId);
    }

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
