package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Product extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Brand brand;

    @OneToMany
    private Set<Sellers> sellers;

    @ElementCollection(targetClass = Size.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<Size> sizes;

    private Double price;
    private String batchNum;
    private String SKU;

    private LocalDate expiryDate;
    private LocalDate manufacturingDate;

    @OneToMany
    private Set<Color> colors;

    @OneToMany
    private Set<Review> reviews;

    @OneToOne
    private Category category;
}
