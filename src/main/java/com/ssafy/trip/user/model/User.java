package com.ssafy.trip.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User (유저정보)", description = "아이디, 비번, 이름을 가진 Domain Class")
public class User {
	@ApiModelProperty(value = "유저 아이디값")
	private int userId;
	@ApiModelProperty(value = "로그인에 사용될 이메일")
	private String userEmail;
	@ApiModelProperty(value = "유저 비밀번호")
	private String userPassword;
	@ApiModelProperty(value = "유저 이름")
	private String userName;
	@ApiModelProperty(value = "가입 일자")
	private String joinDate;
	@ApiModelProperty(value = "유저 이미지")
	private String userImg;
	@ApiModelProperty(value = "refreshToken")
	private String refreshToken;
}
