package com.simudest.simudest.service;

import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.entity.Usuario;
import com.simudest.simudest.exception.UserAlreadyExistException;
import com.simudest.simudest.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void registrar(UsuarioDto usuarioDto) throws UserAlreadyExistException {
        if(checkIfUserExist(usuarioDto.getEmail())){
            throw new UserAlreadyExistException("Ya existe un usuario con este email");
        }
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        encodePassword(usuario);
        usuario.setAdmin(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    private void encodePassword(Usuario usuario){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String newPass = passwordEncoder.encode(usuario.getContra());
        usuario.setContra(newPass);
    }
}
