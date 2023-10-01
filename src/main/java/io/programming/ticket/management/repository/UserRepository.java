package io.programming.ticket.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.programming.ticket.management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByUsername(String username);
}
