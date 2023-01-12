package com.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Facultati", schema = "dbo", catalog = "proiect-admitere")
public class FacultatiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Denumire")
    private String denumire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacultatiEntity that = (FacultatiEntity) o;

        if (id != that.id) return false;
        if (denumire != null ? !denumire.equals(that.denumire) : that.denumire != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (denumire != null ? denumire.hashCode() : 0);
        return result;
    }
}
