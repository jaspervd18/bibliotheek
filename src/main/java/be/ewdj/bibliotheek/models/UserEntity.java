package be.ewdj.bibliotheek.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private Roles role;

    private int maxFavoieten;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_favorieten", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "boek_id"))
    private List<Boek> favorieten = new ArrayList<>();

    public UserEntity(String username, String password, Roles role, int maxFavoieten) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.maxFavoieten = maxFavoieten;
    }

    public void addFavoriet(Boek boek) {
        if (favorieten.size() < maxFavoieten) {
            favorieten.add(boek);
        }
    }

    public void removeFavoriet(Boek boek) {
        favorieten.remove(boek);
    }

    public boolean isMaxFavoritesReached() {
        return favorieten.size() >= maxFavoieten;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        UserEntity other = (UserEntity) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
