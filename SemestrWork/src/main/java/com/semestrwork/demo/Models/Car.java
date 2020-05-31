package com.semestrwork.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User   owner;

    private String brandCar;
    private String modelCar;
    private String year_issue;
    private String date_posting;
    private String color;
    private String mileage;
    private String engine;
    private String body_type;
    private String gearBox_type;
    private String driveUnit_type;
    private String rudder_type;
    private String condition_type;
    private String image;
    private int    cost;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;
}
