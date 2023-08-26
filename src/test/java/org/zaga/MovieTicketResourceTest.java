package org.zaga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.zaga.model.MovieTicketBooking;
import org.zaga.repository.MovieTicketBookingRepo;



import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestTransaction
public class MovieTicketResourceTest {

    @Inject
    MovieTicketBookingRepo movieTicketBookingRepo;

        @Test
        @Order(1)
        public void testcreateTicketBooking(){
            MovieTicketBooking booking = new MovieTicketBooking();
            booking.setCustomername("nila");
            booking.setMobileno(123456L);
            booking.setTicketcount(1);
            booking.setTicketprice(200);
            movieTicketBookingRepo.persist(booking);
            MovieTicketBooking tBooking = given().body(booking).contentType(ContentType.JSON).when().post("/ticketbooking/create")
                .then().statusCode(201).extract().body().as(MovieTicketBooking.class);
            assertNotNull(tBooking);
            assertNotNull(tBooking.id);
        }

        @Test
        @Order(2)
        public void testcreateTicketRepository() {
            MovieTicketBooking booking = new MovieTicketBooking();
            booking.setCustomername("nila");
            booking.setMobileno(123456L);
            booking.setTicketcount(1);
            booking.setTicketprice(200);
            movieTicketBookingRepo.persist(booking);
            assertNotNull(booking);
            assertNotNull(booking.id);
        }

                @Test
                @Order(3)
                public void testgetAllTicketBooks(){
                    java.util.List<MovieTicketBooking> tickets = given().when().get("/ticketbooking/getallticketbookings").then().statusCode(200).extract()
                                .body().jsonPath().getList(".",MovieTicketBooking.class);
                                assertEquals(tickets.size(),3);
            }

        @Test
        @Order(4)
        public void testgetTicketDetailsByMobileNumber(){
            Long mobileno = 875486L;
            MovieTicketBooking ticketbymobileno = given().pathParam("mobileno", 875486L).body(mobileno).contentType(ContentType.JSON).when().get("/ticketbooking/getbymobileno/{mobileno}").then().statusCode(200).extract()
                        .body().as(MovieTicketBooking.class);
                        assertNotNull(ticketbymobileno);
                        assertEquals(875486L,ticketbymobileno.getMobileno());        
        }

            @Test
            @Order(5)
            public void testupdateTicketBook() {
            Long mobileno = 875486L;
            MovieTicketBooking updateTicketBook = new MovieTicketBooking();
            updateTicketBook.setTicketcount(1);
            given().pathParam("mobileno",mobileno).body(updateTicketBook).contentType(ContentType.JSON).when().put("/ticketbooking/update/{mobileno}")
            .then().statusCode(200).extract().body().as(MovieTicketBooking.class);;
            }

        
            @Test
            @Order(6)
            public void testdeleteTicketByMobileNumber() {
                Long mobileno = 123456L;
                given().pathParam("mobileno",mobileno).when().delete("/ticketbooking/delete/{mobileno}").then()
                .statusCode(204);
            }

    
}
