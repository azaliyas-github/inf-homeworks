package ru.itis.restoke.repository.users;

import ru.itis.restoke.models.User;
import ru.itis.restoke.repository.CrudRepository;

import java.util.Map;

public interface UsersRepository extends CrudRepository<User>{
     boolean verifyUser(String email, String password);
}
