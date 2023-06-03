package be.ewdj.bibliotheek.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.repository.AuteurRepository;
import be.ewdj.bibliotheek.repository.BoekRepository;

@RestController
@RequestMapping("/catalogus")
public class BoekController {

    @Autowired
    private BoekRepository boekRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @GetMapping("/catalogus/{auteurId}")
    public List<Boek> getBooksByAuthor(@PathVariable Long auteurId) {
        Optional<Auteur> optionalAuteur = auteurRepository.findById(auteurId);
        if (optionalAuteur.isPresent()) {
            Auteur auteur = optionalAuteur.get();
            return boekRepository.findBooksByAuthor(auteur);
        }
        return Collections.emptyList();
    }

    @GetMapping("/boek/{isbn}")
    public ResponseEntity<Boek> getBoekByIsbn(@PathVariable String isbn) {
        Optional<Boek> optionalBoek = boekRepository.findByIsbn(isbn);
        return optionalBoek
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
