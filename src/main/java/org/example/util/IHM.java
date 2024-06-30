package org.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class IHM {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;

    public IHM() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Système de gestion ===");
            System.out.println("1. Gestion de l'inventaire");
            System.out.println("2. Gestion des ventes");
            System.out.println("3. Gestion des clients");
            System.out.println("4. Rapports et analyses");
            System.out.println();
            System.out.print("Sélectionnez une option : ");
            choix = sc.nextLine();

            switch (choix) {
                case "1" -> choixArticle();
                case "2" -> IHMVente();
                case "3" -> IhmClient();
                case "4" -> IHMRapports();
                default -> {
                    return;
                }
            }

        }
    }

    public void choixArticle() {
        String choixTypeArticle;

        while (true) {
            System.out.println("=== Gestion de l'inventaire ===");
            System.out.println("1. Articles Electroniques");
            System.out.println("2. Nourriture");
            System.out.println("3. Articles de mode");
            System.out.print("Sélectionnez une option : ");
            choixTypeArticle = sc.nextLine();

            switch (choixTypeArticle) {
                case "1" -> IHMElectronique();
                default -> {
                    return;
                }
            }
        }
    }

    private void IHMElectronique() {

    IHMElectronique iHMElectronique = new IHMElectronique();
    iHMElectronique.start();
    }

    private void IhmClient () {
        IHMClient ihmClient = new IHMClient();
        ihmClient.start();
    }

    private void IHMVente () {
        IHMVente ihmVente = new IHMVente();
        ihmVente.start();
    }

    private void IHMRapports () {
        IHMRapports ihmRapports = new IHMRapports();
        ihmRapports.start();
    }


}
