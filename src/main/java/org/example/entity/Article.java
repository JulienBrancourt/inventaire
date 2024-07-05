package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected int id;
    protected String description;
    protected double prix;
    protected int quantite;
    @Column(name = "date_restock")
    protected Date dateRestock;

    @ManyToMany(mappedBy = "articles")
    private List<Vente> ventes;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", dateRestock=" + dateRestock +
                '}';
    }

}
