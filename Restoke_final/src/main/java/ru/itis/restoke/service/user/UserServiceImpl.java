package ru.itis.restoke.service.user;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.controllers.helpers.*;
import ru.itis.restoke.dbo.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.user.*;

import java.util.*;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<UserDto> getUserByEmail(String email) {
        List<UserDbo> userDbo = usersRepository.findUserByEmail(email);
        List<UserDto> userDto;
        if (userDbo.size() != 0) {
            return UserDto.toUserDto(userDbo);
        }
        return userDto = new ArrayList<>();
    }

    @Override
    public List<UserDto> getUserById(Long id) {
        List<UserDbo> userDbo = usersRepository.findUserById(id);
        List<UserDto> userDto = UserDto.toUserDto(userDbo);
        return userDto;
    }

    @Override
    public boolean verifyUser(UserDto userDto, String hashedPassword) {
        List<UserDbo> userDbo = usersRepository.findUserByEmail(userDto.getEmail());
        // если хешированные пароли(из бд и из фронта) совпадают возвращается true
        if (userDbo.size() != 0)
            return userDbo.get(0).getPassword_hash().equals(hashedPassword);

        return false;
    }

    @Override
    public void registerUser(UserForm userForm) {
        String hashedPassword = hashPassword(userForm.getPassword());

        usersRepository.save(UserDbo.builder()
                .first_name(userForm.getFirst_name())
                .last_name(userForm.getLast_name())
                .password_hash(hashedPassword)
                .date_of_registration(userForm.getDate_of_registration())
                .address(userForm.getAddress())
                .email(userForm.getEmail())
                .build());
    }

    private String hashPassword(String password) {
        String hashedPassword = null;

        try {
            byte[] passwordBytes = password.getBytes();
            //здесь хеширую пароль
            hashedPassword = HashFunctions.getHash(passwordBytes, "SHA-512");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }


}
