package com.brunohonor.usuario.business;

import com.brunohonor.usuario.business.converter.UsuarioConverter;
import com.brunohonor.usuario.business.dto.UsuarioDTO;
import com.brunohonor.usuario.infrastructure.entity.Usuario;
import com.brunohonor.usuario.infrastructure.repository.UsuarioRepository;
import com.brunohonor.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){

        emailExiste(usuarioDTO.getEmail());

        Usuario usuario = usuarioConverter.DTOparaUsuario(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario = usuarioRepository.save(usuario);


        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public void emailExiste (String email){
        try{
            boolean existe = verificaEmailExistente (email);
            if(existe){
                throw new com.bruno.aprendendo.spring.infrastructure.exceptions.ConflictException("Email já cadastrado" + email);
            }
        }catch (com.bruno.aprendendo.spring.infrastructure.exceptions.ConflictException e){
            throw new com.bruno.aprendendo.spring.infrastructure.exceptions.ConflictException("Email já cadastrado" , e.getCause());
        }
    }
    public boolean verificaEmailExistente (String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail (String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new com.bruno.aprendendo.spring.infrastructure.exceptions.ResourceNotFoundException("Email não encontrado" + email));
    }
    public void deletausuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);

    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        //buscamos o email do usuário através do token (tirar a obrigatoriedade do email)
        String email= jwtUtil.extrairEmailToken(token.substring(7));
        //Criptografia de senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        //Busca os dados do usuário no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email) .orElseThrow(() ->
                new com.bruno.aprendendo.spring.infrastructure.exceptions.ResourceNotFoundException("Email não localizado"));

        //Mesclou os dados que recebemos na requisição DTO com dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        //Salvou os dados do usuário convertido e depois pegou o retorno e converteu para usuarioDTO

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }
}
