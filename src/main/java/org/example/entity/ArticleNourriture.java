package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class ArticleNourriture extends Article {
    @Column(name="date_peremption")
    private LocalDate datePeremption;
}
