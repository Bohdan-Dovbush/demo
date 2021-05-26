package com.example.demo.entity.booking;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;
    private LocalDateTime createDate = LocalDateTime.now();
    private Boolean pay;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private List<Ticket> tickets = new ArrayList<>();

    public Booking(LocalDateTime createDate, Boolean pay, List<Ticket> tickets) {
        this.createDate = createDate;
        this.pay = pay;
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    private Boolean isPay() {
        return pay;
    }
}
