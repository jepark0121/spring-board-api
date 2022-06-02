package com.example.springboardapi.board.vo;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardInsertReqVo {
    private int boardId;
    @NotBlank(message = "VALID_NOT_NULL")
    private String title;
    @NotBlank(message = "VALID_NOT_NULL")
    @Size(max = 200, message = "LENGTH_ERROR")
    private String contents;
    @UniqueElements(message = "DUPLICATE_ERROR")
    @Size(max = 5, message = "MAX_SIZE_ERROR")
    private List<String> tagList;
    private String registId;

}
