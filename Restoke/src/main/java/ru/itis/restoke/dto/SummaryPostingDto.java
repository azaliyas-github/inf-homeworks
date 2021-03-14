package ru.itis.restoke.dto;

import lombok.*;
import ru.itis.restoke.models.Posting;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SummaryPostingDto {
    private Long id;
    private Date dateOfPublishing;
    private String address;
    private String header;
    private int price;
    private String photoRef;

    public static List<SummaryPostingDto> ToSummaryPostingDto(List<Posting> postings) {
        List<SummaryPostingDto> summaryPostingDtos = new ArrayList<>();
        for (Posting posting: postings) {
            SummaryPostingDto summaryPostingDto = new SummaryPostingDto(
                    posting.getId(),
                    posting.getDateOfPublishing(),
                    posting.getAddress(),
                    posting.getHeader(),
                    posting.getPrice(),
                    posting.getPhoto()
            );
            summaryPostingDtos.add(summaryPostingDto);
        }
        return summaryPostingDtos;
    }
}
