package ru.itis.restoke.dto;

import lombok.*;
import ru.itis.restoke.dbo.*;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SellerDto {
    private Long id;
    private Long user_id;
    private double general_rating_of_a_seller;
    private int role;

    public static List<SellerDto> toSellerDtoList(List<SellerDbo> sellerDbos) {
        List<SellerDto> sellerDtoList = new ArrayList<>();
        for (SellerDbo sellerDbo : sellerDbos) {
            sellerDtoList.add(SellerDto.builder()
                    .id(sellerDbo.getId())
                    .user_id(sellerDbo.user_id)
                    .general_rating_of_a_seller(sellerDbo.getGeneral_rating_of_a_seller())
                    .role(sellerDbo.getRole()).build());
        }
        return sellerDtoList;
    }
}
