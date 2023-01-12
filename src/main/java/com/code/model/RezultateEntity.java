package com.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Rezultate", schema = "dbo", catalog = "proiect-admitere")
public class RezultateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdRezultat")
    private int idRezultat;
    @Basic
    @Column(name = "IdCandidat")
    private int idCandidat;
    @Basic
    @Column(name = "Nota_1")
    private Double nota1;
    @Basic
    @Column(name = "Nota_2")
    private Double nota2;

    public int getIdRezultat() {
        return idRezultat;
    }

    public void setIdRezultat(int idRezultat) {
        this.idRezultat = idRezultat;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RezultateEntity that = (RezultateEntity) o;

        if (idRezultat != that.idRezultat) return false;
        if (idCandidat != that.idCandidat) return false;
        if (nota1 != null ? !nota1.equals(that.nota1) : that.nota1 != null) return false;
        if (nota2 != null ? !nota2.equals(that.nota2) : that.nota2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRezultat;
        result = 31 * result + idCandidat;
        result = 31 * result + (nota1 != null ? nota1.hashCode() : 0);
        result = 31 * result + (nota2 != null ? nota2.hashCode() : 0);
        return result;
    }
}
