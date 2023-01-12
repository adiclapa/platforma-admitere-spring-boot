package com.code.payload.responses;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DocumenteCandidat {
    public Integer candidatId;
    public Integer id;
    public Integer count = -1;
    public String tip;
    public String link;
    public Integer valid;
    public Date data;

    public DocumenteCandidat(Integer candidatId, Integer id, String tip, String link, Integer valid, Date data) {
        this.candidatId = candidatId;
        this.id = id;
        this.tip = tip;
        this.link = link;
        this.valid = valid;
        this.data = data;
    }

    public DocumenteCandidat(Integer id, String tip, String link, Integer valid, Date data) {
        this.id = id;
        this.tip = tip;
        this.link = link;
        this.valid = valid;
        this.data = data;
    }

    public DocumenteCandidat(Integer id, String tip, Integer valid, Date data) {
        this.id = id;
        this.tip = tip;
        this.valid = valid;
        this.data = data;
    }
}
