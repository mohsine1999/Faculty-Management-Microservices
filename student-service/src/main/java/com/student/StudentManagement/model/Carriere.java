package com.student.StudentManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.student.StudentManagement.enumurations.Diplomat;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity(name = "carriere")
@Table(uniqueConstraints=@UniqueConstraint( columnNames={"diplomat", "student_id"}))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carriere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "diplomat")
    private Diplomat diplomat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Student student;

    public Carriere(Long id){
        this.id=id;
    }


}
