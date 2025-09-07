package com.ssafy.trip.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FileInfo (파일 정보)", description = "파일 저장 이름, 원본 이름 등의 정보를 갖는 Domain Class")
public class FileInfo {
    @ApiModelProperty(value = "글 번호")
    private int articleNo;
    @ApiModelProperty(value = "저장된 폴더")
    private String saveFolder;
    @ApiModelProperty(value = "파일 원본 이름")
    private String originalFile;
    @ApiModelProperty(value = "파일 저장된 이름")
    private String saveFile;
}
