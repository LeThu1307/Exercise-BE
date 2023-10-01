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
@RequestMapping("/tickets")
public class AdminController {
	@Autowired
	TicketService ticketService;

	@GetMapping()
	public ResponseEntity<Object> getTickets() {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getAllTickets());
	}

	@PostMapping()
	public ResponseEntity<Object> createTickets(@RequestBody TicketModel ticket) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.createTicket(ticket));
	}

	@GetMapping("/{ticketid}")
	public ResponseEntity<Object> getTicketDetail(@PathVariable Long ticketid) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTicketDetail(ticketid));
	}

	@PutMapping("/{ticketid}")
	public ResponseEntity<Object> updateTicketDetail(@PathVariable Long ticketid, @RequestBody TicketModel ticket) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.updateTicketDetail(ticketid, ticket));
	}

	@DeleteMapping("/{ticketid}")
	public void deleteTicket(@PathVariable Long ticketid) {
		ticketService.deleteTicket(ticketid);
	}

}
