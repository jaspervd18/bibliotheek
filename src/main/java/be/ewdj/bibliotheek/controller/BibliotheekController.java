package be.ewdj.bibliotheek.controller;

import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.models.Locatie;
import be.ewdj.bibliotheek.models.UserEntity;
import be.ewdj.bibliotheek.repository.AuteurRepository;
import be.ewdj.bibliotheek.repository.BoekRepository;
import be.ewdj.bibliotheek.repository.LocatieRepository;
import be.ewdj.bibliotheek.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("user")
    public UserEntity getUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(auth.getName());
        return optionalUser.orElse(null);
    }

    @GetMapping(value = "/catalogus")
    public String toonCatalogus(Model model) {
        model.addAttribute("listBoeken", boekRepo.findAll());
        return "catalogus";
    }

    @GetMapping("/catalogus/favorieten")
    public String toonFavorieten(Model model) {
        model.addAttribute("listBoeken", boekRepo.findMostPopular());
        return "favorieten";
    }

    @GetMapping("/catalogus/{isbn}")
    public String toonDetail(@PathVariable(value = "isbn") String isbn, Model model) {
        Optional<Boek> optionalBoek = boekRepo.findByIsbn(isbn);
        Boek boek = optionalBoek.orElse(null);

        if (boek == null) {
            return "error_page";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(auth.getName());
        UserEntity user = optionalUser.orElse(null);

        model.addAttribute("boek", boek);
        return user.getRole().toString() == "USER" ? "detail_boek" : "edit_boek";
    }

    @PostMapping("/catalogus/{isbn}/favoriet")
    public String favoriet(@PathVariable(value = "isbn") String isbn, Model model) {
        Optional<Boek> optionalBoek = boekRepo.findByIsbn(isbn);
        Boek boek = optionalBoek.orElse(null);

        if (boek == null) {
            return "error_page";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(auth.getName());
        UserEntity user = optionalUser.orElse(null);

        if (user == null) {
            return "error_page";
        }

        if (user.getFavorieten().contains(boek)) {
            user.removeFavoriet(boek);
        } else {
            user.addFavoriet(boek);
        }

        userRepository.save(user);

        model.addAttribute("boek", boek);
        return "redirect:/";
    }

    @GetMapping("/catalogus/new")
    public String nieuwBoek(Model model) {
        model.addAttribute("boek", new Boek());
        return "edit_boek";
    }

    @Transactional
    @PostMapping("/catalogus/save")
    public String handleSave(@Valid Boek boek, BindingResult result, RedirectAttributes redirectAttributes,
            Model model) {
        if (result.hasErrors()) {
            return "edit_boek";
        }

        Iterator<Auteur> itrAuteurs = boek.getAuteurs().iterator();
        while (itrAuteurs.hasNext()) {
            Auteur auteur = itrAuteurs.next();
            if (auteur.getNaam() == null || auteur.getNaam().isEmpty() ||
                    auteur.getVoornaam() == null || auteur.getVoornaam().isEmpty()) {
                itrAuteurs.remove();
            } else {
                Auteur existingAuteur = auteurRepo.findByNaamAndVoornaam(auteur.getNaam(), auteur.getVoornaam());
                if (existingAuteur == null) {
                    auteurRepo.save(auteur);
                } else {
                    auteur.setId(existingAuteur.getId());
                }
            }
        }

        Iterator<Locatie> itrLocaties = boek.getLocaties().iterator();
        while (itrLocaties.hasNext()) {
            Locatie locatie = itrLocaties.next();
            if (locatie.getCode1() == 0 || locatie.getCode2() == 0 || locatie.getPlaatsnaam() == null ||
                    locatie.getPlaatsnaam().isEmpty()) {
                itrLocaties.remove();
            } else {
                locatie.setBoek(boek);
            }
        }

        Optional<Boek> existingBoek = boekRepo.findByIsbn(boek.getIsbn());
        if (existingBoek.isPresent()) {
            // boekRepo.updateBookFields(boek.getIsbn(), boek.getTitel(),
            // boek.getAankoopPrijs());
            boekRepo.updateBookFieldsWithAssociations(boek.getIsbn(), boek.getTitel(), boek.getAankoopPrijs());
            return "redirect:/";
        } else {
            boekRepo.save(boek);
        }
        locatieRepo.saveAllAndFlush(boek.getLocaties());
        return "redirect:/";
    }

}
