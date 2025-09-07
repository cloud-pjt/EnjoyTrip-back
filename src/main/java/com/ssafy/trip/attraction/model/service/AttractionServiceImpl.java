package com.ssafy.trip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.attraction.model.*;
import com.ssafy.trip.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.attraction.model.dao.AttractionDao;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionDao dao;
	
	@Autowired
	public AttractionServiceImpl(AttractionDao attractionDao) {
		super();
		this.dao = attractionDao;
	}

	@Override
	public List<AreaCode> getAreaCode(int areaCode) {
		if(areaCode == 0) {
			return dao.getSido();
		}
		return dao.getGugun(areaCode);
	}

	@Override
	public List<Attraction> listAttraction(SearchCondition serchCondition) {
		return dao.listAttraction(serchCondition);
	}

	@Override
	public AttractionDescription detailAttraction(int contentId) {
		return dao.detailAttraction(contentId);
	}

	@Override
	public void registTripPlan(TripPlan trip) throws SQLException {
		dao.registTripPlan(trip);
	}

	@Override
	public void updateTripPlan(TripPlan trip) throws SQLException {
		dao.updateTripPlan(trip);
	}

	@Override
	public void deleteTripPlan(int planId) throws SQLException {
		dao.deleteTripPlan(planId);
	}

	@Override
	public List<TripPlan> listPublicTripPlan() throws SQLException {
		return dao.listPublicTripPlan();
	}

	@Override
	public List<TripPlan> listUserTripPlan(int userId) throws SQLException {
		return dao.listUserTripPlan(userId);
	}

	@Override
	public void registTripAttraction(TripAttraction tripAttraction) throws SQLException {
		dao.registTripAttraction(tripAttraction);
	}

	@Override
	public void deleteTripAttraction(TripAttraction tripAttraction) throws SQLException {
		dao.deleteTripAttraction(tripAttraction);
	}

	@Override
	public List<Attraction> listTripAttraction(int planId) throws SQLException {
		return dao.listTripAttraction(planId);
	}

	@Override
	public void registTripMember(TripMember tripMember) throws SQLException {
		dao.registTripMember(tripMember);
	}

	@Override
	public void deleteTripMember(TripMember tripMember) throws SQLException {
		dao.deleteTripMember(tripMember);
	}

	@Override
	public List<User> listTripMember(int planId) throws SQLException {
		return dao.listTripMember(planId);
	}

	@Override
	public List<ContentType> getContentType() {
		return dao.getContentType();
	}

}
