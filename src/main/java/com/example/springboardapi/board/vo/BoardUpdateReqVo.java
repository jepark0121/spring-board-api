package com.example.springboardapi.board.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateReqVo {
    @Min(value = 1, message = "VALID_NOT_NULL")
    @NotNull(message = "VALID_NOT_NULL")
    private int boardId;
    @NotBlank(message = "VALID_NOT_NULL")
    private String title;
    @NotBlank(message = "VALID_NOT_NULL")
    @Size(max = 200, message = "LENGTH_ERROR")
    private String contents;
    @UniqueElements(message = "DUPLICATE_ERROR")
    @Size(max = 5, message = "MAX_SIZE_ERROR")
    private List<String> tagList;
    private String updateId;
}
