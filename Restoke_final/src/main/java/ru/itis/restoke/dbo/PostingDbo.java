package ru.itis.restoke.dbo;

import lombok.*;
import ru.itis.restoke.dto.*;

import javax.persistence.*;
import java.sql.*;

@Data
@Entity
@Table(name = "postings")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PostingDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long category_id;
    public Long subcategory_id;
    public Long seller_id;
    public String mobile_number;
    public Date date;
    public String city;
    public String header;
    public String description;
    public int price;
    public String photo;

    public static PostingDbo toPostingDbo(PostingForm postingForm) {
        return PostingDbo.builder()
                .category_id(postingForm.getCategory_id())
                .subcategory_id(postingForm.getSubcategory_id())
                .seller_id(postingForm.getSeller_id())
                .mobile_number(postingForm.getMobile_number())
                .date(postingForm.getDate())
                .city(postingForm.getCity())
                .header(postingForm.getHeader())
                .description(postingForm.getDescription())
                .price(postingForm.getPrice())
                .photo(postingForm.getPhoto())
                .build();
    }
}
