package com.brunohonor.usuario.business;

import com.brunohonor.usuario.business.converter.UsuarioConverter;
import com.brunohonor.usuario.business.dto.UsuarioDTO;
import com.brunohonor.usuario.infrastructure.entity.Usuario;
import com.brunohonor.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioConverter.DTOparaUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);


        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
