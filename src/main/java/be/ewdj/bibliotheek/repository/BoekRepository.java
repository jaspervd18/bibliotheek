package be.ewdj.bibliotheek.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.ewdj.bibliotheek.models.Auteur;
import be.ewdj.bibliotheek.models.Boek;
import jakarta.transaction.Transactional;

public interface BoekRepository extends JpaRepository<Boek, Long> {

  Boek findById(long id);

  Optional<Boek> findByIsbn(String isbn);

  @Query("SELECT b FROM Boek b JOIN b.gebruikers u GROUP BY b ORDER BY COUNT(u) DESC, b.titel ASC")
  List<Boek> findMostPopular();

  @Query("SELECT b FROM Boek b JOIN b.auteurs a WHERE a.naam LIKE %:naam% OR a.voornaam LIKE %:naam%")
  List<Boek> findBooksByAuthor(@Param("naam") String naam);

  @Modifying
  @Transactional
  @Query("UPDATE Boek b SET b.titel = :titel, b.aankoopPrijs = :aankoopPrijs WHERE b.isbn = :isbn")
  void updateBookFields(@Param("isbn") String isbn, @Param("titel") String titel,
      @Param("aankoopPrijs") double aankoopPrijs);

}
