package com.example.springboardapi.board.vo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardReqVo {
    private int boardId;
    private String title;
    private String contents;
    private List<String> tagList;
    private String registId;
    private String updateId;
}
