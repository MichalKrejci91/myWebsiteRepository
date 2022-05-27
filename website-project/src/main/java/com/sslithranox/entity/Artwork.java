package com.sslithranox.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table ... specify table?
public class Artwork {
    private Long id;
    private String name;
    private String description;

    @Column(nullable = true, length = 64)
    private String photos;

    public Artwork(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/artwork-photos/" + id + "/" + photos;
    }

}
