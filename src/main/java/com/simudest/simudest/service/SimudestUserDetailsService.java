package com.simudest.simudest.service;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.entity.Usuario;
import com.simudest.simudest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SimudestUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public SimudestUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userDb = usuarioRepository.findByEmail(username);
        //Optional<Usuario> userDb = usuarioRepository.findById(username);
        if (userDb.isPresent()) {
            Usuario usuario = userDb.get();

            Set<String> roles = new HashSet<>();
            roles.add(Constantes.ROL_OPOSITOR);
            if (usuario.getAdmin()){
                roles.add(Constantes.ROL_ADMIN);
            }

            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            //En username en vez de guardar el email guardamos la id del usuario, aunque se haya identificado con el email
            return org.springframework.security.core.userdetails.User
                    .withUsername(usuario.getId())
                    .roles(roles.toArray(new String[0]))
                    .password(usuario.getContra())
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
