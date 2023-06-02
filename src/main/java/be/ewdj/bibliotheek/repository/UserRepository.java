package be.ewdj.bibliotheek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ewdj.bibliotheek.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}