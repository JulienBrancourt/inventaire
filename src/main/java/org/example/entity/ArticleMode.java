package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.util.Categorie;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class ArticleMode extends Article{
    @Enumerated
    private Categorie categorie;

    private int taille;
}
