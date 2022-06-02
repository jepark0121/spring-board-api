package com.example.springboardapi.board.vo;

import com.example.springboardapi.board.model.Board;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardResVo {
    private List<Board> data;
}
