package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "여행 맴버 (TripMember)")
public class TripMember {
    @ApiModelProperty(value = "여행계획 아이디")
    private int planId;
    @ApiModelProperty(value = "맴버 아이디")
    private int userId;
    @ApiModelProperty(value = "여행 초대 수락 여부")
    private int status;
}
