package com.code.payload.requests;

public class SpecializariRequest {
    private String Nume;
    private int Locuri;
    private String Facultate;
    private int Index_spec;

    public int getIndex_spec() {
        return Index_spec;
    }

    public void setIndex_spec(int index_spec) {
        Index_spec = index_spec;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public int getLocuri() {
        return Locuri;
    }

    public void setLocuri(int locuri) {
        Locuri = locuri;
    }

    public String getFacultate() {
        return Facultate;
    }

    public void setFacultate(String facultate) {
        Facultate = facultate;
    }

    public SpecializariRequest(String nume, int locuri, String facultate, int index_spec) {
        Nume = nume;
        Locuri = locuri;
        Facultate = facultate;
        Index_spec = index_spec;
    }
}
