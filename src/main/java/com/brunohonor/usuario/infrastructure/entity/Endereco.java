package com.brunohonor.usuario.infrastructure.entity;

import com.brunohonor.usuario.business.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "endereco")
@Builder
public class Endereco {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cep" , length = 9)
    private String cep;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "cidade" , length = 20)
    private String cidade;
    @Column(name = "estado", length = 2)
    private String estado;

    public Endereco(EnderecoDTO endDTO) {
    }

//    @ManyToOne
//    @JoinColumn(name = "idusuario")
//    private Usuario usuario;




}
