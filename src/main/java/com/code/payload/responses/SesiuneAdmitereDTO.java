package com.code.payload.responses;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SesiuneAdmitereDTO {
    public Date start;
    public Date end;
    public Integer noCandidati;

    public SesiuneAdmitereDTO(Date start, Date end, Integer noCandidati) {
        this.start = start;
        this.end = end;
        this.noCandidati = noCandidati;
    }
}
