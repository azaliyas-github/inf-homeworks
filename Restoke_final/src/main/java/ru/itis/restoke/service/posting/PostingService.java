package ru.itis.restoke.service.posting;

import ru.itis.restoke.dto.*;

import java.util.*;

public interface PostingService {
    List<PostingDto> getPostingsByPriceAndSellersRoleAndQueryString(Integer minPrice,
                                                       Integer maxPrice, String sellersRole,
                                                                    String[] words);
    List<PostingDto> getPostingsBySubcategoryPriceAndSellersRole(Integer minPrice,
                                                      Integer maxPrice, String sellersRole,
                                                      String subcategory);

    public void createPosting(PostingForm postingForm);
}
