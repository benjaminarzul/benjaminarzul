package fr.softeam.togafquizz.repository;

import fr.softeam.togafquizz.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Question entity.
 */
public interface QuestionRepository extends JpaRepository<Question,Long>{

}
