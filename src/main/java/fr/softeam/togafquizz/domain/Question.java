package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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

	@Column(nullable = false)
	@NotNull
	private String libelle;

	@Column(nullable = false)
	@NotNull
	private Integer numero;

	@Column(nullable = false)
	@NotNull
	private String scenario;

	@Column
	private String explication;

	@ManyToOne
	private Quizz quizz;

	@OneToOne
	private Reponse meilleureReponse;

	@OneToOne
	private Reponse secondeMeilleureReponse;

	@OneToOne
	private Reponse troisiemeMeilleureReponse;

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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getExplication() {
		return explication;
	}

	public void setExplication(String explication) {
		this.explication = explication;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Question question = (Question) o;

		if (id != null ? !id.equals(question.id) : question.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Question{" + "id=" + id + ", libelle='" + libelle + "'"
				+ ", numero='" + numero + "'" + ", scenario='" + scenario + "'"
				+ ", explication='" + explication + "'" + '}';
	}
}
