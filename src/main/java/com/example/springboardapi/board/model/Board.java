package com.example.springboardapi.board.model;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private int boardId;
    private String title;
    private String contents;
    private String tagList;
    private String registId;
}
