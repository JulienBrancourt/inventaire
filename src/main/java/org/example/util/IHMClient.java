package org.example.util;

import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class IHMClient {
    private static SessionFactory sessionFactory;
    private static Session session;
    private Scanner sc;

    public IHMClient() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Client ===");
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

    public Client create() {
        System.out.println("=== Création ===");
        System.out.println("Nom");
        String nom = sc.nextLine();

        System.out.println("Email");
        String email = sc.nextLine();

        Client client = Client
                .builder()
                .nom(nom)
                .email(email)
                .build();

        ClientRepository clientRepository = new ClientRepository();
        clientRepository.createClient(client);

        System.out.println(client);
        return client;

    }

}
