package be.ewdj.bibliotheek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ewdj.bibliotheek.models.Boek;

@RestController
@RequestMapping("/api/boeken/")
public class BoekController {

    @Autowired
    private BoekService boekService;

    @GetMapping("{isbn}")
    public Boek getBookByIsbn(@PathVariable String isbn) {
        return boekService.getBookByIsbn(isbn);
    }

    @GetMapping("/auteur/{auteurNaam}/{auteurVoornaam}")
    public List<Boek> getBooksByAuthor(@RequestBody String auteurNaam, @RequestBody String auteurVoornaam) {
        return boekService.getBooksByAuthor(auteurNaam, auteurVoornaam);
    }
}
