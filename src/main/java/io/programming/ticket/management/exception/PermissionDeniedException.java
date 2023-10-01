package io.programming.ticket.management.exception;

public class PermissionDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public PermissionDeniedException(String message){
        super(message);
    }
}
