package ru.itis.restoke.dto;

import lombok.*;
import ru.itis.restoke.dbo.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password_hash;
    private String address;
    private Date date_of_registration;

    public static List<UserDto> toUserDto(List<UserDbo> userDbo) {
        List<UserDto> userDtos = new ArrayList<>();
        for(UserDbo uDbo : userDbo) {
            UserDto userDto = UserDto.builder()
                    .id(uDbo.getId())
                    .first_name(uDbo.getFirst_name())
                    .last_name(uDbo.getLast_name())
                    .email(uDbo.getEmail())
                    .password_hash(uDbo.getPassword_hash())
                    .address(uDbo.getAddress())
                    .date_of_registration(uDbo.getDate_of_registration())
                    .build();
            userDtos.add(userDto);
        }

        return userDtos;
    }

}
