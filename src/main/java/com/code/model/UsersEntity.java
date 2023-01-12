package com.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users", schema = "dbo", catalog = "proiect-admitere")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdUser")
    private int idUser;
    @Basic
    @Column(name = "IdRol")
    private Integer idRol;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Nume")
    private String nume;
    @Basic
    @Column(name = "Prenume")
    private String prenume;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idUser != that.idUser) return false;
        if (idRol != null ? !idRol.equals(that.idRol) : that.idRol != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (prenume != null ? !prenume.equals(that.prenume) : that.prenume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (idRol != null ? idRol.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        return result;
    }
}
