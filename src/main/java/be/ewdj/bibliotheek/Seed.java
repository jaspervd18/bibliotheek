package be.ewdj.bibliotheek;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.models.Locatie;
import be.ewdj.bibliotheek.models.Roles;
import be.ewdj.bibliotheek.models.UserEntity;
import be.ewdj.bibliotheek.repository.*;

@Component
public class Seed implements CommandLineRunner {

        @Autowired
        private BoekRepository boekRepo;

        @Autowired
        private AuteurRepository auteurRepo;

        @Autowired
        private LocatieRepository locatieRepo;

        @Autowired
        private UserRepository userRepo;

        @Override
        public void run(String... args) throws Exception {
                Locatie locatie1 = new Locatie(50, 106, "Thriller");
                Locatie locatie2 = new Locatie(256, 204, "Romantiek");
                Locatie locatie3 = new Locatie(296, 249, "Humor");
                Locatie locatie4 = new Locatie(100, 147, "Kinderen");
                Locatie locatie5 = new Locatie(198, 147, "Koken");
                Locatie locatie6 = new Locatie(295, 251, "Reizen");
                Locatie locatie7 = new Locatie(153, 101, "Geschiedenis");
                Locatie locatie8 = new Locatie(98, 51, "Biografie");
                Locatie locatie9 = new Locatie(289, 234, "Kunst");
                Locatie locatie10 = new Locatie(227, 177, "Thriller");
                Locatie locatie11 = new Locatie(250, 195, "Romantiek");
                Locatie locatie12 = new Locatie(148, 95, "Humor");
                Locatie locatie13 = new Locatie(176, 60, "Kinderen");
                Locatie locatie14 = new Locatie(261, 212, "Koken");
                Locatie locatie15 = new Locatie(251, 198, "Reizen");
                Locatie locatie16 = new Locatie(155, 102, "Geschiedenis");
                Locatie locatie17 = new Locatie(207, 155, "Biografie");
                Locatie locatie18 = new Locatie(166, 112, "Kunst");

                Auteur auteur1 = new Auteur("Bart", "Van Loo");
                Auteur auteur2 = new Auteur("J.K.", "Rowling");
                Auteur auteur3 = new Auteur("J.R.R.", "Tolkien");
                Auteur auteur4 = new Auteur("Stephen", "King");
                Auteur auteur5 = new Auteur("George R.R.", "Martin");
                Auteur auteur6 = new Auteur("Jef", "Aerts");
                Auteur auteur7 = new Auteur("Dirk", "Bracke");
                Auteur auteur8 = new Auteur("Herman", "Brusselmans");
                Auteur auteur9 = new Auteur("Tom", "Lanoye");
                Auteur auteur10 = new Auteur("Pieter", "Aspe");
                Auteur auteur11 = new Auteur("Hugo", "Claus");
                Auteur auteur12 = new Auteur("Jeroen", "Meus");
                Auteur auteur13 = new Auteur("Pascale", "Naessens");
                Auteur auteur14 = new Auteur("Sergio", "Herman");
                Auteur auteur15 = new Auteur("Jamie", "Oliver");
                Auteur auteur16 = new Auteur("Peter", "Goossens");
                Auteur auteur17 = new Auteur("Mark", "Manson");
                Auteur auteur18 = new Auteur("Jill", "Mansell");

                auteurRepo.saveAllAndFlush(Arrays.asList(auteur1, auteur2, auteur3, auteur4, auteur5, auteur6, auteur7,
                                auteur8, auteur9, auteur10, auteur11, auteur12, auteur13, auteur14, auteur15, auteur16,
                                auteur17,
                                auteur18));

                Boek boek1 = new Boek("Boergondiërs", "9789403139005", 17.99);
                Boek boek2 = new Boek("Harry Potter en de steen der wijzen", "9781338878929", 10.99);
                Boek boek3 = new Boek("In de ban van de ring", "9789027462718", 25.9);
                Boek boek4 = new Boek("It", "9789024577552", 12.99);
                Boek boek5 = new Boek("Game of Thrones", "9780007448036", 25.99);
                Boek boek6 = new Boek("Groter dan een droom", "9789045114019", 25.99);
                Boek boek7 = new Boek("Black", "9789002274237", 25.99);
                Boek boek8 = new Boek("Ex", "9789026151538", 25.99);
                Boek boek9 = new Boek("Sprakeloos", "9789044633412", 25.99);
                Boek boek10 = new Boek("Het vierkant van de wraak", "9789022313541", 25.99);
                Boek boek11 = new Boek("Het verdriet van België", "9789023401346", 25.99);
                Boek boek12 = new Boek("Dagelijkse kost", "9789022337790", 25.99);
                Boek boek13 = new Boek("Puur genieten", "9789401402750", 25.99);
                Boek boek14 = new Boek("Sergiology", "9789490028459", 25.9);
                Boek boek15 = new Boek("Jamie in 15 minuten", "9789021552767", 25.99);
                Boek boek16 = new Boek("Mijn 200 klassiekers", "9789461313232", 25.9);
                Boek boek17 = new Boek("The Subtle Art of Not Giving a F*ck", "9780062641540", 25.99);
                Boek boek18 = new Boek("Ik zie je op het strand", "9789024579815", 25.99);

                locatie1.setBoek(boek1);
                locatie2.setBoek(boek2);
                locatie3.setBoek(boek3);
                locatie4.setBoek(boek4);
                locatie5.setBoek(boek5);
                locatie6.setBoek(boek6);
                locatie7.setBoek(boek7);
                locatie8.setBoek(boek8);
                locatie9.setBoek(boek9);
                locatie10.setBoek(boek10);
                locatie11.setBoek(boek11);
                locatie12.setBoek(boek12);
                locatie13.setBoek(boek13);
                locatie14.setBoek(boek14);
                locatie15.setBoek(boek15);
                locatie16.setBoek(boek16);
                locatie17.setBoek(boek17);
                locatie18.setBoek(boek18);

                boek1.addLocatie(locatie1);
                boek2.addLocatie(locatie2);
                boek3.addLocatie(locatie3);
                boek4.addLocatie(locatie4);
                boek5.addLocatie(locatie5);
                boek6.addLocatie(locatie6);
                boek7.addLocatie(locatie7);
                boek8.addLocatie(locatie8);
                boek9.addLocatie(locatie9);
                boek10.addLocatie(locatie10);
                boek11.addLocatie(locatie11);
                boek12.addLocatie(locatie12);
                boek13.addLocatie(locatie13);
                boek14.addLocatie(locatie14);
                boek15.addLocatie(locatie15);
                boek16.addLocatie(locatie16);
                boek17.addLocatie(locatie17);
                boek18.addLocatie(locatie18);

                boek1.addAuteur(auteur1);
                boek2.addAuteur(auteur2);
                boek3.addAuteur(auteur3);
                boek4.addAuteur(auteur4);
                boek5.addAuteur(auteur5);
                boek6.addAuteur(auteur6);
                boek7.addAuteur(auteur7);
                boek8.addAuteur(auteur8);
                boek9.addAuteur(auteur9);
                boek10.addAuteur(auteur10);
                boek11.addAuteur(auteur11);
                boek12.addAuteur(auteur12);
                boek13.addAuteur(auteur13);
                boek14.addAuteur(auteur14);
                boek15.addAuteur(auteur15);
                boek16.addAuteur(auteur16);
                boek17.addAuteur(auteur17);
                boek18.addAuteur(auteur18);

                boekRepo.saveAllAndFlush(
                                Arrays.asList(boek1, boek2, boek3, boek4, boek5, boek6, boek7, boek8, boek9, boek10,
                                                boek11, boek12, boek13, boek14, boek15, boek16, boek17, boek18));

                locatieRepo.saveAllAndFlush(Arrays.asList(locatie1, locatie2, locatie3, locatie4, locatie5, locatie6,
                                locatie7, locatie8, locatie9, locatie10, locatie11, locatie12, locatie13, locatie14,
                                locatie15,
                                locatie16, locatie17, locatie18));

                UserEntity user1 = new UserEntity("admin",
                                "$2a$12$.XkLZQ8Zdwl.2T9WLhoXBe5obkk6U5DQ3Qg6GVfK4pQ.QgccoYACy",
                                Roles.ADMIN, 10);
                UserEntity user2 = new UserEntity("user",
                                "$2a$12$30FxlwGA7AUSgeVnI8bNSOOQRGiZ7Pt3Jn9erKsFKXqW8q5yYezJi", Roles.USER, 8);
                UserEntity user3 = new UserEntity("Piet",
                                "$2a$12$y/t6nv.B2.9lkz1nbPFtgOca7dRREhT/MTSlvqfc3nF5ucH7qYK5q", Roles.USER, 3);
                UserEntity user4 = new UserEntity("Jan", "$2a$12$y/t6nv.B2.9lkz1nbPFtgOca7dRREhT/MTSlvqfc3nF5ucH7qYK5q",
                                Roles.USER, 15);
                UserEntity user5 = new UserEntity("Jef", "$2a$12$y/t6nv.B2.9lkz1nbPFtgOca7dRREhT/MTSlvqfc3nF5ucH7qYK5q",
                                Roles.ADMIN, 4);

                userRepo.saveAllAndFlush(Arrays.asList(user1, user2, user3, user4, user5));

        }

}
