package org.example.util;

import org.example.entity.Article;
import org.example.entity.ArticleElectronique;
import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.repository.ClientRepository;
import org.example.repository.ElectroniqueRepository;
import org.example.repository.VenteRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.util.Etat.*;

public class IHMVente {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;

    public IHMVente() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Ventes ===");
            System.out.println("1. Création");
            System.out.println("2. Modification");
            System.out.println("3. Suppression");
            System.out.println("4. Consultation");
            System.out.println();
            System.out.print("Sélectionnez une option : ");
            choix = sc.nextLine();

            switch (choix) {
                case "1" -> create();
                default -> {
                    return;
                }
            }

        }
    }

    public Vente create() {
        System.out.println("=== Création ===");
        System.out.println("Date de la vente (dd/mm/yyyy)");
        String dateString = sc.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(dateString);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }

        System.out.println("Quel est l'état de la vente ?");
        int i = 1;
        for (Etat etat : Etat.values()) {
            System.out.println(i + " " + etat);
            i++;
        }
        System.out.print("Entrez le numéro correspondant à l'état de la vente : ");
        int choix = sc.nextInt();
        sc.nextLine();

        Etat choixEtat = switch (choix) {
            case 1 -> ENCOURS;
            case 2 -> FINALISEE;
            case 3 -> ANNULEE;
            default -> null;
        };

        System.out.println("Saisir l'id du client :");
        int idClient = sc.nextInt();
        sc.nextLine();
        ClientRepository clientRepository = new ClientRepository();
        Client clientFound =  clientRepository.findById(idClient);


        ElectroniqueRepository electroniqueRepository = new ElectroniqueRepository();
        ArticleElectronique articleFound;
        List<Article> list = new ArrayList<>();
        while (true) {
            System.out.println("Saisir l'id de l'article (0 pour quitter)");
            int idArticle = sc.nextInt();
            sc.nextLine();
            if (idArticle == 0) {
                break;
            }
            articleFound = electroniqueRepository.findById(idArticle);
            if (articleFound != null) {
                list.add(articleFound);
                System.out.println("Article ajouté : " + articleFound);
            } else {
                System.out.println("Article non trouvé avec l'id: " + idArticle);
            }
            System.out.println("Liste actuelle des articles : " + list);
        }

        Vente vente = Vente
                .builder()
                .dateVente(date)
                .etat(choixEtat)
                .client(clientFound)
                .articles(list)
                .build();

        VenteRepository venteRepository = new VenteRepository();
        venteRepository.createVente(vente);
        return vente;

    }

}
