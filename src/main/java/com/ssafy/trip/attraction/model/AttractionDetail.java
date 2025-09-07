package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AttractionDetail (관광지 상세정보)")
public class AttractionDetail {
	@ApiModelProperty(value = "콘텐츠 아이디")
	private int contentId;
	@ApiModelProperty(value = "카테고리 1")
	private String cat1;
	@ApiModelProperty(value = "카테고리 2")
	private String cat2;
	@ApiModelProperty(value = "카테고리 3")
	private String cat3;
	@ApiModelProperty(value = "생성된 시간")
	private String createdTime;
	@ApiModelProperty(value = "수정된 시간")
	private String modifiedTime;
	@ApiModelProperty(value = "booktour")
	private String booktour;
}
