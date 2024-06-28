package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class ArticleElectronique extends Article {
    @Column(name="duree_batterie")
    private int dureeBatterie;
}
