package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "TripAttraction (여행 계획 세부 정보)")
public class TripAttraction {
    @ApiModelProperty(value = "여행 계획 아이디")
    private int planId;
    @ApiModelProperty(value = "여행지 아이디")
    private int contentId;
    @ApiModelProperty(value = "여행지 방문일")
    private Timestamp visitDate;
}
