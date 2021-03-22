package ru.itis.restoke.dto;

import lombok.*;

import java.sql.*;

@Setter
@Getter
@Builder
public class PostingForm {
    private Long category_id;
    private Long subcategory_id;
    private Long seller_id;
    private String mobile_number;
    private Date date;
    private String city;
    private String header;
    private String description;
    private int price;
    private String photo;
}
