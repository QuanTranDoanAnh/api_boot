package vn.quantda.server.service;

import java.util.List;

import vn.quantda.server.entity.Booking;

public interface BookingService {

	List<Booking> findUserBookings(String emailId); 
}
