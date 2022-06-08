package com.example.springboardapi.board.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;
import java.util.List;

@ApiModel(value = "게시글 insert Vo", description = "게시글 인서트를 위한 Vo")
@Getter
@Setter
@NoArgsConstructor
public class BoardInsertReqVo {
    @ApiModelProperty(value = "게시글 ID")
    private int boardId;
    @ApiModelProperty(value = "게시글 제목", example = "디폴트 제목")
    @NotBlank(message = "VALID_NOT_NULL")
    private String title;
    @ApiModelProperty(value = "게시글 내용", example = "디폴트 내용")
    @NotBlank(message = "VALID_NOT_NULL")
    @Size(max = 200, message = "LENGTH_ERROR")
    private String contents;
    @ApiModelProperty(value = "게시글 태그리스트", example = "[\"한글1\", \"한글2\", \"한글5\", \"한글6\"]")
    @UniqueElements(message = "DUPLICATE_ERROR")
    @Size(max = 5, message = "MAX_SIZE_ERROR")
    private List<String> tagList;
    @ApiModelProperty(value = "게시글 작성자 ID" ,example = "Jieun")
    private String registId;

}
