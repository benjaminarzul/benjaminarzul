package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Reponse.
 */
@Entity
@Table(name = "T_REPONSE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reponse implements Serializable {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.libelle == null) ? 0 : this.libelle.hashCode());
		result = prime * result
				+ ((this.numero == null) ? 0 : this.numero.hashCode());
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

		Reponse other = (Reponse) pObject;

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

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Reponse { ");
		builder.append("id=").append(this.id);
		builder.append(", libelle=").append(this.libelle);
		builder.append(", numero=").append(this.numero);
		builder.append(" }");

		return builder.toString();
	}

	public String toStringSimplifie() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.id).append('-').append(this.libelle);

		return builder.toString();
	}
}
