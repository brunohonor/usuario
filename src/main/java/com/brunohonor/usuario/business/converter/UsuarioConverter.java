package com.brunohonor.usuario.business.converter;

import com.brunohonor.usuario.business.dto.EnderecoDTO;
import com.brunohonor.usuario.business.dto.TelefoneDTO;
import com.brunohonor.usuario.business.dto.UsuarioDTO;
import com.brunohonor.usuario.infrastructure.entity.Endereco;
import com.brunohonor.usuario.infrastructure.entity.Telefone;
import com.brunohonor.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public  UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();
    }

    public  List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(UsuarioConverter::paraEnderecoDTO).toList();
    }

    public static EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .bairro(enderecoDTO.getBairro())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public  List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTO){
        return telefoneDTO.stream().map(UsuarioConverter::paraTelefoneDTO).toList();

    }

    public static TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public  Usuario DTOparaUsuario(UsuarioDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .email(dto.getEmail())
                .enderecos(dto.getEnderecos().stream().map(e -> new Endereco(e)).toList())
                .telefones(dto.getTelefones().stream().map(e -> new Telefone(e)).toList())
                .build();

    }


}



