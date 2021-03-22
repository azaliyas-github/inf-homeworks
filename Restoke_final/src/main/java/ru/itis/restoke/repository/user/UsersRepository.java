package ru.itis.restoke.repository.user;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;
import ru.itis.restoke.dbo.*;

import java.util.*;

@Repository
public interface UsersRepository extends JpaRepository<UserDbo, Long>, CrudRepository<UserDbo, Long> {

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    List<UserDbo> findUserByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    List<UserDbo> findUserById(Long id);
}
