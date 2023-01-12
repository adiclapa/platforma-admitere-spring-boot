package com.code.payload.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {
    public Integer id;
    public Integer count = -1;
    public String nume;
    public String prenume;
    public String email;
    public String docVerificate;

    public UserDTO(Integer id, String nume, String prenume, String email, String docVerificate) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.docVerificate = docVerificate;
    }

    public UserDTO(Integer id, String nume, String prenume, String email) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return id.equals(((UserDTO) obj).id) && nume.equals(((UserDTO) obj).nume) && prenume.equals(((UserDTO) obj).prenume) && email.equals(((UserDTO) obj).email);
    }

}
