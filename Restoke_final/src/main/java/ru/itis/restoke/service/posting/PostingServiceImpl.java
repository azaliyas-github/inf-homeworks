package ru.itis.restoke.service.posting;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.posting.*;
import ru.itis.restoke.repository.subcategory.*;
import java.util.*;

@Component
public class PostingServiceImpl implements PostingService{

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    CustomRepository customRepository;

    @Autowired
    SubcategoriesRepository subcategoriesRepository;

    public List<PostingDto> getPostingsByPriceAndSellersRoleAndQueryString(Integer minPrice, Integer maxPrice,
                                                             String sellersRole, String[] words) {
        QueryBuilder queryBuilder = new QueryBuilder();

        if (minPrice != null) {
            queryBuilder.addMinPriceCondition(minPrice);
        }
        if (maxPrice != null) {
            queryBuilder.addMaxPriceCondition(maxPrice);
        }
        if (sellersRole != null) {
            if (sellersRole.equals("0")) {
                queryBuilder.addRoleCondition(sellersRole);
            }
            if (sellersRole.equals("1")) {
                queryBuilder.addRoleCondition(sellersRole);
            }
        }

        queryBuilder.addSearchCondition(words);
        final String query = queryBuilder.build();
        List<PostingDbo> postingDbos = customRepository.findByPriceAndSellersRole(query);
        List<PostingDto> postingDtos = PostingDto.ToSummaryPostingDto(postingDbos);
        return postingDtos;
    }

    public List<PostingDto> getPostingsBySubcategoryPriceAndSellersRole(Integer minPrice, Integer maxPrice,
                                                             String sellersRole,
                                                             String subcategory) {
        QueryBuilder queryBuilder = new QueryBuilder();

        if (minPrice != null) {
            queryBuilder.addMinPriceCondition(minPrice);
        }
        if (maxPrice != null) {
            queryBuilder.addMaxPriceCondition(maxPrice);
        }
        if (sellersRole != null) {
            if (sellersRole.equals("0")) {
                queryBuilder.addRoleCondition(sellersRole);
            }
            if (sellersRole.equals("1")) {
                queryBuilder.addRoleCondition(sellersRole);
            }
        }

        SubcategoryDbo subcategoryDbo = subcategoriesRepository.findByName(subcategory).get(0);
        final String query = queryBuilder.addSubcategoryCondition(subcategoryDbo.getId()).build();
        List<PostingDbo> postingDbos = customRepository.findByPriceAndSellersRole(query);
        List<PostingDto> postingDtos = PostingDto.ToSummaryPostingDto(postingDbos);

        return postingDtos;
    }

    public void createPosting(PostingForm postingForm) {
        postingRepository.save(PostingDbo.toPostingDbo(postingForm));
    }
}
