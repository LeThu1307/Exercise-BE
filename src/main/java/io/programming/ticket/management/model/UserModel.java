package io.programming.ticket.management.model;

import io.programming.ticket.management.contanst.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {

	private Long userId;
	private String username;
	private String email;
	private UserRole userRole = UserRole.USER;

	public UserModel() {
		super();
	}

	public UserModel(Long userId, String username, String email, UserRole userRole) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.userRole = userRole;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
