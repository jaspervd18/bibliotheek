package be.ewdj.bibliotheek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ewdj.bibliotheek.models.Locatie;

public interface LocatieRepository extends JpaRepository<Locatie, Long> {

    Locatie findById(long id);

}
