package fr.softeam.togafquizz.repository;

import fr.softeam.togafquizz.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Session entity.
 */
public interface SessionRepository extends JpaRepository<Session,Long>{

}
