package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "SearchCondition (리스트 검색 정보)", description = "시도, 구군에 대한 코드와 이름을 가진 Class")
public class SearchCondition {
	private int sidoCode;
	private int gugunCode;
	private int contentTypeId;
	private String word;
}
