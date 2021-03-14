package ru.itis.restoke.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Seller {
    private long id;
    private long user_id;
    private double generalRatingOfASeller;
    //0 - individual, 1 - company
    private int role;


}
