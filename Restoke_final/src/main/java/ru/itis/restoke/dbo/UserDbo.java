package ru.itis.restoke.dbo;

import lombok.*;

import javax.persistence.*;
import java.sql.*;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class UserDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String password_hash;
    private Date date_of_registration;
    private String address;
    private String email;

    public UserDbo(Long id, String firstName, String lastName, String email,
                   String password_hash, String address, Date dateOfRegistration) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.password_hash = password_hash;
        this.address = address;
        this.date_of_registration = dateOfRegistration;
    }
}
