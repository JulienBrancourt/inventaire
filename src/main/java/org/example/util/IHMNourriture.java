package org.example.util;

import org.example.entity.ArticleMode;
import org.example.entity.ArticleNourriture;
import org.example.repository.ModeRepository;
import org.example.repository.NourritureRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IHMNourriture {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;

    public IHMNourriture() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Nourriture ===");

            DisplayCRUD displayCRUD = new DisplayCRUD();
            displayCRUD.appelAffichage();

            choix = sc.nextLine();

            switch (choix) {
                case "1" -> create();
                default -> {
                    return;
                }
            }

        }
    }

    public ArticleNourriture create() {
        System.out.println("=== Création ===");
        System.out.println("Description");
        String description = sc.nextLine();

        System.out.println("Prix");
        double prix = sc.nextDouble();

        System.out.println("Quantité");
        int quantite = sc.nextInt();
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

        System.out.println("date de péremption (dd/MM/yyyy)");
        String dateStringPeremption = sc.nextLine();
        SimpleDateFormat formatterPeremption = new SimpleDateFormat("dd/MM/yyyy");
        Date datePeremption;
        try {
            datePeremption = formatterPeremption.parse(dateStringPeremption);
            System.out.println("Converted Date: " + datePeremption);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }

        ArticleNourriture article = ArticleNourriture
                .builder()
                .description(description)
                .prix(prix)
                .quantite(quantite)
                .dateRestock(date)
                .datePeremption(datePeremption)
                .build();

        NourritureRepository nourritureRepository = new NourritureRepository();
        nourritureRepository.createNourriture(article);
        System.out.println(article);
        return article;
    }
}
