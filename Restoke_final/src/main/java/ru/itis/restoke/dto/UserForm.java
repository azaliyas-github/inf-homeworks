package ru.itis.restoke.dto;

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
public class UserForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String password;
    private Date date_of_registration;
    private String address;
    private String email;
}
