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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPourcentageReussite() {
		return pourcentageReussite;
	}

	public void setPourcentageReussite(Integer pourcentageReussite) {
		this.pourcentageReussite = pourcentageReussite;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quizz getQuizz() {
		return quizz;
	}

	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Resultat resultat = (Resultat) o;

		if (id != null ? !id.equals(resultat.id) : resultat.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Resultat{" + "id=" + id + ", pourcentageReussite='"
				+ pourcentageReussite + "'" + '}';
	}
}
