package com.code.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
@Table(name = "CandidatiAdmitere")
public class CandidatiAdmitere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCandidatiAdmitere;
    private Integer IdCandidat;
    private Date IdAdmitere;
}
