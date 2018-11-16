package com.codegym.hotel.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 4, max = 12)
    @Pattern(regexp="(^$|[0-9]{10})", message = "Invalid Id")
    String id;
    @Size(min=5)
    @NotNull(message = "Must not empty")
    String name;
    @NotNull
    String role;
    @NotNull
    String directManager;
    @NotNull
    String idCard;
    @Size(min=4, message = "Please type more than 4 characters")
    String address;
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Email invalid")
    String email;
    @Pattern(regexp = "(^$|^0[0-9]{10})", message = "Phone invalid")
    String phone;
}
