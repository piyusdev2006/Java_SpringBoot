package com.piyus.SpringJPARelationship2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // inverse mapping of department table with student table
    //@OneToMany(mappedBy = "department"
             //cascade = CascadeType.PERSIST
            //cascade = CascadeType.REMOVE
            //cascade = CascadeType.ALL
    //) // this table is mapped in Student Table
    //private List<Student>students = new ArrayList<>();
}
