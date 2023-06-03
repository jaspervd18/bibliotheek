package be.ewdj.bibliotheek.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.format.annotation.NumberFormat;

import be.ewdj.bibliotheek.validator.AuteursNotEmpty;
import be.ewdj.bibliotheek.validator.LocatiesNotEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boeken")
public class Boek {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "{titel.verplicht}")
  private String titel;

  @ISBN(message = "{isbn.ongeldig}")
  @NotBlank(message = "{isbn.verplicht}")
  @Column(unique = true)
  private String isbn;

  @DecimalMin(value = "0.00", inclusive = false, message = "{aankoopPrijs.teLaag}")
  @DecimalMax(value = "100.00", inclusive = false, message = "{aankoopPrijs.teHoog}")
  @NumberFormat(pattern = "0.00", style = NumberFormat.Style.NUMBER)
  private double aankoopPrijs;

  private int aantalSterren;

  @ManyToMany
  @JoinTable(name = "auteur_boeken", joinColumns = @JoinColumn(name = "boek_id"), inverseJoinColumns = @JoinColumn(name = "auteur_id"))
  @AuteursNotEmpty
  private List<Auteur> auteurs = new ArrayList<>();

  @OneToMany(mappedBy = "boek")
  @LocatiesNotEmpty
  private List<Locatie> locaties = new ArrayList<>(3);

  public Boek(String titel, String isbn, double aankoopPrijs) {
    this.titel = titel;
    this.isbn = isbn;
    this.aankoopPrijs = aankoopPrijs;
    this.aantalSterren = 0;
  }

  public Boek() {
  }

  public void addAuteur(Auteur auteur) {
    auteurs.add(auteur);
  }

  public void removeAuteur(Auteur auteur) {
    auteurs.remove(auteur);
  }

  public void addLocatie(Locatie locatie) {
    locaties.add(locatie);
  }

  public void removeLocatie(Locatie locatie) {
    locaties.remove(locatie);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Boek other = (Boek) obj;
    if (isbn == null) {
      if (other.isbn != null)
        return false;
    } else if (!isbn.equals(other.isbn))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return titel;
  }

}
