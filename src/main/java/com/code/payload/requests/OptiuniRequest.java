package com.code.payload.requests;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class OptiuniRequest {
    private Integer ordine;
    private Integer specializare;
    private String denumire;

    public OptiuniRequest(Integer ordine, Integer specializare,String denumire) {
        this.ordine = ordine;
        this.specializare = specializare;
        this.denumire=denumire;

    }
    public OptiuniRequest(Integer specializare,String denumire){

        this.specializare=specializare;
        this.denumire=denumire;
    }
    public OptiuniRequest()
    {
      ordine=0;
      specializare=0;
      denumire="aads";
    }

    public Integer getOrdine() {
        return ordine;
    }

    public void setOrdine(Integer ordine) {
        this.ordine = ordine;
    }

    public Integer getSpecializare() {
        return specializare;
    }

    public void setSpecializare(Integer specializare) {
        this.specializare = specializare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
