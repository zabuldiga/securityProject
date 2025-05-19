package com.example.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "privilegies")
public class Privilegies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int privilegies_id;
    private String privilegies_name;



}
