package org.zaga.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MovieTicketBooking")
public class MovieTicketBooking extends PanacheEntity {

    private String customername;
    private Long mobileno;
    private Integer ticketprice;
    private Integer ticketcount;
    private Integer totalamount; 
}
