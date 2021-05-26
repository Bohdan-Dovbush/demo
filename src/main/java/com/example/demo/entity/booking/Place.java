package com.example.demo.entity.booking;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private Integer row;
    private Integer seats;
    private Boolean active;

    public Place(Integer row, Integer seats, Boolean active) {
        this.row = row;
        this.seats = seats;
        this.active = active;
    }

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(placeId, place.placeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId);
    }
}
