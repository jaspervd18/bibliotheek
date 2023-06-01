package be.ewdj.bibliotheek.auteur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {

    Auteur findById(long id);

    Auteur findByNaam(String naam);

    Auteur findByVoornaam(String voornaam);

}
