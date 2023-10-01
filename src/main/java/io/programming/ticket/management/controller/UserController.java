package io.programming.ticket.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.programming.ticket.management.model.UserModel;
import io.programming.ticket.management.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController() {
	}

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping()
	public ResponseEntity<Object> getUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getListUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
	}

	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody UserModel userRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userRequest));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserModel user) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, user));
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
}
