package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "ContentType (콘텐츠 아이디, 이름)")
public class ContentType {

	private int contentTypeId;
	private String contentTypeName;
}
