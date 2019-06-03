package com.transport.smart.model;

import javax.persistence.*;


@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private Route route;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            orphanRemoval = true)
    private RaspberryPi pi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public RaspberryPi getPi() {
        return pi;
    }

    public void setPi(RaspberryPi pi) {
        this.pi = pi;
    }
}
