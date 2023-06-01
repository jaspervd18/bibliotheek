package be.ewdj.bibliotheek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ewdj.bibliotheek.auteur.Auteur;
import be.ewdj.bibliotheek.auteur.AuteurRepository;
import be.ewdj.bibliotheek.boek.Boek;
import be.ewdj.bibliotheek.boek.BoekRepository;
import be.ewdj.bibliotheek.locatie.Locatie;

@Controller
public class BibliotheekController {

    @Autowired
    private BoekRepository boekRepo;

    @Autowired
    private AuteurRepository auteurRepo;

    @GetMapping(value = "/catalogus")
    public String viewHomePage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("listBoeken", boekRepo.findAll());
        return "catalogus";
    }

    @GetMapping("/favorieten")
    public String toonMeestFavorieteBoeken(Model model) {
        model.addAttribute("listBoeken", boekRepo.findMostPopular());
        return "favorieten";
    }

    @GetMapping("/detail/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get boek from the service
        Boek boek = boekRepo.findById(id);
        // set boek as a model attribute to pre-populate the form
        model.addAttribute("boek", boek);
        return "detail_boek";
    }

    @GetMapping("/nieuwBoek")
    public String toonNieuwBoekForm(Model model) {
        Boek boek = new Boek();
        Auteur auteur1 = new Auteur();
        boek.addAuteur(auteur1);
        model.addAttribute("boek", boek);
        return "nieuw_boek";
    }

    @PostMapping("/nieuwBoek")
    public String saveBoek(@ModelAttribute("boek") Boek boek, @ModelAttribute("auteur1") Auteur auteur1) {
        auteurRepo.save(auteur1);
        boekRepo.save(boek);
        return "redirect:/";
    }

}
