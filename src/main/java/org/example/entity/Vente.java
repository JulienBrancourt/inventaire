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

@Entity
@Data
@AllArgsConstructor
@Builder
public class Vente {
    @Id
    @GeneratedValue
    private int id;

    @Column(name="date_vente")
    private Date dateVente;

    @Enumerated
    private Etat etat;

    @ManyToMany
    @JoinTable(
            name = "vente_article",
            joinColumns = @JoinColumn(name = "vente_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Vente() {
        articles = new ArrayList<>();
    }
}
