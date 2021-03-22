package ru.itis.restoke.service.seller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.seller.*;
import java.util.*;

@Component
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellersRepository sellersRepository;

    @Override
    public List<SellerDto> getSellerByUserId(Long userId) {
        List<SellerDbo> sellerDbos = sellersRepository.findSellerDboByUser_id(userId);
        return SellerDto.toSellerDtoList(sellerDbos);
    }

    @Override
    public void createSeller(SellerDto sellerDto) {
        sellersRepository.save(SellerDbo.toSellerDbo(sellerDto));
    }
}
