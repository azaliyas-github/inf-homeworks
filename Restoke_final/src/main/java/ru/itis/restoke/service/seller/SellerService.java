package ru.itis.restoke.service.seller;

import ru.itis.restoke.dto.*;

import java.util.*;

public interface SellerService {
    List<SellerDto> getSellerByUserId(Long userId);

    void createSeller(SellerDto sellerDto);
}
