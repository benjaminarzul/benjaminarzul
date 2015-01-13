package fr.softeam.togafquizz.repository;

import fr.softeam.togafquizz.domain.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quizz entity.
 */
public interface QuizzRepository extends JpaRepository<Quizz,Long>{

}
