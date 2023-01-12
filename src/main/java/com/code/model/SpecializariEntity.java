package com.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Specializari", schema = "dbo", catalog = "proiect-admitere")
public class SpecializariEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Nume")
    private String nume;
    @Basic
    @Column(name = "Locuri")
    private int locuri;
    @Basic
    @Column(name = "IdFacultate")
    private int idFacultate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getLocuri() {
        return locuri;
    }

    public void setLocuri(int locuri) {
        this.locuri = locuri;
    }

    public int getIdFacultate() {
        return idFacultate;
    }

    public void setIdFacultate(int idFacultate) {
        this.idFacultate = idFacultate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecializariEntity that = (SpecializariEntity) o;

        if (id != that.id) return false;
        if (locuri != that.locuri) return false;
        if (idFacultate != that.idFacultate) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + locuri;
        result = 31 * result + idFacultate;
        return result;
    }
}
