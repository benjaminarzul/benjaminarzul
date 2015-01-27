package fr.softeam.togafquizz.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * Persist AuditEvent managed by the Spring Boot actuator
 * 
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
@Entity
@Table(name = "T_PERSISTENT_AUDIT_EVENT")
public class PersistentAuditEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id")
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String principal;

	@Column(name = "event_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime auditEventDate;
	@Column(name = "event_type")
	private String auditEventType;

	@ElementCollection
	@MapKeyColumn(name = "name")
	@Column(name = "value")
	@CollectionTable(name = "T_PERSISTENT_AUDIT_EVENT_DATA", joinColumns = @JoinColumn(name = "event_id"))
	private Map<String, String> data = new HashMap<>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long pId) {
		this.id = pId;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String pPrincipal) {
		this.principal = pPrincipal;
	}

	public LocalDateTime getAuditEventDate() {
		return this.auditEventDate;
	}

	public void setAuditEventDate(LocalDateTime pAuditEventDate) {
		this.auditEventDate = pAuditEventDate;
	}

	public String getAuditEventType() {
		return this.auditEventType;
	}

	public void setAuditEventType(String pAuditEventType) {
		this.auditEventType = pAuditEventType;
	}

	public Map<String, String> getData() {
		return this.data;
	}

	public void setData(Map<String, String> pMapData) {
		this.data = pMapData;
	}
}
