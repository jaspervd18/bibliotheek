package be.ewdj.bibliotheek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ewdj.bibliotheek.models.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {

    Auteur findById(long id);

    Auteur findByNaam(String naam);

    Auteur findByVoornaam(String voornaam);

}
