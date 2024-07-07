package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.Etat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Vente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date_vente")
    private Date dateVente;

    @Enumerated
    private Etat etat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vente_article",
            joinColumns = @JoinColumn(name = "vente_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Vente() {
        articles = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", dateVente=" + dateVente +
                ", etat=" + etat +
                ", client=" + (client != null ? client.getId() : null) +
                ", articlesCount=" + (articles != null ? articles.size() : 0) +
                ", articleIds=" + (articles != null ? articles
                .stream()
                .map(Article::getId)
                .collect(Collectors.toList())
                : "[]") +
                '}';
    }
}
