package org.example.util;


import org.example.entity.ArticleMode;
import org.example.repository.ModeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.example.util.Categorie.*;


public class IHMMode {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;

    public IHMMode() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Articles de mode ===");
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

    public ArticleMode create() {
        System.out.println("=== Création ===");
        System.out.println("Description");
        String description = sc.nextLine();

        System.out.println("Prix");
        double prix = sc.nextDouble();

        System.out.println("Quantité");
        int quantite = sc.nextInt();
        sc.nextLine();

        System.out.println("Taille");
        int taille = sc.nextInt();
        sc.nextLine();

        System.out.println("date de restock (dd/MM/yyyy)");
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

        System.out.println("Quelle catégorie ");
        int i = 1;
        for (Categorie categorie : Categorie.values()) {
            System.out.println(i + " " +categorie);
            i++;
        }
        System.out.print("Entrez le numéro correspondant à l'état de la vente : ");

        int saisieCategorie = sc.nextInt();
        sc.nextLine();
        Categorie choixCategorie = switch (saisieCategorie) {
            case 1 -> HOMME;
            case 2 -> FEMME;
            case 3 -> ENFANT;
            default -> null;
        };

        ArticleMode article = ArticleMode
                .builder()
                .description(description)
                .prix(prix)
                .quantite(quantite)
                .dateRestock(date)
                .taille(taille)
                .categorie(choixCategorie)
                .build();

        ModeRepository modeRepository = new ModeRepository();
        modeRepository.createMode(article);
        System.out.println(article);
        return article;


    }
}
