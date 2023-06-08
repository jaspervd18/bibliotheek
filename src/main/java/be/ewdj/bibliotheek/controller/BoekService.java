package be.ewdj.bibliotheek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.repository.AuteurRepository;
import be.ewdj.bibliotheek.repository.BoekRepository;

@Service
public class BoekService {

    @Autowired
    private BoekRepository boekRepo;

    @Autowired
    private AuteurRepository auteurRepo;

    public Boek getBookByIsbn(String isbn) {
        return boekRepo.findByIsbn(isbn).orElseThrow();
    }

    public List<Boek> getBooksByAuthor(String auteurNaam) {
        return boekRepo.findBooksByAuthor(auteurNaam);
    }

}