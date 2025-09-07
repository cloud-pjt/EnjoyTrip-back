package com.ssafy.trip.attraction.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "AttractionDescription (관광지 설명)")
public class AttractionDescription {

	private int contentId;
	private String homepage;
	private String overview;
}
