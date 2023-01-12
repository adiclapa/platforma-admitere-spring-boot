package com.code.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Table(name = "Documente", schema = "dbo", catalog = "proiect-admitere")
public class DocumenteEntity {
    @Basic
    @Column(name = "IdUser")
    private int idUser;
    @Basic
    @Column(name = "link")
    private String link;
    @Basic
    @Column(name = "tip")
    private String tip;
    @Basic
    @Column(name = "valid")
    private Integer valid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IndexDoc")
    private int indexDoc;
    @Basic
    @Column(name = "IdCandidat")
    private int idCandidat;
    @Basic
    @Column(name = "data")
    private Date data;

    public DocumenteEntity() {

    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public int getIndexDoc() {
        return indexDoc;
    }

    public void setIndexDoc(int indexDoc) {
        this.indexDoc = indexDoc;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumenteEntity that = (DocumenteEntity) o;

        if (idUser != that.idUser) return false;
        if (indexDoc != that.indexDoc) return false;
        if (idCandidat != that.idCandidat) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (tip != null ? !tip.equals(that.tip) : that.tip != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (tip != null ? tip.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + indexDoc;
        result = 31 * result + idCandidat;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
