package io.programming.ticket.management.validation;

import io.programming.ticket.management.exception.BadRequestException;
import io.programming.ticket.management.model.UserModel;

public class ValidationUser {

	public static void validateUser(UserModel user) {
		if (user.getUserId() != null && user.getUserId() != 0) {
			throw new BadRequestException("User Id mustn't be entered!");
		}
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			throw new BadRequestException("Please enter user name!");
		}
	}

	public static void validateUpdateUser(Long userId, UserModel user) {
		if (userId != user.getUserId()) {
			throw new BadRequestException("User isn't match");
		}
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			throw new BadRequestException("Please enter user name!");
		}
	}

}
