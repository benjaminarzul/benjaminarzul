package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.softeam.togafquizz.domain.util.CustomLocalDateSerializer;
import fr.softeam.togafquizz.domain.util.ISO8601LocalDateDeserializer;

/**
 * A Session.
 */
@Entity
@Table(name = "T_SESSION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Session implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
	@Column(nullable = false)
	@NotNull
	private LocalDate debut;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
	@Column(nullable = false)
	@NotNull
	private LocalDate fin;

	public Long getId() {
		return this.id;
	}

	public void setId(Long pId) {
		this.id = pId;
	}

	public LocalDate getDebut() {
		return this.debut;
	}

	public void setDebut(LocalDate pDebut) {
		this.debut = pDebut;
	}

	public LocalDate getFin() {
		return this.fin;
	}

	public void setFin(LocalDate pFin) {
		this.fin = pFin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.debut == null) ? 0 : debut.hashCode());
		result = prime * result + ((this.fin == null) ? 0 : fin.hashCode());
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

		Session other = (Session) pObject;

		if (this.debut == null) {
			if (other.debut != null) {
				return false;
			}
		} else if (!this.debut.equals(other.debut)) {
			return false;
		}

		if (this.fin == null) {
			if (other.fin != null) {
				return false;
			}
		} else if (!this.fin.equals(other.fin)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Session { ");
		builder.append("id=").append(this.id);
		builder.append(", debut=").append(this.debut);
		builder.append(", fin=").append(this.fin);
		builder.append(" }");

		return builder.toString();
	}

	public String toStringSimplifie() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.id).append('-').append(this.debut).append('/')
				.append(this.fin);

		return builder.toString();
	}

}
