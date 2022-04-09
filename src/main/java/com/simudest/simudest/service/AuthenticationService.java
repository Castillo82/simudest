package com.simudest.simudest.service;


import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.exception.UserAlreadyExistException;

public interface AuthenticationService {

    public void registrar(UsuarioDto usuarioDto) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email);
}
