//package com.example.demo.entity.gallery;
//
//import com.example.demo.entity.film.Cinema;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "cinema_image")
//public class CinemaImage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "cinema_image_id")
//    private Long cinemaImageId;
//    private String image;
//
//    @ManyToOne
//    @JoinColumn(name = "cinema_id")
//    private Cinema cinema;
//
//    public CinemaImage(String image) {
//        this.image = image;
//    }
//
//}
