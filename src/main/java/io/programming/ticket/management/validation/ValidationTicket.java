package io.programming.ticket.management.validation;

import io.programming.ticket.management.contanst.Status;
import io.programming.ticket.management.exception.BadRequestException;
import io.programming.ticket.management.model.TicketModel;

public class ValidationTicket {

	public static void validateAddTicket(TicketModel ticket) {
		if (ticket.getId() != null) {
			throw new BadRequestException("Ticket Id mustn't be entered!");
		}
		if (ticket.getTittle() == null || ticket.getTittle().isEmpty()) {
			throw new BadRequestException("Tittle must be entered!");
		}
		if (ticket.getAssigneeId() == null || ticket.getAssigneeId() == 0) {
			throw new BadRequestException("Assignee must be entered!");
		}
		if (ticket.getStatus() != null) {
			boolean validStatus = false;
			for (Status status : Status.values()) {
				if (status.equals(ticket.getStatus())) {
					validStatus = true;
					break;
				}
			}
			if (!validStatus) {
				throw new BadRequestException("Status isn't valid!");
			}
		}
	}
	
	public static void validateUpdateTicket(Long ticketid, TicketModel ticket) {
		if(ticketid != ticket.getId()) {
			throw new BadRequestException("Ticket isn't match");
		}
		if (ticket.getStatus() != null) {
			boolean validStatus = false;
			for (Status status : Status.values()) {
				if (status.equals(ticket.getStatus())) {
					validStatus = true;
					break;
				}
			}
			if (!validStatus) {
				throw new BadRequestException("Status isn't valid!");
			}
		}
	}

}
