package fr.softeam.togafquizz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Question.
 */
@Entity
@Table(name = "T_QUESTION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "scenario")
    private String scenario;

    @ManyToOne
    private Quizz quizz;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reponse> reponses = new HashSet<>();

    @OneToOne
    private Reponse meilleureReponse;

    @OneToOne
    private Reponse secondeMeilleureReponse;

    @OneToOne
    private Reponse troisiemeMeilleureReponse;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reponse> moinsBonneReponses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public Reponse getMeilleureReponse() {
        return meilleureReponse;
    }

    public void setMeilleureReponse(Reponse meilleureReponse) {
        this.meilleureReponse = meilleureReponse;
    }

    public Reponse getSecondeMeilleureReponse() {
        return secondeMeilleureReponse;
    }

    public void setSecondeMeilleureReponse(Reponse secondeMeilleureReponse) {
        this.secondeMeilleureReponse = secondeMeilleureReponse;
    }

    public Reponse getTroisiemeMeilleureReponse() {
        return troisiemeMeilleureReponse;
    }

    public void setTroisiemeMeilleureReponse(Reponse troisiemeMeilleureReponse) {
        this.troisiemeMeilleureReponse = troisiemeMeilleureReponse;
    }

    public Set<Reponse> getMoinsBonneReponses() {
        return moinsBonneReponses;
    }

    public void setMoinsBonneReponses(Set<Reponse> moinsBonneReponses) {
        this.moinsBonneReponses = moinsBonneReponses;
    }

    public Set<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(Set<Reponse> reponses) {
        this.reponses = reponses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", libelle='" + libelle + "'" +
                ", scenario='" + scenario + "'" +
                '}';
    }
}
