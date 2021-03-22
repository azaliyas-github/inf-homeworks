package ru.itis.restoke.dbo;

import lombok.*;
import ru.itis.restoke.dto.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sellers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long user_id;
    public double general_rating_of_a_seller;
    public int role;

    public static SellerDbo toSellerDbo(SellerDto sellerDto) {
        return SellerDbo.builder()
                .user_id(sellerDto.getUser_id())
                .general_rating_of_a_seller(sellerDto.getGeneral_rating_of_a_seller())
                .role(sellerDto.getRole())
                .build();
    }
}
