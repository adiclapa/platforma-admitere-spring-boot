package com.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CandidatiAdmitere", schema = "dbo", catalog = "proiect-admitere")
public class CandidatiAdmitereEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdCandidatiAdmitere", nullable = false)
    private int idCandidatiAdmitere;
    @Basic
    @Column(name = "IdCandidat", nullable = false)
    private int idCandidat;
    @Basic
    @Column(name = "IdAdmitere", nullable = false)
    private int idAdmitere;
    @ManyToOne
    @JoinColumn(name = "IdAdmitere", referencedColumnName = "IdAdmitere", nullable = false, insertable = false, updatable=false)
    private SesiuniAdmitereEntity sesiuniAdmitereByIdAdmitere;

    public int getIdCandidatiAdmitere() {
        return idCandidatiAdmitere;
    }

    public void setIdCandidatiAdmitere(int idCandidatiAdmitere) {
        this.idCandidatiAdmitere = idCandidatiAdmitere;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public int getIdAdmitere() {
        return idAdmitere;
    }

    public void setIdAdmitere(int idAdmitere) {
        this.idAdmitere = idAdmitere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidatiAdmitereEntity that = (CandidatiAdmitereEntity) o;

        if (idCandidatiAdmitere != that.idCandidatiAdmitere) return false;
        if (idCandidat != that.idCandidat) return false;
        if (idAdmitere != that.idAdmitere) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCandidatiAdmitere;
        result = 31 * result + idCandidat;
        result = 31 * result + idAdmitere;
        return result;
    }

    public SesiuniAdmitereEntity getSesiuniAdmitereByIdAdmitere() {
        return sesiuniAdmitereByIdAdmitere;
    }

    public void setSesiuniAdmitereByIdAdmitere(SesiuniAdmitereEntity sesiuniAdmitereByIdAdmitere) {
        this.sesiuniAdmitereByIdAdmitere = sesiuniAdmitereByIdAdmitere;
    }
}
