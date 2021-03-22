package ru.itis.restoke.dbo;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "subcategory")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long category_id;
    public String name;
}
