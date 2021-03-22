package ru.itis.restoke.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Feedback {
    private long id;
    private long sellerId;
    private long userId;
    private String feedbackText;
    private int singleRatingOfSeller;

}
