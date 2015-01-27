package fr.softeam.togafquizz.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

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
	@Lob
	@NotNull
	private String libelle;

	@Column(nullable = false)
	@NotNull
	private Integer numero;

	@Column(nullable = false)
	@Lob
	@NotNull
	private String scenario;

	@Column
	@Lob
	private String explication;

	@ManyToOne
	private Quizz quizz;

	@OneToOne
	private Reponse meilleureReponse;

	@OneToOne
	private Reponse secondeMeilleureReponse;

	@OneToOne
	private Reponse troisiemeMeilleureReponse;

	@JoinTable(name = "T_QUESTION_REPONSE", joinColumns = { @JoinColumn(name = "question_id") }, inverseJoinColumns = { @JoinColumn(name = "reponse_id") })
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Reponse> reponses = new HashSet<Reponse>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long pId) {
		this.id = pId;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String pLibelle) {
		this.libelle = pLibelle;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer pNumero) {
		this.numero = pNumero;
	}

	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(String pScenario) {
		this.scenario = pScenario;
	}

	public String getExplication() {
		return this.explication;
	}

	public void setExplication(String pExplication) {
		this.explication = pExplication;
	}

	public Quizz getQuizz() {
		return this.quizz;
	}

	public void setQuizz(Quizz pQuizz) {
		this.quizz = pQuizz;
	}

	public Reponse getMeilleureReponse() {
		return this.meilleureReponse;
	}

	public void setMeilleureReponse(Reponse pMeilleureReponse) {
		this.meilleureReponse = pMeilleureReponse;
	}

	public Reponse getSecondeMeilleureReponse() {
		return this.secondeMeilleureReponse;
	}

	public void setSecondeMeilleureReponse(Reponse pSecondeMeilleureReponse) {
		this.secondeMeilleureReponse = pSecondeMeilleureReponse;
	}

	public Reponse getTroisiemeMeilleureReponse() {
		return this.troisiemeMeilleureReponse;
	}

	public void setTroisiemeMeilleureReponse(Reponse pTroisiemeMeilleureReponse) {
		this.troisiemeMeilleureReponse = pTroisiemeMeilleureReponse;
	}

	public Set<Reponse> getReponses() {
		return this.reponses;
	}

	public void setReponses(Set<Reponse> pSetReponse) {
		this.reponses = pSetReponse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.libelle == null) ? 0 : this.libelle.hashCode());
		result = prime * result
				+ ((this.numero == null) ? 0 : this.numero.hashCode());
		result = prime * result
				+ ((this.quizz == null) ? 0 : this.quizz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object pObject) {
		if (this == pObject) {
			return true;
		}

		if (pObject == null) {
			return false;
		}

		if (getClass() != pObject.getClass()) {
			return false;
		}

		Question other = (Question) pObject;

		if (this.libelle == null) {
			if (other.libelle != null) {
				return false;
			}
		} else if (!this.libelle.equals(other.libelle)) {
			return false;
		}

		if (this.numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!this.numero.equals(other.numero)) {
			return false;
		}

		if (this.quizz == null) {
			if (other.quizz != null) {
				return false;
			}
		} else if (!this.quizz.equals(other.quizz)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Question { ");
		builder.append("id=").append(this.id);
		builder.append(", libelle=").append(this.libelle);
		builder.append(", numero=").append(this.numero);
		builder.append(", scenario=").append(this.scenario);
		builder.append(", explication=").append(this.explication);
		builder.append(", quizz=")
				.append(this.quizz == null ? "<aucun>" : this.quizz
						.toStringSimplifie());
		builder.append(", meilleureReponse=").append(
				this.meilleureReponse == null ? "<aucune>"
						: this.meilleureReponse.toStringSimplifie());
		builder.append(", secondeMeilleureReponse=").append(
				this.secondeMeilleureReponse == null ? "<aucune>"
						: this.secondeMeilleureReponse.toStringSimplifie());
		builder.append(", troisiemeMeilleureReponse=").append(
				this.troisiemeMeilleureReponse == null ? "<aucune>"
						: this.troisiemeMeilleureReponse.toStringSimplifie());

		// réponses
		builder.append(", reponses=");

		if (CollectionUtils.isEmpty(this.reponses)) {
			builder.append("<aucune>");
		} else {
			builder.append("{ nombre=").append(this.reponses.size());
			builder.append(", donnees=[ ");

			for (Reponse curReponse : this.reponses) {
				builder.append(curReponse.toStringSimplifie());
				builder.append(", ");
			}

			builder.delete(builder.length() - ", ".length(), builder.length());
			builder.append(" ] }");
		}

		builder.append(" }");

		return builder.toString();
	}

	public String toStringSimplifie() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.id).append('-').append(this.libelle);

		return builder.toString();
	}

}
