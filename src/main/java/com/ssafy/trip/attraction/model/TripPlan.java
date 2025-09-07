package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel(value = "TripPlan (여행 계획 정보)")
public class TripPlan {
    @ApiModelProperty(value = "계획 제목")
    private String title;
    @ApiModelProperty(value = "계획 아이디")
    private int planId;
    @ApiModelProperty(value = "생성자 아이디")
    private int userId;
    @ApiModelProperty(value = "생성자 아이디")
    private String userEmail;
    @ApiModelProperty(value = "여행 시작일")
    private Timestamp startDate;
    @ApiModelProperty(value = "여행 종료일")
    private Timestamp endDate;
    @ApiModelProperty(value = "공개 상태")
    private boolean publicStatus;
}
