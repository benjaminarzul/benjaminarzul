package fr.softeam.togafquizz.repository;

import fr.softeam.togafquizz.domain.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Reponse entity.
 */
public interface ReponseRepository extends JpaRepository<Reponse,Long>{

}
