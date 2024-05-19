package ctu.cit.se.authenticationservice.repositories;

import ctu.cit.se.authenticationservice.entities.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, UUID> {
    Optional<CustomUser> findByUsername(String username);
    Optional<CustomUser> findCustomUserByUsernameAndPassword(String username, String password);
}
