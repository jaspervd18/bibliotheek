package be.ewdj.bibliotheek;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.models.Locatie;
import be.ewdj.bibliotheek.models.UserEntity;
import be.ewdj.bibliotheek.repository.AuteurRepository;
import be.ewdj.bibliotheek.repository.BoekRepository;
import be.ewdj.bibliotheek.repository.LocatieRepository;
import be.ewdj.bibliotheek.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
public class BibliotheekController {

    @Autowired
    private BoekRepository boekRepo;

    @Autowired
    private AuteurRepository auteurRepo;

    @Autowired
    private LocatieRepository locatieRepo;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/catalogus")
    public String toonCatalogus(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("listBoeken", boekRepo.findAll());
        return "catalogus";
    }

    @GetMapping("/favorieten")
    public String toonFavorieten(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("listBoeken", boekRepo.findMostPopular());
        return "favorieten";
    }

    @GetMapping("/detail/{isbn}")
    public String toonDetail(@PathVariable(value = "isbn") String isbn, Model model) {
        model.addAttribute("boek", boekRepo.findByIsbn(isbn));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "detail_boek";
    }

    @GetMapping("/nieuwBoek")
    public String nieuwBoek(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("boek", new Boek());
        return "nieuw_boek";
    }

    @PostMapping("/nieuwBoek")
    public String saveNieuwBoek(@Valid @ModelAttribute("boek") Boek boek) {
        Iterator<Auteur> itrAuteurs = boek.getAuteurs().iterator();
        while (itrAuteurs.hasNext()) {
            Auteur auteur = itrAuteurs.next();
            if (auteur.getNaam() == null || auteur.getNaam().equals("") ||
                    auteur.getVoornaam() == null
                    || auteur.getVoornaam().equals("")) {
                itrAuteurs.remove();
            } else {
                auteurRepo.save(auteur);
            }
        }
        Iterator<Locatie> itrLocaties = boek.getLocaties().iterator();
        while (itrLocaties.hasNext()) {
            Locatie locatie = itrLocaties.next();
            if (locatie.getCode1() == 0 || locatie.getCode2() == 0 || locatie.getPlaatsnaam() == null
                    || locatie.getPlaatsnaam().equals("")) {
                itrLocaties.remove();
            } else {
                locatie.setBoek(boek);
            }
        }
        boekRepo.save(boek);
        locatieRepo.saveAllAndFlush(boek.getLocaties());
        return "redirect:/";
    }

}
