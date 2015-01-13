package fr.softeam.togafquizz.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "duree_en_minutes")
    private Integer dureeEnMinutes;

    @Column(name = "type")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDureeEnMinutes(Integer dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Quizz quizz = (Quizz) o;

        if (id != null ? !id.equals(quizz.id) : quizz.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", libelle='" + libelle + "'" +
                ", numero='" + numero + "'" +
                ", dureeEnMinutes='" + dureeEnMinutes + "'" +
                ", type='" + type + "'" +
                '}';
    }
}
