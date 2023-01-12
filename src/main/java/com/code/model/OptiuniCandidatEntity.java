package com.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "OptiuniCandidat", schema = "dbo", catalog = "proiect-admitere")
public class OptiuniCandidatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "IdCandidat")
    private int idCandidat;
    @Basic
    @Column(name = "IdSpecializare")
    private int idSpecializare;
    @Basic
    @Column(name = "OrdineOptiuni")
    private int ordineOptiuni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public int getIdSpecializare() {
        return idSpecializare;
    }

    public void setIdSpecializare(int idSpecializare) {
        this.idSpecializare = idSpecializare;
    }

    public int getOrdineOptiuni() {
        return ordineOptiuni;
    }

    public void setOrdineOptiuni(int ordineOptiuni) {
        this.ordineOptiuni = ordineOptiuni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptiuniCandidatEntity that = (OptiuniCandidatEntity) o;

        if (id != that.id) return false;
        if (idCandidat != that.idCandidat) return false;
        if (idSpecializare != that.idSpecializare) return false;
        if (ordineOptiuni != that.ordineOptiuni) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idCandidat;
        result = 31 * result + idSpecializare;
        result = 31 * result + ordineOptiuni;
        return result;
    }
}
