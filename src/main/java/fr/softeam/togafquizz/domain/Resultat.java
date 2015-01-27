package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Resultat.
 */
@Entity
@Table(name = "T_RESULTAT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Resultat implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "pourcentage_reussite", nullable = false)
	@NotNull
	private Integer pourcentageReussite;

	@ManyToOne
	private User user;

	@ManyToOne
	private Quizz quizz;

	public Long getId() {
		return this.id;
	}

	public void setId(Long pId) {
		this.id = pId;
	}

	public Integer getPourcentageReussite() {
		return this.pourcentageReussite;
	}

	public void setPourcentageReussite(Integer pPourcentageReussite) {
		this.pourcentageReussite = pPourcentageReussite;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User pUser) {
		this.user = pUser;
	}

	public Quizz getQuizz() {
		return this.quizz;
	}

	public void setQuizz(Quizz pQuizz) {
		this.quizz = pQuizz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.pourcentageReussite == null) ? 0
						: this.pourcentageReussite.hashCode());
		result = prime * result
				+ ((this.quizz == null) ? 0 : this.quizz.hashCode());
		result = prime * result
				+ ((this.user == null) ? 0 : this.user.hashCode());
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

		Resultat other = (Resultat) pObject;

		if (this.pourcentageReussite == null) {
			if (other.pourcentageReussite != null) {
				return false;
			}
		} else if (!this.pourcentageReussite.equals(other.pourcentageReussite)) {
			return false;
		}

		if (this.quizz == null) {
			if (other.quizz != null) {
				return false;
			}
		} else if (!this.quizz.equals(other.quizz)) {
			return false;
		}

		if (this.user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!this.user.equals(other.user)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Resultat { ");
		builder.append("id=").append(this.id);
		builder.append(", pourcentageReussite=").append(
				this.pourcentageReussite);
		builder.append(", quizz=")
				.append(this.quizz == null ? "<aucun>" : this.quizz
						.toStringSimplifie());
		builder.append(", user=").append(
				this.user == null ? "<aucun>" : this.user.toStringSimplifie());
		builder.append(" }");

		return builder.toString();
	}

}
