package com.revature.hms.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.Booking;




public interface BookingRepository extends CrudRepository<Booking,Integer>{
	
	public List<Booking>findByCancellation(String cancellation);
	public List<Booking>findByRoomNumberGreaterThan(int roomNumber);
	public Optional<Booking> findByRoomNumber(int roomNumber);
	
	public Booking findByCustomerUserName(String userName);
	@Transactional
	public String deleteByCustomerUserName(String userName);

}