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

import io.programming.ticket.management.model.TicketModel;
import io.programming.ticket.management.service.TicketService;

@RestController
@RequestMapping()
public class TicketController {

	@Autowired
	TicketService ticketService;

	public TicketController() {

	}

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	@GetMapping("users/{userid}/tickets")
	public ResponseEntity<Object> getTickets(@PathVariable Long userid) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getUserTickets(userid));
	}

	@PostMapping("users/{userid}/tickets")
	public ResponseEntity<Object> createTickets(@PathVariable Long userid, @RequestBody TicketModel ticket) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.createTicket(userid, ticket));
	}

	@GetMapping("users/{userid}/tickets/{ticketid}")
	public ResponseEntity<Object> getTicketDetail(@PathVariable Long userid, @PathVariable Long ticketid) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTicketDetail(userid, ticketid));
	}

	@PutMapping("users/{userid}/tickets/{ticketid}")
	public ResponseEntity<Object> updateTicketDetail(@PathVariable Long userid, @PathVariable Long ticketid,
			@RequestBody TicketModel ticket) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.updateTicketDetail(userid, ticketid, ticket));
	}

	@DeleteMapping("users/{userid}/tickets/{ticketid}")
	public void deleteTicket(@PathVariable Long userid, @PathVariable Long ticketid) {
		ticketService.deleteTicket(userid, ticketid);
	}
}
