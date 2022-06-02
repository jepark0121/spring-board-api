package com.example.springboardapi.board.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateReqVo {
    @Min(value = 1, message = "VALID_NOT_NULL")
    @NotNull(message = "VALID_NOT_NULL")
    private int boardId;
    @NotEmpty(message = "VALID_NOT_NULL")
    private String title;
    @NotNull(message = "VALID_NOT_NULL")
    @NotEmpty(message = "VALID_NOT_NULL")
    @Size(max = 200, message = "LENGTH_ERROR")
    private String contents;
    private List<String> tagList;
    private String updateId;
}
