package fr.softeam.togafquizz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.softeam.togafquizz.domain.Resultat;
import fr.softeam.togafquizz.domain.User;

/**
 * Spring Data JPA repository for the Resultat entity.
 */
public interface ResultatRepository extends JpaRepository<Resultat, Long> {

	List<Resultat> findByUser(User user);

}
