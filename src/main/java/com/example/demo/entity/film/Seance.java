package com.example.demo.entity.film;

import com.example.demo.entity.booking.Ticket;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seance")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seance_id")
    private Long seanceId;
    private LocalTime time;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @OneToMany(mappedBy = "seance",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    public Seance(LocalTime time, Hall hall, Film film) {
        this.time = time;
        this.hall = hall;
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seance)) return false;
        Seance seance = (Seance) o;
        return Objects.equals(seanceId, seance.seanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seanceId);
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
        ticket.setSeance(this);
        ticket.getPrice();
    }
}
