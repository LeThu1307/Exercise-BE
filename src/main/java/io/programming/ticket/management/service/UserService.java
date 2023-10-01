package io.programming.ticket.management.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programming.ticket.management.entity.User;
import io.programming.ticket.management.exception.BadRequestException;
import io.programming.ticket.management.model.UserModel;
import io.programming.ticket.management.repository.UserRepository;
import io.programming.ticket.management.validation.ValidationUser;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getListUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User Id not found!"));
	}

	public User createUser(UserModel userRequest) {
		ValidationUser.validateUser(userRequest);
		if (isUsernameExist(userRequest.getUsername())) {
			throw new BadRequestException("User name is exist!");
		}
		return userRepository.save(userToEntity(userRequest));
	}

	public User updateUser(Long userId, UserModel userRequest) {
		if (!isUserIdExist(userId)) {
			throw new BadRequestException("User id isn't exist!");
		}
		ValidationUser.validateUpdateUser(userId, userRequest);
		userRequest.setUserId(userId);
		return userRepository.save(userToEntity(userRequest));
	}

	public void deleteUser(Long userId) {
		if (!isUserIdExist(userId)) {
			throw new BadRequestException("User isn't exist!");
		}
		userRepository.deleteById(userId);
	}

	public boolean isUsernameExist(String username) {
		if (userRepository.findByUsername(username) != null && !userRepository.findByUsername(username).isEmpty())
			return true;
		return false;
	}

	public boolean isUserIdExist(Long userId) {
		return userRepository.findById(userId).orElse(null) != null;
	}

	public static User userToEntity(UserModel userModel) {
		User user = new User();
		if (userModel.getUserId() != null) {
			user.setId(userModel.getUserId());
		}
		if (userModel.getUsername() != null) {
			user.setUsername(userModel.getUsername());
		}
		if (userModel.getEmail() != null) {
			user.setEmail(userModel.getEmail());
		}
		user.setUserRole(userModel.getUserRole());
		return user;
	}

}
