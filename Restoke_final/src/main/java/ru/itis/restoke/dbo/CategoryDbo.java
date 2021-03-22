package ru.itis.restoke.dbo;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String str_id;
    public String name;
}
