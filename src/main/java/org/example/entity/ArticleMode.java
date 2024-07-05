package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.util.Categorie;
import org.example.util.Taille;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class ArticleMode extends Article{
    @Enumerated
    private Categorie categorie;

    @Enumerated
    private Taille taille;

    @Override
    public String toString() {
        return super.toString()+" "+categorie+" "+taille;
    }
}
