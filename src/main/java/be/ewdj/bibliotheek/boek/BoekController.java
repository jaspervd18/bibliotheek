package be.ewdj.bibliotheek.boek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import be.ewdj.bibliotheek.auteur.Auteur;
import be.ewdj.bibliotheek.auteur.AuteurRepository;
import be.ewdj.bibliotheek.locatie.Locatie;

@Controller
public class BoekController {

  @Autowired
  private BoekRepository boekRepo;

  @Autowired
  private AuteurRepository auteurRepo;

  @GetMapping("/catalogus")
  public String viewHomePage(Model model, Principal principal) {
    model.addAttribute("username", principal.getName());
    model.addAttribute("listBoeken", boekRepo.findAll());
    return "index";
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

  @PostMapping("/saveBoek")
  public String saveBoek(@ModelAttribute("boek") Boek boek, @ModelAttribute("auteur1") Auteur auteur1) {
    auteurRepo.save(auteur1);
    boekRepo.save(boek);
    return "redirect:/";
  }

}
