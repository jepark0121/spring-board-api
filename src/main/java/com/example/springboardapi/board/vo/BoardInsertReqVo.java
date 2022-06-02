package com.example.springboardapi.board.vo;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardInsertReqVo {
    private int boardId;
    @NotEmpty(message = "VALID_NOT_NULL")
    private String title;
    @NotNull(message = "VALID_NOT_NULL")
    @NotEmpty(message = "VALID_NOT_NULL")
    @Size(max = 200, message = "LENGTH_ERROR")
    private String contents;
    private List<String> tagList;
    private String registId;

}
