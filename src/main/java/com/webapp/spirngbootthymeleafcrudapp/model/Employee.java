package com.webapp.spirngbootthymeleafcrudapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//employee entity
@Entity
@Data
@Getter@Setter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;

   @Column(name = "phone_number")
   private long phoneNumber;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String Email;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

}
