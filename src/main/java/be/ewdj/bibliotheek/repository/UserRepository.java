package be.ewdj.bibliotheek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ewdj.bibliotheek.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    Boolean existsByUsername(String username);
}