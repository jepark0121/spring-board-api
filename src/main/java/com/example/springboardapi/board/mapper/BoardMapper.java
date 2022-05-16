package com.example.springboardapi.board.mapper;

import com.example.springboardapi.board.model.Board;
import com.example.springboardapi.board.vo.BoardReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    ArrayList<Board> selectBoardList();

    Board selectBoardOne(BoardReqVo reqVo);

    int insertBoard(BoardReqVo reqVo);

    void insertBoardTags(BoardReqVo reqVo);

    int updateBoard(BoardReqVo reqVo);

    int deleteBoard(BoardReqVo reqVo);

    void deleteTagList(BoardReqVo reqVo);

    int realDeleteBoard(BoardReqVo reqVo);

    List<Integer> selectTagList(BoardReqVo reqVo);

}
