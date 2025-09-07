package com.ssafy.trip.attraction.controller;

import java.util.Collections;
import java.util.List;

import com.ssafy.trip.attraction.model.*;
import com.ssafy.trip.user.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/attractionapi")
@Api(tags = {"관광지 컨트롤러 API V1"})
public class AttractionController {

	private AttractionService service;

	@Autowired
	public AttractionController(AttractionService service) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "시도 리스트", notes = "시도 리스트 반환")
	@GetMapping(value = "/list/{areaCode}")
	public ResponseEntity<?> getAreaCode(@PathVariable int areaCode) {
		log.debug("getAreaCode call");
		try {
			List<AreaCode> list = service.getAreaCode(areaCode);
			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "컨텐츠 타입", notes = "음식점, 관광지등 컨텐츠 타입 반환")
	@GetMapping(value = "/list/type")
	public ResponseEntity<?> getContentType() {
		log.debug("ContentType call");
		try {
			List<ContentType> list = service.getContentType();
			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "관광지 리스트", notes = "검색 정보에 따른 관광지 정보 리스트 반환")
	@GetMapping(value = "/list")
	public ResponseEntity<?> listAttraction(SearchCondition searchCondition) {
		log.debug("listAttraction call");
		System.out.println(searchCondition);
		
		try {
			List<Attraction> list = service.listAttraction(searchCondition);
			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "관광지 세부 정보", notes = "관광지별 세부정보 반환")
	@GetMapping(value = "/detail/{contentId}")
	public ResponseEntity<?> detailAttraction(@PathVariable int contentId) {
		log.debug("detailAttraction call");
		try {
			AttractionDescription detail = service.detailAttraction(contentId);
			return ResponseEntity.
					status(HttpStatus.OK).
					body(detail);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 계획 등록", notes = "여행계획 등록")
	@PostMapping(value = "/plan")
	public ResponseEntity<?> registTripPlan(@RequestBody TripPlan tripPlan) {
		log.debug("tripPlan regist : {}", tripPlan);
		try {

			service.registTripPlan(tripPlan);

			return ResponseEntity.
					status(HttpStatus.CREATED).body(tripPlan);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@ApiOperation(value = "여행 계획 업데이트", notes = "여행 계획 업데이트")
	@PutMapping(value = "/plan")
	public ResponseEntity<?> updateTripPlan(@RequestBody TripPlan tripPlan) {
		log.debug("tripPlan update : {}", tripPlan);
		try {

			service.updateTripPlan(tripPlan);

			return ResponseEntity.
					status(HttpStatus.OK).body("성공적으로 여행 계획 수정하였습니다.");
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 계획 삭제", notes = "여행 계획 삭제")
	@DeleteMapping(value = "/plan/{planId}")
	public ResponseEntity<?> deleteTripPlan(@PathVariable int planId) {
		log.debug("Delete TripPlan : {}", planId);
		try {

			service.deleteTripPlan(planId);

			return ResponseEntity.
					status(HttpStatus.OK).body("성공적으로 여행 계획 삭제하였습니다.");
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "공개된 여행 계획 리스트", notes = "공개된 여행 계획 리스트 반환")
	@GetMapping(value = "/plan/list")
	public ResponseEntity<?> listPublicTripPlan() {
		log.debug("public TripPlan List");
		try {
			List<TripPlan> list = service.listPublicTripPlan();

			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "유저별 여행 계획 리스트", notes = "유저별 여행 계획 리스트 반환")
	@GetMapping(value = "/plan/list/{userId}")
	public ResponseEntity<?> listUserTripPlan(@PathVariable int userId) {
		log.debug("user TripPlan List");
		try {
			List<TripPlan> list = service.listUserTripPlan(userId);

			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 세부 계획 등록", notes = "여행 세부 계획 등록")
	@PostMapping(value = "/plan-att")
	public ResponseEntity<?> registTripAttraction(@RequestBody TripAttraction tripAttraction) {
		log.debug("tripAttraction regist : {}", tripAttraction);
		System.out.println("test");
		try {

			service.registTripAttraction(tripAttraction);

			return ResponseEntity.
					status(HttpStatus.CREATED).body("성공적으로 여행 세부 계획을 등록하였습니다.");
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 세부 계획 삭제", notes = "여행 세부 계획 삭제")
	@DeleteMapping(value = "/plan-att/{planId}/{contentId}")
	public ResponseEntity<?> deleteTripAttraction(@PathVariable int planId, @PathVariable int contentId) {
		log.debug("Delete tripAttraction : {}, {}", planId, contentId);
		try {
			TripAttraction attraction = new TripAttraction();
			attraction.setPlanId(planId);
			attraction.setContentId(contentId);
			service.deleteTripAttraction(attraction);

			return ResponseEntity.
					status(HttpStatus.OK).body("성공적으로 여행 세부 계획 삭제하였습니다.");
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 세부 계획 리스트", notes = "여행 세부 계획 리스트")
	@GetMapping(value = "/plan-att/list/{planId}")
	public ResponseEntity<?> listTripAttraction(@PathVariable int planId) {
		log.debug("TripAttraction List");
		try {
			List<Attraction> list = service.listTripAttraction(planId);

			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 맴버 등록", notes = "여행 맴버 등록")
	@PostMapping(value = "/plan-member")
	public ResponseEntity<?> registTripMember(@RequestBody TripMember tripMember) {
		log.debug("tripMember regist : {}", tripMember);
		try {

			service.registTripMember(tripMember);

			return ResponseEntity.
					status(HttpStatus.CREATED).body("성공적으로 여행 맴버를 등록하였습니다.");
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 맴버 삭제", notes = "여행 맴버 삭제")
	@DeleteMapping(value = "/plan-member/{planId}/{userId}")
	public ResponseEntity<?> deleteTripMember(@PathVariable int planId, @PathVariable int userId) {
		log.debug("Delete TripMember : {}, {}", planId, userId);
		try {
			TripMember member = new TripMember();
			member.setPlanId(planId);
			member.setUserId(userId);
			service.deleteTripMember(member);

			return ResponseEntity.
					status(HttpStatus.OK).body("성공적으로 여행 맴버를 삭제하였습니다.");
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "여행 맴버 리스트", notes = "여행 맴버 리스트")
	@GetMapping(value = "/plan-member/list/{planId}")
	public ResponseEntity<?> listTripMember(@PathVariable int planId) {
		log.debug("TripMember List");
		try {
			List<User> list = service.listTripMember(planId);

			if (list != null) {
				return ResponseEntity.
						status(HttpStatus.OK).
						body(list);
			}
			return ResponseEntity.
					status(HttpStatus.OK).
					body(Collections.EMPTY_LIST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
