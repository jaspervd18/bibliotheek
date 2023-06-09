package be.ewdj.bibliotheek.models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auteurs", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "naam", "voornaam" })
})
public class Auteur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  private String naam;

  @NotBlank
  private String voornaam;

  @ManyToMany(mappedBy = "auteurs")
  private List<Boek> boeken;

  public Auteur(String voornaam, String naam) {
    this.naam = naam;
    this.voornaam = voornaam;
  }

  public Auteur() {
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((naam == null) ? 0 : naam.hashCode());
    result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
    Auteur other = (Auteur) obj;
    if (naam == null) {
      if (other.naam != null)
        return false;
    } else if (!naam.equals(other.naam))
      return false;
    if (voornaam == null) {
      if (other.voornaam != null)
        return false;
    } else if (!voornaam.equals(other.voornaam))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return voornaam + " " + naam;
  }

}
