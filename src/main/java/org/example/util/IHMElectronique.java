package org.example.util;

import org.example.entity.ArticleElectronique;
import org.example.repository.ElectroniqueRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IHMElectronique {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;
    private ElectroniqueRepository electroniqueRepository = new ElectroniqueRepository();


    public IHMElectronique() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Articles électroniques ===");

            DisplayCRUD displayCRUD = new DisplayCRUD();
            displayCRUD.appelAffichage();

            choix = sc.nextLine();

            switch (choix) {
                case "1" -> create();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> findAll();
                default -> {
                    return;
                }
            }

        }
    }

    public ArticleElectronique create() {
        System.out.println("=== Création ===");
        System.out.println("Description");
        String description = sc.nextLine();

        System.out.println("Prix");
        double prix = sc.nextDouble();
        //sc.nextLine();

        System.out.println("Quantité");
        int quantite = sc.nextInt();
        sc.nextLine();

        System.out.println("Durée de la batterie");
        int batterie = sc.nextInt();
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

        ArticleElectronique article = ArticleElectronique
                .builder()
                .description(description)
                .prix(prix)
                .quantite(quantite)
                .dateRestock(date)
                .dureeBatterie(batterie)
                .build();

        electroniqueRepository.createElectronique(article);
        System.out.println(article);
        return article;

    }

    public void update() {
        System.out.println("=== Modification ===");
        System.out.println("id du produit : ");
        int id = sc.nextInt();
        sc.nextLine();
        ArticleElectronique articleElectronique = electroniqueRepository.findById(id);

        System.out.println("Description");
        System.out.println("ancienne description : " + articleElectronique.getDescription());
        String description = sc.nextLine();
        articleElectronique.setDescription(description);

        System.out.println("Prix");
        System.out.println("ancien prix : " + articleElectronique.getPrix());
        double prix = sc.nextDouble();
        articleElectronique.setPrix(prix);

        System.out.println("Quantité");
        System.out.println("ancienne quantité : " + articleElectronique.getQuantite());
        int quantite = sc.nextInt();
        sc.nextLine();
        articleElectronique.setQuantite(quantite);

        System.out.println("Durée de la batterie");
        System.out.println("ancienne durée : " + articleElectronique.getDureeBatterie());
        int batterie = sc.nextInt();
        sc.nextLine();
        articleElectronique.setDureeBatterie(batterie);

        System.out.println("date de restock (dd/MM/yyyy)");
        System.out.println("ancienne date : " + articleElectronique.getDateRestock());
        String dateString = sc.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(dateString);
            System.out.println("Converted Date: " + date);
            articleElectronique.setDateRestock(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }

        electroniqueRepository.updateElectronique(articleElectronique);
    }

    public void delete() {
        System.out.println("Quelle id voulez-vous supprimer ? ");
        int id = sc.nextInt();
        sc.nextLine();

        ArticleElectronique articleElectronique = electroniqueRepository.findById(id);
        electroniqueRepository.delete(articleElectronique);
    }

    public void findAll() {
        ElectroniqueRepository electroniqueRepository = new ElectroniqueRepository();
        electroniqueRepository.findAll();
    }
}
