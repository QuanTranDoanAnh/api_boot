package vn.quantda.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.quantda.server.entity.Booking;
import vn.quantda.server.entity.UserDetail;
import vn.quantda.server.repository.BookingRepository;
import vn.quantda.server.repository.UserRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public List<Booking> findUserBookings(String emailId) {
		UserDetail userDetail = userRepository.findByEmail(emailId);
		List<Booking> bookings = bookingRepository.findByCreatedBy(userDetail.getId());
		return bookings;
	}

}
