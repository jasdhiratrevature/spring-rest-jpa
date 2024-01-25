package com.revature.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
//@Table(name="emp_rest")
public class Employee {
   //@Column
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private int phone;

    public Employee(String name, String email, int phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
