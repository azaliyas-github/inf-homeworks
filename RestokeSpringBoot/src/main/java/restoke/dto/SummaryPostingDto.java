package restoke.dto;

import lombok.*;
import restoke.models.*;

import java.sql.Date;
import java.util.*;

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
