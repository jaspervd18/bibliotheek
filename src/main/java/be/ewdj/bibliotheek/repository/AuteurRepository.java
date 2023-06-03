package be.ewdj.bibliotheek.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ewdj.bibliotheek.models.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {

    Optional<Auteur> findById(long id);

    Auteur findByNaamAndVoornaam(String naam, String voornaam);

}
