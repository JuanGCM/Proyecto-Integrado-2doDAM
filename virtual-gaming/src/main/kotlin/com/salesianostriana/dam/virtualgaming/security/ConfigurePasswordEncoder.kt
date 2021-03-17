package com.salesianostriana.dam.virtualgaming.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ConfigurePasswordEncoder() {

    @Bean
    fun passwordEncoder():PasswordEncoder = BCryptPasswordEncoder()
}