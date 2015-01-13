package fr.softeam.togafquizz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.softeam.togafquizz.domain.Quizz;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class QuizzService {

    private final Logger log = LoggerFactory.getLogger(QuizzService.class);

    public  Quizz createQuizzFromCSV(String CSVData) {
        Quizz quizz = new Quizz();
        String[] csv = CSVData.split(";");
        quizz.setLibelle(csv[1]);
        quizz.setNumero(Integer.parseInt(csv[2]));
        quizz.setDureeEnMinutes(Integer.parseInt(csv[3]));
        quizz.setType(Integer.parseInt(csv[4]));
        log.debug("Created information for quizz: {}", quizz);
        return quizz;
    }
}
