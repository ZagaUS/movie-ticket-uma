package org.zaga.controller;

import java.util.List;

import org.zaga.model.MovieTicketBooking;
import org.zaga.service.MovieTicketBookService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ticketbooking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieTicketResource {

    @Inject
    MovieTicketBookService movieTicketBookService;

    @POST
    @Path("/create")
    @Transactional
    public Response createTicketBooking(MovieTicketBooking ticketBooking) {
        MovieTicketBooking newTicketBooking = new MovieTicketBooking();
            newTicketBooking.setCustomername(ticketBooking.getCustomername());
            newTicketBooking.setMobileno(ticketBooking.getMobileno());
            newTicketBooking.setTicketprice(ticketBooking.getTicketprice());
            newTicketBooking.setTicketcount(ticketBooking.getTicketcount());
            
      MovieTicketBooking createBooking = movieTicketBookService.createTicketBooking(newTicketBooking);
      return Response.status(Response.Status.CREATED).entity(createBooking).build();       
      
    }

    @GET
    @Path("/getallticketbookings")
    @Transactional
    public Response getAllTicketBookings() {
        try {
            List<MovieTicketBooking> ticketBookings = movieTicketBookService.getallTicketBookings();
            return Response.status(200).entity(ticketBookings).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/getbymobileno/{mobileno}")
    @Transactional
    public Response getTicketDetailsByMobileNumber(@PathParam("mobileno") Long mobileno) {
        try {
            MovieTicketBooking ticketBooking = movieTicketBookService.getTicketDetailsByMobileNumber(mobileno);
            return Response.status(200).entity(ticketBooking).build();
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Path("/update/{mobileno}")
    @Transactional
    public Response updateTicketBooking(@PathParam("mobileno") Long mobileno, MovieTicketBooking ticketBooking) {
        try {
            MovieTicketBooking booking = movieTicketBookService.updateTicketBooking(mobileno, ticketBooking);
            return Response.status(200).entity(booking).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{mobileno}")
    @Transactional
    public Response deleteTicketByMobileNumber(@PathParam("mobileno") Long mobileno) {
      movieTicketBookService.deleteTicketByMobileNumber(mobileno);
      return Response.status(Response.Status.NO_CONTENT).build();
    }
}
