package org.zaga.repository;

import org.zaga.model.MovieTicketBooking;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieTicketBookingRepo implements PanacheRepository<MovieTicketBooking> {
    
    public MovieTicketBooking findByMobileNumber(Long mobileno) {
        return find("mobileno",mobileno).firstResult();
    }
    
}
