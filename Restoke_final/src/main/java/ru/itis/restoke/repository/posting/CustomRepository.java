package ru.itis.restoke.repository.posting;

import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;

import java.util.*;

public interface CustomRepository {
    List<PostingDbo> findByPriceAndSellersRole(String query);
}
