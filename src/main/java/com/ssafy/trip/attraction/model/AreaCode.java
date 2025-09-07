package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "AreaCode (시도, 구군 정보)", description = "시도, 구군에 대한 코드와 이름을 가진 Class")
public class AreaCode {
	private int sidoCode;
	private String sidoName;
	private String sidoImg;
	private int gugunCode;
	private String gugunName;
}
