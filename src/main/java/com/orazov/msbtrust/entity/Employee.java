package com.orazov.msbtrust.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String job_title;
    private String email;
    private int age;
}