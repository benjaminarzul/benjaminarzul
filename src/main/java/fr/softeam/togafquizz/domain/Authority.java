package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "T_AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority implements Serializable {

	@NotNull
	@Size(min = 0, max = 50)
	@Id
	@Column(length = 50)
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	@Override
	public boolean equals(Object pObject) {
		if (this == pObject) {
			return true;
		}

		if (pObject == null || getClass() != pObject.getClass()) {
			return false;
		}

		Authority authority = (Authority) pObject;

		if (this.name != null ? !this.name.equals(authority.name)
				: authority.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return this.name != null ? this.name.hashCode() : 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Authority { ");
		builder.append("name=").append(this.name);
		builder.append(" }");

		return builder.toString();
	}

}
