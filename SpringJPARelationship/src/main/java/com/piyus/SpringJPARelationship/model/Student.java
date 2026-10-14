package com.piyus.SpringJPARelationship.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// @Data
@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id",
    nullable = false)
    private Department department;

    // own setter methods
//    public void addDepartment(Department department) {
//        this.department = department;
//        this.department.getStudents().add(this);
//    }
//
//    public void removeDepartment(Department department) {
//        this.department = department;
//        this.department.getStudents().remove(this);
//    }

}
