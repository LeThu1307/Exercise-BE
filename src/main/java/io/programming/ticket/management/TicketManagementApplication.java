package io.programming.ticket.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
