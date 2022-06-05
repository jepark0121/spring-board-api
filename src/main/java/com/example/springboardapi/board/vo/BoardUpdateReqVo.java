package com.example.springboardapi.board.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.util.List;

@ApiModel(value = "게시글 update Vo", description = "게시글 업데이트를 위한 Vo")
@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateReqVo {
    @ApiModelProperty(value = "게시글 ID")
    @Min(value = 1, message = "VALID_NOT_NULL")
    @NotNull(message = "VALID_NOT_NULL")
    private int boardId;
    @ApiModelProperty(value = "게시글 제목")
    @NotBlank(message = "VALID_NOT_NULL")
    private String title;
    @ApiModelProperty(value = "게시글 내용")
    @NotBlank(message = "VALID_NOT_NULL")
    @Size(max = 200, message = "LENGTH_ERROR")
    private String contents;
    @ApiModelProperty(value = "게시글 태그리스트")
    @UniqueElements(message = "DUPLICATE_ERROR")
    @Size(max = 5, message = "MAX_SIZE_ERROR")
    private List<String> tagList;
    @ApiModelProperty(value = "게시글 수정자 ID")
    private String updateId;
}
