package ru.itis.restoke.service.user;

import ru.itis.restoke.dto.*;
import java.util.*;

public interface UserService {
     public List<UserDto> getUserByEmail(String email);

     public List<UserDto> getUserById(Long id);

     public boolean verifyUser(UserDto userDto, String hashedPassword);

     public void registerUser(UserForm userForm);
}
