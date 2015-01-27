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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A user.
 */
@Entity
@Table(name = "T_USER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Pattern(regexp = "^[a-z0-9]*$")
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String login;

	@JsonIgnore
	@NotNull
	@Size(min = 6, max = 100)
	@Column(length = 100)
	private String password;

	@Size(max = 50)
	@Column(name = "first_name", length = 50)
	private String firstName;

	@Size(max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;

	@Size(max = 50)
	@Column(name = "society", length = 50)
	private String society;

	@Email
	@Size(max = 100)
	@Column(length = 100, unique = true)
	private String email;

	@Column(nullable = false)
	private boolean activated = false;

	@Size(min = 2, max = 5)
	@Column(name = "lang_key", length = 5)
	private String langKey;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "T_USER_AUTHORITY", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Authority> authorities = new HashSet<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<PersistentToken> persistentTokens = new HashSet<>();

	@ManyToOne
	private Session session;

	public Long getId() {
		return this.id;
	}

	public void setId(Long pId) {
		this.id = pId;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String pLogin) {
		this.login = pLogin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String pPassword) {
		this.password = pPassword;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String pFirstName) {
		this.firstName = pFirstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String pLastName) {
		this.lastName = pLastName;
	}

	public String getSociety() {
		return this.society;
	}

	public void setSociety(String pSociety) {
		this.society = pSociety;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String pEmail) {
		this.email = pEmail;
	}

	public boolean getActivated() {
		return this.activated;
	}

	public void setActivated(boolean pActivated) {
		this.activated = pActivated;
	}

	public String getActivationKey() {
		return this.activationKey;
	}

	public void setActivationKey(String pActivationKey) {
		this.activationKey = pActivationKey;
	}

	public String getLangKey() {
		return this.langKey;
	}

	public void setLangKey(String pLangKey) {
		this.langKey = pLangKey;
	}

	public Set<Authority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set<Authority> pSetAuthority) {
		this.authorities = pSetAuthority;
	}

	public Set<PersistentToken> getPersistentTokens() {
		return this.persistentTokens;
	}

	public void setPersistentTokens(Set<PersistentToken> pSetPersistentToken) {
		this.persistentTokens = pSetPersistentToken;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session pSession) {
		this.session = pSession;
	}

	@Override
	public boolean equals(Object pObject) {
		if (this == pObject) {
			return true;
		}
		if (pObject == null || getClass() != pObject.getClass()) {
			return false;
		}

		User user = (User) pObject;

		if (!this.login.equals(user.login)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return this.login.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("User { ");
		builder.append("id=").append(this.id);
		builder.append(", login=").append(this.login);
		builder.append(", password=").append(this.password);
		builder.append(", firstName=").append(this.firstName);
		builder.append(", lastName=").append(this.lastName);
		builder.append(", society=").append(this.society);
		builder.append(", email=").append(this.email);
		builder.append(", langKey=").append(this.langKey);
		builder.append(", activationKey=").append(this.activationKey);
		builder.append(", session=").append(
				this.session == null ? "<aucune>" : this.session
						.toStringSimplifie());
		builder.append(" }");

		return builder.toString();
	}

	public String toStringSimplifie() {
		return this.login;
	}
}
