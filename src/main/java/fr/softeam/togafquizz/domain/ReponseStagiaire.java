package fr.softeam.togafquizz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ReponseStagiaire.
 */
@Entity
@Table(name = "T_REPONSESTAGIAIRE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReponseStagiaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Resultat resultat;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Reponse reponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReponseStagiaire reponseStagiaire = (ReponseStagiaire) o;

        if (id != null ? !id.equals(reponseStagiaire.id) : reponseStagiaire.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ReponseStagiaire{" +
                "id=" + id +
                '}';
    }
}
