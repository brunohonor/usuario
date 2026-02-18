package com.brunohonor.usuario.infrastructure.entity;

import com.brunohonor.usuario.business.dto.EnderecoDTO;
import com.brunohonor.usuario.business.dto.TelefoneDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "telefone")
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero" , length =  11)
    private String numero;
    @Column(name = "ddd" , length = 3)
    private String ddd;

    public Telefone(TelefoneDTO telefoneDTO) {
        this.numero = telefoneDTO.getNumero();
    }
}
