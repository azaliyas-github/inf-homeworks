package ru.itis.restoke.dto;

import lombok.*;
import ru.itis.restoke.dbo.*;
import ru.itis.restoke.models.*;
import java.sql.Date;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class PostingDto {
    private Long id;
    private Date dateOfPublishing;
    private String address;
    private String header;
    private int price;
    private String photoRef;

    public static List<PostingDto> ToSummaryPostingDto(List<PostingDbo> postings) {
        List<PostingDto> postingDtos = new ArrayList<>();
        for (PostingDbo posting: postings) {
            PostingDto postingDto = new PostingDto(
                    posting.getId(),
                    posting.getDate(),
                    posting.getCity(),
                    posting.getHeader(),
                    posting.getPrice(),
                    posting.getPhoto()
            );
            postingDtos.add(postingDto);
        }
        return postingDtos;
    }
}
