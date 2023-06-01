package be.ewdj.bibliotheek.locatie;

import be.ewdj.bibliotheek.boek.Boek;
import jakarta.persistence.*;

@Entity
@Table(name = "locaties")
public class Locatie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private int code1;
  private int code2;
  private String plaatsnaam;

  @ManyToOne
  private Boek boek;

  public Locatie(int code1, int code2, String plaatsnaam) {
    this.code1 = code1;
    this.code2 = code2;
    this.plaatsnaam = plaatsnaam;
  }

  public Locatie() {
  }

  public int getCode1() {
    return code1;
  }

  public void setCode1(int code1) {
    this.code1 = code1;
  }

  public int getCode2() {
    return code2;
  }

  public void setCode2(int code2) {
    this.code2 = code2;
  }

  public String getPlaatsnaam() {
    return plaatsnaam;
  }

  public void setPlaatsnaam(String plaatsnaam) {
    this.plaatsnaam = plaatsnaam;
  }

  public Boek getBoek() {
    return boek;
  }

  public void setBoek(Boek boek) {
    this.boek = boek;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + code1;
    result = prime * result + code2;
    result = prime * result + ((plaatsnaam == null) ? 0 : plaatsnaam.hashCode());
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
    Locatie other = (Locatie) obj;
    if (code1 != other.code1)
      return false;
    if (code2 != other.code2)
      return false;
    if (plaatsnaam == null) {
      if (other.plaatsnaam != null)
        return false;
    } else if (!plaatsnaam.equals(other.plaatsnaam))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return String.format("%d, %d: %s", code1, code2, plaatsnaam);
  }

}
