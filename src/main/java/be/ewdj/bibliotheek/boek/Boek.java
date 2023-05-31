package be.ewdj.bibliotheek.boek;

import java.util.ArrayList;
import java.util.List;

import be.ewdj.bibliotheek.auteur.Auteur;
import be.ewdj.bibliotheek.locatie.Locatie;
import jakarta.persistence.*;

@Entity
@Table(name = "boeken")
public class Boek {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String titel;

  @ManyToMany
  @JoinTable(name = "auteur_boeken", joinColumns = @JoinColumn(name = "boek_id"), inverseJoinColumns = @JoinColumn(name = "auteur_id"))
  private List<Auteur> auteurs = new ArrayList<>();

  private String isbn;

  private double aankoopPrijs;

  private int aantalSterren;

  @OneToMany(mappedBy = "boek")
  private List<Locatie> locaties = new ArrayList<>();

  public Boek(String titel, String isbn, double aankoopPrijs) {
    this.titel = titel;
    this.isbn = isbn;
    this.aankoopPrijs = aankoopPrijs;
    this.aantalSterren = 0;
  }

  public Boek(String titel, String isbn) {
    this.titel = titel;
    this.isbn = isbn;
  }

  public Boek() {
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public double getAankoopPrijs() {
    return aankoopPrijs;
  }

  public void setAankoopPrijs(double aankoopPrijs) {
    this.aankoopPrijs = aankoopPrijs;
  }

  public int getAantalSterren() {
    return aantalSterren;
  }

  public void setAantalSterren(int aantalSterren) {
    this.aantalSterren = aantalSterren;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Auteur> getAuteurs() {
    return auteurs;
  }

  public void setAuteurs(List<Auteur> auteurs) {
    this.auteurs = auteurs;
  }

  public List<Locatie> getLocaties() {
    return locaties;
  }

  public void setLocaties(List<Locatie> locaties) {
    this.locaties = locaties;
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
    return "Book [titel=" + titel + "]";
  }

}
