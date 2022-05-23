package com.example.springboardapi.board.vo;

import com.example.springboardapi.board.model.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardResVo {
    private List<Board> data;
}
