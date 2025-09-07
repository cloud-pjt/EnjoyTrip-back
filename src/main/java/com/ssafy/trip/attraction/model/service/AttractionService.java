package com.ssafy.trip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.attraction.model.*;
import com.ssafy.trip.user.model.User;

public interface AttractionService {
	List<AreaCode> getAreaCode(int areaCode);

	List<ContentType> getContentType();

	List<Attraction> listAttraction(SearchCondition serchCondition);

	AttractionDescription detailAttraction(int contentId);


	void registTripPlan(TripPlan trip) throws SQLException;
	void updateTripPlan(TripPlan trip) throws SQLException;
	void deleteTripPlan(int planId) throws SQLException;
	List<TripPlan> listPublicTripPlan() throws SQLException;
	List<TripPlan> listUserTripPlan(int userId) throws SQLException;

	void registTripAttraction(TripAttraction tripAttraction) throws SQLException;
	void deleteTripAttraction(TripAttraction tripAttraction) throws SQLException;
	List<Attraction> listTripAttraction(int planId) throws SQLException;

	void registTripMember(TripMember tripMember) throws SQLException;
	void deleteTripMember(TripMember tripMember) throws SQLException;
	List<User> listTripMember(int planId) throws SQLException;
}
