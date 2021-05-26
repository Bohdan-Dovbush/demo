package com.example.demo.entity.booking;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;
    private Integer price;
    private Boolean isBooked;
    private LocalDateTime createTicket = LocalDateTime.now();

    public Ticket(Integer price, Boolean isBooked, LocalDateTime createTicket, Seance seance) {
        this.price = price;
        this.isBooked = isBooked;
        this.createTicket = createTicket;
        this.seance = seance;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    private Seance seance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    public Boolean getIsBooked() {
        return isBooked;
    }
}
