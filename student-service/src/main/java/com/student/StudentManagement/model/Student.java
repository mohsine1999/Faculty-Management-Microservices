package com.student.StudentManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.student.StudentManagement.enumurations.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cin;
    @Column(unique = true)
    private Long apogee;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String cne;
    @Column(unique = true)
    private String email;
    private String phone;
    private Date dateNaissance;
    private String lieuNaissance;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private Gender genre;

    // new

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id", nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Filiere filier;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Carriere> carrieres;
}
