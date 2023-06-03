package be.ewdj.bibliotheek.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locaties")
public class Locatie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Min(50)
  @Max(300)
  private int code1;

  @Min(50)
  @Max(300)
  private int code2;

  @NotBlank(message = "{locatie.plaatsnaam.verplicht}")
  @Pattern(regexp = "[A-Z][a-z]+", message = "{locatie.plaatsnaam.patroon}")
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
