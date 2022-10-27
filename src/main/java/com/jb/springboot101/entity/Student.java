package com.jb.springboot101.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_student")
public class Student {
    @Id
    @SequenceGenerator(name = "sgn", sequenceName = "sgsn", allocationSize = 10, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgn")
    private long sid;
    private String name;
    @Column(name = "dob")
    private LocalDate date_of_birth;
    @Transient
    private int age;
    private String email;
    private String username;
    private String pwd;

    public int getAge() {
        return Period.between(this.date_of_birth, LocalDate.now()).getYears();
    }

}
