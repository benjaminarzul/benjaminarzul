package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Quizz.
 */
@Entity
@Table(name = "T_QUIZZ")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Quizz implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
	@NotNull
	@Size(min = 1, max = 50)
	private String libelle;

	@Column
	@NotNull
	private Integer numero;

	@Column(name = "duree_en_minutes", nullable = false)
	@NotNull
	private Integer dureeEnMinutes;

	@Column(nullable = false)
	@NotNull
	private Integer type;

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

	public Integer getDureeEnMinutes() {
		return this.dureeEnMinutes;
	}

	public void setDureeEnMinutes(Integer pDureeEnMinutes) {
		this.dureeEnMinutes = pDureeEnMinutes;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer pType) {
		this.type = pType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.libelle == null) ? 0 : libelle.hashCode());
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

		Quizz other = (Quizz) pObject;

		if (this.libelle == null) {
			if (other.libelle != null) {
				return false;
			}
		} else if (!this.libelle.equals(other.libelle)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Quizz { ");
		builder.append("id=").append(this.id);
		builder.append(", libelle=").append(this.libelle);
		builder.append(", numero=").append(this.numero);
		builder.append(", dureeEnMinutes=").append(this.dureeEnMinutes);
		builder.append(", type=").append(this.type);
		builder.append(" }");

		return builder.toString();
	}

	public String toStringSimplifie() {
		return this.libelle;
	}
}
