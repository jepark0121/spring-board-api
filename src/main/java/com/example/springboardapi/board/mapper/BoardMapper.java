package com.example.springboardapi.board.mapper;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    ArrayList<Board> selectBoardList();

    Board selectBoardOne(@Param("boardId")int boardId);

    int insertBoard(BoardReqVo reqVo);

    void insertBoardTags(BoardReqVo reqVo);

    int updateBoard(BoardReqVo reqVo);

    int deleteBoard(@Param("boardId")int boardId);

    void deleteTagList(@Param("boardId")int boardId);

    int realDeleteBoard(@Param("boardId")int boardId);

    List<Integer> selectTagList(@Param("boardId")int boardId);

}
