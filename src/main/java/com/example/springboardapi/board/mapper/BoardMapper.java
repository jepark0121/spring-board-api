package com.example.springboardapi.board.mapper;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface BoardMapper {
    ArrayList<Board> selectBoardList();

    Board selectBoardOne(BoardReqVo reqVo);

    void insertBoard(BoardReqVo reqVo);

    void insertBoardTags(BoardReqVo reqVo);

    void updateBoard(BoardReqVo reqVo);

    void deleteBoard(BoardReqVo reqVo);
}
