package vn.quantda.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.quantda.server.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByCreatedBy(Long userId);
}
