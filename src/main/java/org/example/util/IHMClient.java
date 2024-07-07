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
    private ClientRepository clientRepository = new ClientRepository();

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
                case "2" -> update();
                case "3" -> delete();
                case "4" -> findAll();
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

        clientRepository.createClient(client);
        System.out.println(client);
        return client;

    }

    public void update() {
        System.out.println("=== Modification ===");
        System.out.println("id du client : ");
        int id = sc.nextInt();
        sc.nextLine();
        Client client = clientRepository.findById(id);

        System.out.println("Nom");
        System.out.println("ancien nom : " + client.getNom());
        String nom = sc.nextLine();
        client.setNom(nom);

        System.out.println("Mail");
        System.out.println("ancien mail : " + client.getEmail());
        String mail = sc.nextLine();
        client.setEmail(mail);

        clientRepository.updateClient(client);
    }

    public void delete() {
        System.out.println("Quelle id voulez-vous supprimer ? ");
        int id = sc.nextInt();
        sc.nextLine();

        Client client = clientRepository.findById(id);
        clientRepository.delete(client);
    }

    public void findAll() {
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.findAll();
    }

}
