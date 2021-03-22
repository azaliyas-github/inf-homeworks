package ru.itis.restoke.repository.seller;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import ru.itis.restoke.dbo.*;

import java.util.*;

public interface SellersRepository extends JpaRepository<SellerDbo, Long>, CrudRepository<SellerDbo, Long> {
    @Query(value = "SELECT s.id, s.user_id, s.general_rating_of_a_seller, s.role " +
            "FROM sellers s WHERE user_id = :user_id", nativeQuery = true)
    List<SellerDbo> findSellerDboByUser_id(Long user_id);

}

