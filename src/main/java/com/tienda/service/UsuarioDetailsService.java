package com.tienda.service;

import org.springframework.security.core.userdetails.*;

//sirve solo para la parte de login
public interface UsuarioDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}