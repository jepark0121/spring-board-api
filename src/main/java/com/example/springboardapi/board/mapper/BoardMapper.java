package com.example.springboardapi.board.mapper;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardInsertReqVo;
import com.example.springboardapi.board.vo.BoardUpdateReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    ArrayList<Board> selectBoardList();

    Board selectBoardOne(@Param("boardId")int boardId);

    int insertBoard(BoardInsertReqVo reqVo);

    void insertBoardTags(BoardUpdateReqVo reqVo);

    int updateBoard(BoardUpdateReqVo reqVo);

    int deleteBoard(@Param("boardId")int boardId);

    void deleteTagList(@Param("boardId")int boardId);

    int realDeleteBoard(@Param("boardId")int boardId);

    List<Integer> selectTagList(@Param("boardId")int boardId);

}
