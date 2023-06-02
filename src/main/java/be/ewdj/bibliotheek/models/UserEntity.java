package be.ewdj.bibliotheek.models;

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

    public UserEntity(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
