package ru.itis.restoke.models;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@Builder
public class Posting {
    private Long id;
    private Long categoryId;
    private Long subCategoryId;
    private Long sellerId;
    private String mobileNumber;
    private Date dateOfPublishing;
    private String address;
    private String header;
    private String description;
    private int price;
    private String photo;
}
