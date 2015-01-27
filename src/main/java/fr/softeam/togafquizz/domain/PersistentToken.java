package fr.softeam.togafquizz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent tokens are used by Spring Security to automatically log in users.
 * 
 * @see fr.softeam.togafquizz.security.CustomPersistentRememberMeServices
 */
@Entity
@Table(name = "T_PERSISTENT_TOKEN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PersistentToken implements Serializable {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat
			.forPattern("d MMMM yyyy");

	private static final int MAX_USER_AGENT_LEN = 255;

	@Id
	private String series;

	@JsonIgnore
	@NotNull
	@Column(name = "token_value", nullable = false)
	private String tokenValue;

	@JsonIgnore
	@Column(name = "token_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate tokenDate;

	// an IPV6 address max length is 39 characters
	@Size(min = 0, max = 39)
	@Column(name = "ip_address", length = 39)
	private String ipAddress;

	@Column(name = "user_agent")
	private String userAgent;

	@JsonIgnore
	@ManyToOne
	private User user;

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String pSeries) {
		this.series = pSeries;
	}

	public String getTokenValue() {
		return this.tokenValue;
	}

	public void setTokenValue(String pTokenValue) {
		this.tokenValue = pTokenValue;
	}

	public LocalDate getTokenDate() {
		return this.tokenDate;
	}

	public void setTokenDate(LocalDate pTokenDate) {
		this.tokenDate = pTokenDate;
	}

	@JsonGetter
	public String getFormattedTokenDate() {
		return DATE_TIME_FORMATTER.print(this.tokenDate);
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String pIpAddress) {
		this.ipAddress = pIpAddress;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String pUserAgent) {
		if (pUserAgent.length() >= MAX_USER_AGENT_LEN) {
			this.userAgent = pUserAgent.substring(0, MAX_USER_AGENT_LEN - 1);
		} else {
			this.userAgent = pUserAgent;
		}
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User pUser) {
		this.user = pUser;
	}

	@Override
	public boolean equals(Object pObject) {
		if (this == pObject) {
			return true;
		}
		if (pObject == null || getClass() != pObject.getClass()) {
			return false;
		}

		PersistentToken that = (PersistentToken) pObject;

		if (!this.series.equals(that.series)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return this.series.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("PersistentToken { ");
		builder.append("series=").append(this.series);
		builder.append(", tokenValue=").append(this.tokenValue);
		builder.append(", tokenDate=").append(this.tokenDate);
		builder.append(", ipAddress=").append(this.ipAddress);
		builder.append(", userAgent=").append(this.userAgent);
		builder.append(", user=").append(
				this.user == null ? "<aucun>" : this.user.toStringSimplifie());

		builder.append(" }");

		return builder.toString();
	}

}
