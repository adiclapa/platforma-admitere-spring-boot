package com.code.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "SesiuniAdmitere", schema = "dbo", catalog = "proiect-admitere")
public class SesiuniAdmitereEntity {
    @Basic
    @Column(name = "StartDate", nullable = false)
    private Date startDate;
    @Basic
    @Column(name = "EndDate", nullable = false)
    private Date endDate;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdAdmitere", nullable = false)
    private int idAdmitere;
    @Basic
    @Column(name = "DenumireAdmitere", nullable = true, length = 255)
    private String denumireAdmitere;
    @OneToMany(mappedBy = "sesiuniAdmitereByIdAdmitere")
    private Collection<CandidatiAdmitereEntity> candidatiAdmiteresByIdAdmitere;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

        SesiuniAdmitereEntity that = (SesiuniAdmitereEntity) o;

        if (idAdmitere != that.idAdmitere) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + idAdmitere;
        return result;
    }

    public String getDenumireAdmitere() {
        return denumireAdmitere;
    }

    public void setDenumireAdmitere(String denumireAdmitere) {
        this.denumireAdmitere = denumireAdmitere;
    }

    public Collection<CandidatiAdmitereEntity> getCandidatiAdmiteresByIdAdmitere() {
        return candidatiAdmiteresByIdAdmitere;
    }

    public void setCandidatiAdmiteresByIdAdmitere(Collection<CandidatiAdmitereEntity> candidatiAdmiteresByIdAdmitere) {
        this.candidatiAdmiteresByIdAdmitere = candidatiAdmiteresByIdAdmitere;
    }
}
