package com.salesianostriana.dam.virtualgaming.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailsServiceImpl(
        var userService: UsuarioServicio
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
            userService.findByUsername(username).orElseThrow {
                UsernameNotFoundException("Usuario $username no encontrado")
            }

}