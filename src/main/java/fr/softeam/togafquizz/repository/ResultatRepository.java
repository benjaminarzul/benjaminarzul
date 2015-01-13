package fr.softeam.togafquizz.repository;

import fr.softeam.togafquizz.domain.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Resultat entity.
 */
public interface ResultatRepository extends JpaRepository<Resultat,Long>{

}
