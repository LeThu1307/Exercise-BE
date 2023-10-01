package io.programming.ticket.management.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programming.ticket.management.entity.Ticket;
import io.programming.ticket.management.entity.User;
import io.programming.ticket.management.exception.BadRequestException;
import io.programming.ticket.management.exception.PermissionDeniedException;
import io.programming.ticket.management.model.TicketModel;
import io.programming.ticket.management.repository.TicketRepository;
import io.programming.ticket.management.repository.UserRepository;
import io.programming.ticket.management.validation.ValidationTicket;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	public List<Ticket> getUserTickets(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User Id not found!"));
		return ticketRepository.findAllByAssignee(user);
	}

	public Ticket createTicket(Long userId, TicketModel ticketRrequest) {
		if (userRepository.findById(userId) == null || userRepository.findById(userId).isEmpty()) {
			throw new BadRequestException("User Id not found!");
		}
		ValidationTicket.validateAddTicket(ticketRrequest);
		if (userRepository.findById(ticketRrequest.getAssigneeId()) == null
				|| userRepository.findById(ticketRrequest.getAssigneeId()).isEmpty()) {
			throw new BadRequestException("Assignee Id not found!");
		}
		if (ticketRrequest.getReporterId() != null && userRepository.findById(ticketRrequest.getReporterId()) == null) {
			throw new BadRequestException("Reporter Id not found!");
		}
		if (ticketRrequest.getReporterId() == null) {
			ticketRrequest.setReporterId(userId);
		}
		Ticket ticketEntity = new Ticket();
		return ticketRepository.save(ticketToEntity(ticketEntity, ticketRrequest));
	}

	public static Ticket ticketToEntity(Ticket ticket, TicketModel ticketModel) {

		if (ticketModel.getId() != null) {
			ticket.setId(ticketModel.getId());
		}

		if (ticketModel.getStatus() != null) {
			ticket.setStatus(ticketModel.getStatus());
		}

		if (ticketModel.getTittle() != null) {
			ticket.setTittle(ticketModel.getTittle());
		}

		if (ticketModel.getAssigneeId() != null) {
			ticket.setAssigneeId(ticketModel.getAssigneeId());
		}

		if (ticketModel.getDescription() != null) {
			ticket.setDescription(ticketModel.getDescription());
		}

		if (ticketModel.getReporterId() != null) {
			ticket.setReporterId(ticketModel.getReporterId());
		}

		if (ticketModel.getCreateTime() != null) {
			ticket.setCreateTime(ticketModel.getCreateTime());
		}

		if (ticketModel.getUpdatedTime() != null) {
			ticket.setUpdatedTime(ticketModel.getUpdatedTime());
		}

		return ticket;
	}

	public Ticket getTicketDetail(Long userid, Long ticketid) {
		if (userRepository.findById(userid) == null || userRepository.findById(userid).isEmpty()) {
			throw new BadRequestException("User Id not found!");
		}
		return ticketRepository.findById(ticketid)
				.orElseThrow(() -> new NoSuchElementException("Ticket Id not found!"));
	}

	public Ticket updateTicketDetail(Long userid, Long ticketid, TicketModel ticketRequest) {
		User user = userRepository.findById(userid).orElseThrow(() -> new BadRequestException("User Id not found!"));
		Ticket ticket = ticketRepository.findById(ticketid)
				.orElseThrow(() -> new BadRequestException("Ticket Id not found!"));
		if (ticket.getReporterId() != userid && ticket.getAssigneeId() != userid
				&& !"ADMINISTRATOR".equalsIgnoreCase(user.getUserRole().toString())) {
			throw new PermissionDeniedException("Permission Denied!");
		}
		if (ticketRequest.getAssigneeId() != null && (userRepository.findById(ticketRequest.getAssigneeId()) == null
				|| userRepository.findById(ticketRequest.getAssigneeId()).isEmpty())) {
			throw new BadRequestException("Assignee Id not found!");
		}
		if (ticketRequest.getReporterId() != null && (userRepository.findById(ticketRequest.getReporterId()) == null
				|| userRepository.findById(ticketRequest.getReporterId()).isEmpty())) {
			throw new BadRequestException("Reporter Id not found!");
		}
		ValidationTicket.validateUpdateTicket(ticketid, ticketRequest);
		Ticket ticketEntity = ticketRepository.findById(ticketid).orElse(null);
		return ticketRepository.save(ticketToEntity(ticketEntity, ticketRequest));
	}

	public void deleteTicket(Long userid, Long ticketid) {
		User user = userRepository.findById(userid).orElseThrow(() -> new BadRequestException("User Id not found!"));
		Ticket ticket = ticketRepository.findById(ticketid)
				.orElseThrow(() -> new BadRequestException("Ticket Id not found!"));
		if (ticket.getReporterId() != userid && !"ADMINISTRATOR".equalsIgnoreCase(user.getUserRole().toString())) {
			throw new PermissionDeniedException("Permission Denied!");
		}
		ticketRepository.deleteById(ticketid);
	}

	// Administration
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	public Ticket getTicketDetail(Long ticketid) {
		return ticketRepository.findById(ticketid)
				.orElseThrow(() -> new NoSuchElementException("Ticket Id not found!"));
	}

	public Ticket createTicket(TicketModel ticketRrequest) {
		ValidationTicket.validateAddTicket(ticketRrequest);
		if (userRepository.findById(ticketRrequest.getAssigneeId()) == null
				|| userRepository.findById(ticketRrequest.getAssigneeId()).isEmpty()) {
			throw new BadRequestException("Assignee Id not found!");
		}
		if (ticketRrequest.getReporterId() == null
				|| userRepository.findById(ticketRrequest.getReporterId()).isEmpty()) {
			throw new BadRequestException("Reporter Id not found!");
		}
		Ticket ticketEntity = new Ticket();
		return ticketRepository.save(ticketToEntity(ticketEntity, ticketRrequest));
	}

	public Object updateTicketDetail(Long ticketid, TicketModel ticketRequest) {
		if (ticketRequest.getAssigneeId() != null && (userRepository.findById(ticketRequest.getAssigneeId()) == null
				|| userRepository.findById(ticketRequest.getAssigneeId()).isEmpty())) {
			throw new BadRequestException("Assignee Id not found!");
		}
		if (ticketRequest.getReporterId() != null && (userRepository.findById(ticketRequest.getReporterId()) == null
				|| userRepository.findById(ticketRequest.getReporterId()).isEmpty())) {
			throw new BadRequestException("Reporter Id not found!");
		}
		ValidationTicket.validateUpdateTicket(ticketid, ticketRequest);
		Ticket ticketEntity = ticketRepository.findById(ticketid).orElse(null);
		return ticketRepository.save(ticketToEntity(ticketEntity, ticketRequest));
	}

	public void deleteTicket(Long ticketid) {
		ticketRepository.findById(ticketid).orElseThrow(() -> new BadRequestException("Ticket Id not found!"));
		ticketRepository.deleteById(ticketid);
	}
}
