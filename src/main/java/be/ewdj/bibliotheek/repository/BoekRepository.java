package be.ewdj.bibliotheek.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import be.ewdj.bibliotheek.models.Locatie;
import jakarta.transaction.Transactional;

public interface BoekRepository extends JpaRepository<Boek, Long> {

  Boek findById(long id);

  Optional<Boek> findByIsbn(String isbn);

  @Query("SELECT b FROM Boek b ORDER BY b.aantalSterren DESC, b.titel ASC LIMIT 10")
  List<Boek> findMostPopular();

  @Query("SELECT b FROM Boek b JOIN b.auteurs a WHERE a.naam LIKE ?1 OR a.voornaam LIKE ?1")
  List<Boek> findBooksByAuthor(Auteur auteur);

  @Modifying
  @Transactional
  @Query("UPDATE Boek b SET b.titel = :titel, b.aankoopPrijs = :aankoopPrijs, b.locaties = :locaties, b.auteurs = :auteurs WHERE b.isbn = :isbn")
  void updateBookFieldsWithAssociations(@Param("isbn") String isbn, @Param("titel") String titel,
      @Param("aankoopPrijs") double aankoopPrijs);

}
