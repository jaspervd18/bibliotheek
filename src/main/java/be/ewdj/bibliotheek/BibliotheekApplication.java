package be.ewdj.bibliotheek;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import be.ewdj.bibliotheek.boek.Boek;
import be.ewdj.bibliotheek.boek.BoekRepository;
import be.ewdj.bibliotheek.auteur.Auteur;
import be.ewdj.bibliotheek.locatie.Locatie;
import be.ewdj.bibliotheek.auteur.AuteurRepository;
import be.ewdj.bibliotheek.locatie.LocatieRepository;

@SpringBootApplication
public class BibliotheekApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BibliotheekApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/catalogus");
		registry.addViewController("/403").setViewName("403");
	}

	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.getDefault());
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	CommandLineRunner commandLineRunner(BoekRepository boekRepo, LocatieRepository locatieRepo,
			AuteurRepository auteurRepo) {
		return args -> {
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
					auteur8, auteur9, auteur10, auteur11, auteur12, auteur13, auteur14, auteur15, auteur16, auteur17,
					auteur18));

			Boek boek1 = new Boek("Boergondiërs", "9789403145402", 17.99);
			Boek boek2 = new Boek("Harry Potter en de steen der wijzen", "9789061699761", 10.99);
			Boek boek3 = new Boek("In de ban van de ring", "9789025366799", 25.9);
			Boek boek4 = new Boek("It", "9789024576799", 12.99);
			Boek boek5 = new Boek("Game of Thrones", "9789025366799", 25.99);
			Boek boek6 = new Boek("Groter dan een droom", "9789025366799", 25.99);
			Boek boek7 = new Boek("Black", "9789025366799", 25.99);
			Boek boek8 = new Boek("Ex", "9789025366799", 25.99);
			Boek boek9 = new Boek("Sprakeloos", "9789025366799", 25.99);
			Boek boek10 = new Boek("Het vierkant van de wraak", "9789025366799", 25.99);
			Boek boek11 = new Boek("Het verdriet van België", "9789025366799", 25.99);
			Boek boek12 = new Boek("Dagelijkse kost", "9789025366799", 25.99);
			Boek boek13 = new Boek("Puur genieten", "9789025366799", 25.99);
			Boek boek14 = new Boek("Sergiology", "9789025366799", 25.9);
			Boek boek15 = new Boek("Jamie in 15 minuten", "9789025366799", 25.99);
			Boek boek16 = new Boek("Mijn 200 klassiekers", "9789025366799", 25.9);
			Boek boek17 = new Boek("The Subtle Art of Not Giving a F*ck", "9789025366799", 25.99);
			Boek boek18 = new Boek("Ik zie je op het strand", "9789025366799", 25.99);

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
					locatie7, locatie8, locatie9, locatie10, locatie11, locatie12, locatie13, locatie14, locatie15,
					locatie16, locatie17, locatie18));

		};
	}

}
