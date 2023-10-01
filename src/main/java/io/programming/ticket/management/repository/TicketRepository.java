package io.programming.ticket.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.programming.ticket.management.entity.Ticket;
import io.programming.ticket.management.entity.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	public List<Ticket> findAllByAssignee(User user);

}
