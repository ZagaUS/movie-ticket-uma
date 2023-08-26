package org.zaga.serviceimplementstion;

import java.util.List;

import org.zaga.model.MovieTicketBooking;
import org.zaga.repository.MovieTicketBookingRepo;
import org.zaga.service.MovieTicketBookService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;

@ApplicationScoped
public class MovieTicketServiceImpl implements MovieTicketBookService {
    
    @Inject
    MovieTicketBookingRepo movieTicketBookingRepo;

    @Override
    public MovieTicketBooking createTicketBooking(MovieTicketBooking ticketBooking) {
        int totalAmount = ticketBooking.getTicketcount()*200;
        ticketBooking.setTotalamount(totalAmount);
        MovieTicketBooking.persist(ticketBooking);
        return ticketBooking;
    }

    @Override
    public List<MovieTicketBooking> getallTicketBookings() {
        return movieTicketBookingRepo.listAll();
    }

    @Override
    public MovieTicketBooking getTicketDetailsByMobileNumber(Long mobileno) {
        return movieTicketBookingRepo.findByMobileNumber(mobileno);
    }

    @Override
    public MovieTicketBooking updateTicketBooking(Long mobileno, MovieTicketBooking ticketBooking) {
        MovieTicketBooking booking = movieTicketBookingRepo.findByMobileNumber(mobileno);
        booking.setTicketcount(ticketBooking.getTicketcount());
        booking.setTotalamount(ticketBooking.getTicketcount()*200);
        return booking;
    }

   
    @Override
    public void deleteTicketByMobileNumber(Long mobileno) {
        MovieTicketBooking ticketBooking = movieTicketBookingRepo.findByMobileNumber(mobileno);
        if (ticketBooking != null) {
            movieTicketBookingRepo.delete(ticketBooking);
        }
    }

}
