package com.salesianostriana.dam.virtualgaming.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

@Entity
class Usuario(

        @get:NotBlank(message = "{usuario.nombrecompleto.blank}")
        var nombreCompleto:String,

        @Column(nullable = false, unique = true)
        @get:NotBlank(message="{usuario.username.blank}")
        private var username:String,

        @get:NotBlank(message="{usuario.password.blank}")
        private var password:String,

        @Column(nullable = false, unique = true)
        @get:NotBlank(message="{usuario.email.blank}")
        @get:Email(message="{usuario.email.email}")
        var email:String,

        var fechaNacimiento:LocalDate,

        @ElementCollection(fetch = FetchType.EAGER)
        val roles: MutableSet<String> = HashSet(),

        @ManyToMany(mappedBy = "likes")
        var deseados:MutableList<Videojuego> = mutableListOf(),

        @OneToMany(mappedBy = "usuario")
        var ordenadores:MutableList<Ordenador> = mutableListOf(),

        private val nonExpired: Boolean = true,

        private val nonLocked: Boolean = true,

        private val enabled: Boolean = true,

        private val credentialsNonExpired : Boolean = true,

        @Id @GeneratedValue
        var id:UUID?= null

):UserDetails{

    constructor(nombreCompleto: String,username: String,password: String,
                email: String, fechaNacimiento:LocalDate, rol:String):this(
                nombreCompleto,username, password,email,fechaNacimiento,
                mutableSetOf(rol),mutableListOf<Videojuego>(), mutableListOf<Ordenador>(),
            true,true,true,true

    )

    fun addDeseados(videojuego:Videojuego){
        this.deseados.add(videojuego)
        videojuego.likes!!.add(this)
    }

    fun addOrdenador(ordenador:Ordenador){
        this.ordenadores.add(ordenador)
        ordenador.usuario = this
    }

    fun removeDeseados(videojuego:Videojuego){
        this.deseados.remove(videojuego)
        videojuego.likes!!.remove(this)
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()

    override fun getPassword()= password

    override fun getUsername()= username

    override fun isAccountNonExpired()= nonExpired

    override fun isAccountNonLocked()= nonLocked

    override fun isCredentialsNonExpired()= credentialsNonExpired

    override fun isEnabled() = enabled

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Usuario
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}