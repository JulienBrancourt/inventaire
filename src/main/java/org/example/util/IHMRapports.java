package org.example.util;

import org.example.repository.VenteRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class IHMRapports {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;

    public IHMRapports() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Rapports et analyses ===");
            System.out.println("1. Ventes par produit, par période et par client");
            System.out.println("2. Modification");
            System.out.println("3. Suppression");
            System.out.println("4. Consultation");
            System.out.println();
            System.out.print("Sélectionnez une option : ");
            choix = sc.nextLine();

            switch (choix) {
                case "1" -> rapport1();
                default -> {
                    return;
                }
            }

        }
    }

    public void rapport1() {
        VenteRepository venteRepository = new VenteRepository();
        venteRepository.findAll();
    }
}
