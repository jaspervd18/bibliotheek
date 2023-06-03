package be.ewdj.bibliotheek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.repository.BoekRepository;

@Service
public class BoekService {

    @Autowired
    private BoekRepository boekRepo;

    public Boek getBookByIsbn(String isbn) {
        return boekRepo.findByIsbn(isbn).orElseThrow();
    }

    public List<Boek> getBooksByAuthor(String auteurNaam, String auteurVoornaam) {
        Auteur auteur = new Auteur(auteurNaam, auteurVoornaam);
        return boekRepo.findBooksByAuthor(auteur);
    }

}