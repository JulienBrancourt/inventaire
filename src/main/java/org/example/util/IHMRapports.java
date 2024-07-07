package org.example.util;

import org.example.entity.Vente;
import org.example.repository.VenteRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class IHMRapports {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;
    private VenteRepository venteRepository = new VenteRepository();

    public IHMRapports() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Rapports et analyses ===");
            System.out.println("1. Ventes par date");
            System.out.println("2. Ventes par produit");
            System.out.println("3. Ventes par client");
            System.out.println();
            System.out.print("Sélectionnez une option : ");
            choix = sc.nextLine();

            switch (choix) {
                case "1" -> rapport1();
//                case "2" -> rapport2();
//                case "3" -> rapport3();
                default -> {
                    return;
                }
            }

        }
    }

    public void rapport1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Date de début (jj/MM/yyyy) :");
        String dateDebut = sc.nextLine();

        System.out.println("Date de fin (jj/MM/yyyy) :");
        String dateFin = sc.nextLine();
        List<Vente> ventes = venteRepository.findVenteByDate(dateDebut, dateFin);

        for (Vente vente : ventes) {
            System.out.println(vente);
        }
    }

//    public void rapport2() {
//        venteRepository.findVenteByDate();
//    }
//    public void rapport3() {
//        venteRepository.findVenteByDate();
//    }
}
