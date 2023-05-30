package be.ewdj.bibliotheek.boek;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoekRepository extends JpaRepository<Boek, Long> {

  Boek findById(long id);

  @Query("SELECT b FROM Boek b WHERE b.isbn = ?1")
  Optional<Boek> findByIsbn(String isbn);

  @Query("SELECT b FROM Boek b ORDER BY b.aantalSterren DESC LIMIT 10")
  List<Boek> findMostPopular();

}
