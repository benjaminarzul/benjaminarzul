package fr.softeam.togafquizz.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified by and created, last modified by date.
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity {

	@CreatedBy
	@NotNull
	@Column(name = "created_by", nullable = false, length = 50, updatable = false)
	private String createdBy;

	@CreatedDate
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "created_date", nullable = false)
	private DateTime createdDate = DateTime.now();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 50)
	private String lastModifiedBy;

	@LastModifiedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "last_modified_date")
	private DateTime lastModifiedDate = DateTime.now();

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String pCreatedBy) {
		this.createdBy = pCreatedBy;
	}

	public DateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(DateTime pCreatedDate) {
		this.createdDate = pCreatedDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String pLastModifiedBy) {
		this.lastModifiedBy = pLastModifiedBy;
	}

	public DateTime getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(DateTime pLastModifiedDate) {
		this.lastModifiedDate = pLastModifiedDate;
	}
}
