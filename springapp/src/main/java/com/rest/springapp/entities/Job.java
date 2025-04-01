package com.rest.springapp.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false) // ✅ Added JoinColumn
    private Company company;

    private double salary;

    @JsonIgnore
    public double getSalary() { // ✅ Corrected @JsonIgnore placement
        return salary;
    }
}
