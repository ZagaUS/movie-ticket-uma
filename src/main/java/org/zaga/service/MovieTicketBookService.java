package org.zaga.service;

import java.util.List;

import org.zaga.model.MovieTicketBooking;


public interface MovieTicketBookService {

    MovieTicketBooking createTicketBooking(MovieTicketBooking ticketBooking);

    List<MovieTicketBooking> getallTicketBookings();

    MovieTicketBooking getTicketDetailsByMobileNumber(Long mobileno);

    MovieTicketBooking updateTicketBooking(Long mobileno, MovieTicketBooking ticketBooking);

    void deleteTicketByMobileNumber(Long mobileno);
    
}
