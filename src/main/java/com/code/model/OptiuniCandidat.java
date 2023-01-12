package com.code.model;
import jakarta.persistence.*;
@Entity
@Table(name="OptiuniCandidat")
public class OptiuniCandidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int IdCandidat;
    private int OrdineOptiuni;
    private int IdSpecializare;

    public int getOrdineOptiuni() {
        return OrdineOptiuni;
    }

    public int getIdCandidat() {
        return IdCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        IdCandidat = idCandidat;
    }

    public void setOrdineOptiuni(int ordineOptiuni) {
        OrdineOptiuni = ordineOptiuni;
    }

    public int getIdSpecializare() {
        return IdSpecializare;
    }

    public void setIdSpecializare(int idSpecializare) {
        IdSpecializare = idSpecializare;
    }

//    public OptiuniCandidat() {
//    }

    public OptiuniCandidat(int idCandidat, int ordineOptiuni, int idSpecializare) {
        IdCandidat = idCandidat;
        OrdineOptiuni = ordineOptiuni;
        IdSpecializare = idSpecializare;
    }

    @Override
    public String toString() {
        return "OptiuniCandidati{" +
                "Id=" + IdCandidat +
                ", OrdineOptiuni=" + OrdineOptiuni +
                ", IdSpecializare=" + IdSpecializare +
                '}';
    }
}
