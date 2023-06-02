package be.ewdj.bibliotheek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ewdj.bibliotheek.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
