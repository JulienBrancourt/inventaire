package org.example.util;

import org.example.entity.Article;
import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.repository.ArticleRepository;
import org.example.repository.ClientRepository;
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
    private VenteRepository venteRepository = new VenteRepository();

    public IHMVente() {
        sc = new Scanner(System.in);
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void start() {
        String choix;

        while (true) {
            System.out.println("=== Ventes ===");

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
        Client clientFound = clientRepository.findById(idClient);

        ArticleRepository articleRepository = new ArticleRepository();
        Article articleFound;
        List<Article> list = new ArrayList<>();
        while (true) {
            System.out.println("Saisir l'id de l'article (0 pour quitter)");
            int idArticle = sc.nextInt();
            sc.nextLine();
            if (idArticle == 0) {
                break;
            }
            articleFound = articleRepository.findById(idArticle);
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

        venteRepository.createVente(vente);
        return vente;

    }

    public void update() {
        System.out.println("=== Modification ===");
        System.out.println("id de la vente : ");
        int id = sc.nextInt();
        sc.nextLine();
        Vente vente = venteRepository.findById(id);

        System.out.println("date de la vente (dd/MM/yyyy)");
        System.out.println("ancienne date : " + venteRepository.findById(id).getDateVente());
        String dateStringVente = sc.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateVente;
        try {
            dateVente = formatter.parse(dateStringVente);
            System.out.println("Converted Date: " + dateVente);
            vente.setDateVente(dateVente);
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
        System.out.println("ancien état : " + vente.getEtat());
        int choix = sc.nextInt();
        sc.nextLine();
        Etat choixEtat = switch (choix) {
            case 1 -> ENCOURS;
            case 2 -> FINALISEE;
            case 3 -> ANNULEE;
            default -> null;
        };
        vente.setEtat(choixEtat);

        System.out.println("Saisir l'id du client :");
        System.out.println("ancien id : " + vente.getClient().getId());
        int idClient = sc.nextInt();
        sc.nextLine();
        ClientRepository clientRepository = new ClientRepository();
        Client clientFound = clientRepository.findById(idClient);
        vente.setClient(clientFound);

        ArticleRepository articleRepository = new ArticleRepository();
        Article articleFound;
        List<Article> list = new ArrayList<>();
        while (true) {
            System.out.println("Saisir l'id de l'article (0 pour quitter)");
            System.out.println("précédents articles : " + vente.getArticles());
            int idArticle = sc.nextInt();
            sc.nextLine();
            if (idArticle == 0) {
                break;
            }
            articleFound = articleRepository.findById(idArticle);
            if (articleFound != null) {
                list.add(articleFound);
                System.out.println("Article ajouté : " + articleFound);
            } else {
                System.out.println("Article non trouvé avec l'id: " + idArticle);
            }
            System.out.println("Liste actuelle des articles : " + list);
        }
        vente.setArticles(list);

        venteRepository.updateVente(vente);
    }

    public void delete() {
        System.out.println("Quelle id voulez-vous supprimer ? ");
        int id = sc.nextInt();
        sc.nextLine();

        Vente vente = venteRepository.findById(id);
        venteRepository.delete(vente);
    }

    public void findAll() {
        VenteRepository venteRepository = new VenteRepository();
        venteRepository.findAll();
    }

}
