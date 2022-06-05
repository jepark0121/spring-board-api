package com.example.springboardapi.board.vo;

import com.example.springboardapi.board.model.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "게시글 API ResponseVo")
@Getter
@Setter
@NoArgsConstructor
public class BoardResVo {
    @ApiModelProperty(value = "응답데이터")
    private List<Board> data;
}
