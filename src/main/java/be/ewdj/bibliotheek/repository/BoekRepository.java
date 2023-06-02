package be.ewdj.bibliotheek.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.ewdj.bibliotheek.models.Boek;

public interface BoekRepository extends JpaRepository<Boek, Long> {

  Boek findById(long id);

  @Query("SELECT b FROM Boek b WHERE b.isbn = ?1")
  Boek findByIsbn(String isbn);

  @Query("SELECT b FROM Boek b ORDER BY b.aantalSterren DESC, b.titel ASC LIMIT 10")
  List<Boek> findMostPopular();

  @Query("SELECT b FROM Boek b JOIN b.auteurs a WHERE a.naam LIKE ?1 OR a.voornaam LIKE ?1")
  List<Boek> findBooksByAuthor(String auteur);

}
