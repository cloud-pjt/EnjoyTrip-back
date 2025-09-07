package com.ssafy.trip.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Board (게시판 정보)", description = "글번호, 글내용 등의 정보를 가진 Domain Class")
public class Board {

    @ApiModelProperty(value = "글 번호")
    private int articleNo;
    @ApiModelProperty(value = "작성자 아이디")
    private String userId;
    @ApiModelProperty(value = "작성자명")
    private String userName;
    @ApiModelProperty(value = "주제")
    private String subject;
    @ApiModelProperty(value = "글 내용")
    private String content;
    @ApiModelProperty(value = "조회수")
    private int hit;
    @ApiModelProperty(value = "등록 시간")
    private String registerTime;
    @ApiModelProperty(value = "업로드된 파일")
    private FileInfo fileInfo;

}
