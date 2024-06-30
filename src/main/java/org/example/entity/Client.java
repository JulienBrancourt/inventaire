package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Vente> ventes;

    public Client() {
        ventes = new ArrayList<>();
    }

    public void addVente (Vente vente) {
        ventes.add(vente);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
