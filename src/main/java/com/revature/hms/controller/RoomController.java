package com.revature.hms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.model.Room;
import com.revature.hms.service.RoomService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController

@RequestMapping("room")
public class RoomController {

	@Autowired
	RoomService roomService;

	
	// get room by id

	@GetMapping("/{roomId}")
	public ResponseEntity<Room> getUserById(@PathVariable("roomId") int roomId) {

		Room room = new Room();
		if (roomService.isRoomExists(roomId)) {
			room = roomService.getRoomById(roomId);
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else
			return new ResponseEntity<>(room, HttpStatus.NO_CONTENT);

		

	}

	// insert a room

	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody Room room) {

		int roomId = room.getRoomId();

		if (roomService.isRoomExists(roomId))

			return new ResponseEntity<>(HttpStatus.CONFLICT);

		else {
			roomService.addRoom(room);

			return new ResponseEntity<>(HttpStatus.OK);
		}

		
	}

	// update a room

	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody Room room) {

		int roomId = room.getRoomId();

		if (roomService.isRoomExists(roomId)) {
			roomService.updateRoom(room);

			return  new ResponseEntity<>(HttpStatus.OK);
		} else
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

		
	}

	// delete a room

	@DeleteMapping("/{roomId}")
	public ResponseEntity<String> deletePatient(@PathVariable("roomId") int roomId) {

		if (roomService.isRoomExists(roomId)) {
			roomService.deleteRoom(roomId);

			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

		
	}

	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms() {

		List<Room> roomList = roomService.getAllRooms();
		if(roomList.size()==0)
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
	}

	
	

}
