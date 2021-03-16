package com.salesianostriana.dam.virtualgaming.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

@Entity
class Usuario(

        @Column(nullable = false, unique = true)
        @get:NotBlank(message = "{usuario.nombrecompleto.blank}")
        private var nombreCompleto:String,

        @Column(nullable = false, unique = true)
        @get:NotBlank(message="{usuario.username.blank}")
        private var username:String,

        @get:NotBlank(message="{usuario.password.blank}")
        private var password:String,

        @Column(nullable = false, unique = true)
        @get:NotBlank(message="{usuario.email.blank}")
        @get:Email(message="{usuario.email.email}")
        private var email:String,

        @get:Past(message="{usuario.fechanacimiento.date}")
        private var fechaNacimiento:Date,

        @ManyToMany(mappedBy = "likes")
        private var deseados:MutableList<Videojuego> = mutableListOf(),

        @OneToMany(mappedBy = "usuario")
        private var ordenadores:MutableList<Ordenador> = mutableListOf(),

        @Id @GeneratedValue
        private var id:UUID?= null

):UserDetails{

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

}