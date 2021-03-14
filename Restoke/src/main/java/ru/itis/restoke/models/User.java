package ru.itis.restoke.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private Date dateOfRegistration;

    public User(Long id, String firstName, String lastName, String email, String password, String address, Date dateOfRegistration) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dateOfRegistration = dateOfRegistration;
    }
}
