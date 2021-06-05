package com.salesianostriana.dam.virtualgaming

import com.salesianostriana.dam.virtualgaming.models.*
import com.salesianostriana.dam.virtualgaming.repositories.*
import com.salesianostriana.dam.virtualgaming.services.VideojuegoServicio
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class VirtualGamingApplication{

	@Bean
	fun init(
			juegoRepo: VideojuegoRepository,
			ramRepo:MemoriaRAMRepository,
			proceRepo:ProcesadorRepository,
			graficaRepo:TarjetaGraficaRepository,
			generoRepo:GeneroJuegoRepository
	) = CommandLineRunner {

		var memorias:MutableList<MemoriaRAM> = mutableListOf()
		var procesadores:MutableList<Procesador> = mutableListOf()
		var graficas:MutableList<TarjetaGrafica> = mutableListOf()
		var generos:MutableList<GeneroJuego> = mutableListOf()
		var juegos:MutableList<Videojuego> = mutableListOf()

		//Procesadores
		procesadores.addAll(  arrayListOf(
				Procesador("Intel core 2 duo", 1, mutableListOf(),mutableListOf(),54564654),
				Procesador("Intel core i3", 2, mutableListOf(),mutableListOf(),54533654),
				Procesador("AMD Ryzen 3", 3, mutableListOf(),mutableListOf(),54564694),
				Procesador("Intel core i5", 4, mutableListOf(),mutableListOf(),54588654),
				Procesador("AMD Ryzen 5", 5, mutableListOf(),mutableListOf(),54564774),
				Procesador("Intel core i7", 6, mutableListOf(),mutableListOf(),545666154),
				Procesador("AMD Ryzen 7", 7, mutableListOf(),mutableListOf(),54564603),
				Procesador("Intel core i9", 8, mutableListOf(),mutableListOf(),54564600),
				Procesador("AMD Ryzen 9", 9, mutableListOf(),mutableListOf(),545647054)))

		//Tarjetas Grafica
		graficas.addAll( arrayListOf(
				TarjetaGrafica("NVIDIA GTX 750 ti", 1,mutableListOf(),mutableListOf(),54545485),
				TarjetaGrafica("NVIDIA GTX 780", 2,mutableListOf(),mutableListOf(),54545498),
				TarjetaGrafica("NVIDIA GTX 1050 ti", 3,mutableListOf(),mutableListOf(),54545464),
				TarjetaGrafica("NVIDIA GTX 1080", 4,mutableListOf(),mutableListOf(),545454114),
				TarjetaGrafica("NVIDIA RTX 2060", 5,mutableListOf(),mutableListOf(),545454778),
				TarjetaGrafica("NVIDIA RTX 2070", 6,mutableListOf(),mutableListOf(),545454880),
				TarjetaGrafica("NVIDIA TITAN V", 7,mutableListOf(),mutableListOf(),545454101),
				TarjetaGrafica("NVIDIA TITAN X", 8,mutableListOf(),mutableListOf(),545454054),
				TarjetaGrafica("NVIDIA RTX 3260 ti", 9,mutableListOf(),mutableListOf(),545454655),
				TarjetaGrafica("NVIDIA RTX 3070", 10,mutableListOf(),mutableListOf(),5454548044),
				TarjetaGrafica("NVIDIA RTX 3080", 11,mutableListOf(),mutableListOf(),5454547002)))

		//Memorias RAM
		memorias.addAll( arrayListOf(
				MemoriaRAM("6GB DDR2",1, mutableListOf(),mutableListOf(),45645664),
				MemoriaRAM("6GB DDR3",2, mutableListOf(),mutableListOf(),45648964),
				MemoriaRAM("8GB DDR3",3, mutableListOf(),mutableListOf(),45642664),
				MemoriaRAM("8GB DDR4",4, mutableListOf(),mutableListOf(),45640264),
				MemoriaRAM("12GB DDR3",5, mutableListOf(),mutableListOf(),45640664),
				MemoriaRAM("12GB DDR4",6, mutableListOf(),mutableListOf(),456057664),
				MemoriaRAM("16GB DDR3",7, mutableListOf(),mutableListOf(),45640464),
				MemoriaRAM("16GB DDR4",8, mutableListOf(),mutableListOf(),456453764),
				MemoriaRAM("24GB DDR4",9, mutableListOf(),mutableListOf(),45645774),
				MemoriaRAM("24GB DDR5",10, mutableListOf(),mutableListOf(),45641164)))

		//Generos de Juegos
		generos.addAll( arrayListOf(
				GeneroJuego("Accion",mutableListOf(),456415615),
				GeneroJuego("Estrategia",mutableListOf(),4561115615),
				GeneroJuego("Horror",mutableListOf(),45100415615),
				GeneroJuego("RPG",mutableListOf(),4564158505),
				GeneroJuego("Shooter",mutableListOf(),4566555615),
				GeneroJuego("Open World",mutableListOf(),4563320615),
				GeneroJuego("Aventura",mutableListOf(),454810015),
				GeneroJuego("Survival",mutableListOf(),4537005615)))

		proceRepo.saveAll(procesadores)
		graficaRepo.saveAll(graficas)
		ramRepo.saveAll(memorias)
		generoRepo.saveAll(generos)

		//Videojuegos
		juegos.addAll( arrayListOf(
				Videojuego("Sea of Thieves", "Eres libre y sin ataduras para afrontar la vida y a otros jugadores como plazcas. " +
						"Te encontrarás con otras tripulaciones mientras navegas en solitario o en grupo",25.40,"Steam",mutableListOf(),mutableListOf(),"https://i.imgur.com/haAmxwo.jpg",mutableListOf(),null,null,null,5151516156),

				Videojuego("Red Dead Redemption 2", "Es un juego de acción y aventuras de mundo abierto en el " +
						"que el jugador puede vagar libremente.",32.89,"Rockstar",mutableListOf(),mutableListOf(),"https://i.imgur.com/fNIdzOW.jpg",mutableListOf(),null,null,null,5151696156),

				Videojuego("Grand Theft Auto V", "Se trata de una aventura de acción de mundo abierto con multitud " +
						"de misiones,además cuenta con modo Online.",9.89,"Rockstar",mutableListOf(),mutableListOf(),"https://i.imgur.com/GAlL2EO.jpg",mutableListOf(),null,null,null,515192556156),

				Videojuego("Dead by Daylight", "Tienes poco tiempo para escapar de un asesino" +
						" que anda buscandote, intenta escapar junto de tus amigos o morir en el intento",5.22,"Steam",mutableListOf(),mutableListOf(),"https://i.imgur.com/0NVyKtx.jpg",mutableListOf(),null,null,null,51515105556),

				Videojuego("Horizon Zero Dawn", "Es un RPG de acción en el que controlas a Aloy, una " +
						"superviviente humana en un paisaje distópico del siglo 31",24.73,"Steam",mutableListOf(),mutableListOf(),"https://i.imgur.com/JrYeNCj.jpg",mutableListOf(),null,null,null,515151599656),

				Videojuego("Overwatch", "Es un shooter multijugador en " +
						"primera persona basado en equipos. ",12.98,"Battle.net",mutableListOf(),mutableListOf(),"https://i.imgur.com/5CBnvrI.jpg",mutableListOf(),null,null,null,51515163006),

				Videojuego("World of Warcraft", "Es un juego mundo abierto donde podras hacer " +
						"muchas misiones, hacer amigos y adentrarte en la historia",69.99,"Battle.net",mutableListOf(),mutableListOf(),"https://i.imgur.com/Wx6PJ5J.jpg",mutableListOf(),null,null,null,51515161778),

				Videojuego("Borderlands 3", "Es un shooter en primera persona basado en la " +
						"recolección de botín. ",14.99,"Epic Games",mutableListOf(),mutableListOf(),"https://i.imgur.com/N9bwDjo.jpg",mutableListOf(),null,null,null,51515161996),

				Videojuego("Far Cry 5", "Es un alocado y divertido shooter de acción y aventuras en " +
						"primera persona en el que debes enfrentarte a oponentes de los más raros y extravagantes.",11.89,"Uplay",mutableListOf(),mutableListOf(),"https://i.imgur.com/FtpKQv0.jpg",mutableListOf(),null,null,null,51515161112),

				Videojuego("The Division 2", "La misión de la División consistía en encontrar el" +
						" origen de la enfermedad y hacer lo necesario para mantener y/o restablecer el orden público.",12.86,"Uplay",mutableListOf(),mutableListOf(),"https://i.imgur.com/3AIHzUv.jpg",mutableListOf(),null,null,null,51515161300)))

		juegoRepo.saveAll(juegos)

		var pro1 = proceRepo.findByCode(2)
		pro1.videojuego = mutableListOf()
		var pro2 = proceRepo.findByCode(3)
		pro2.videojuego = mutableListOf()
		var pro3 = proceRepo.findByCode(4)
		pro3.videojuego = mutableListOf()
		var pro4 = proceRepo.findByCode(5)
		pro4.videojuego = mutableListOf()
		var pro5 = proceRepo.findByCode(6)
		pro5.videojuego = mutableListOf()
		var pro6 = proceRepo.findByCode(7)
		pro6.videojuego = mutableListOf()

		var gra0 = graficaRepo.findByCode(1)
		gra0.videojuego = mutableListOf()
		var gra1 = graficaRepo.findByCode(2)
		gra1.videojuego = mutableListOf()
		var gra2 = graficaRepo.findByCode(3)
		gra2.videojuego = mutableListOf()
		var gra3 = graficaRepo.findByCode(4)
		gra3.videojuego = mutableListOf()

		var mem1 = ramRepo.findByCode(2)
		mem1.videojuego = mutableListOf()
		var mem2 = ramRepo.findByCode(3)
		mem2.videojuego = mutableListOf()
		var mem3 = ramRepo.findByCode(4)
		mem3.videojuego = mutableListOf()
		var mem4 = ramRepo.findByCode(5)
		mem4.videojuego = mutableListOf()
		var mem5 = ramRepo.findByCode(6)
		mem5.videojuego = mutableListOf()
		var mem6 = ramRepo.findByCode(7)
		mem6.videojuego = mutableListOf()
		var mem7 = ramRepo.findByCode(8)
		mem7.videojuego = mutableListOf()

		var gen0 = generoRepo.findByTitulo("Accion")
		gen0.videojuegos = mutableListOf()
		var gen1 = generoRepo.findByTitulo("Estrategia")
		gen1.videojuegos = mutableListOf()
		var gen2 = generoRepo.findByTitulo("Horror")
		gen2.videojuegos = mutableListOf()
		var gen3 = generoRepo.findByTitulo("RPG")
		gen3.videojuegos = mutableListOf()
		var gen4 = generoRepo.findByTitulo("Shooter")
		gen4.videojuegos = mutableListOf()
		var gen5 = generoRepo.findByTitulo("Open World")
		gen5.videojuegos = mutableListOf()
		var gen6 = generoRepo.findByTitulo("Aventura")
		gen6.videojuegos = mutableListOf()

		var juego1 = juegoRepo.findByNombre("Sea of Thieves")
		juego1.generoJuegos = mutableListOf()
		juego1.minProcesador = null
		juego1.minTarjetaGrafica = null
		juego1.minMemoriaRAM = null
		juego1.addGeneroJuegos(gen0)
		juego1.addGeneroJuegos(gen5)
		juego1.addGeneroJuegos(gen6)
		juego1.addProcesador(pro3)
		juego1.addGrafica(gra1)
		juego1.addRAM(mem2)


		var juego2 = juegoRepo.findByNombre("Red Dead Redemption 2")
		juego2.generoJuegos = mutableListOf()
		juego2.minProcesador = null
		juego2.minTarjetaGrafica = null
		juego2.minMemoriaRAM = null
		juego2.addGeneroJuegos(gen0)
		juego2.addGeneroJuegos(gen5)
		juego2.addGeneroJuegos(gen6)
		juego2.addProcesador(pro5)
		juego2.addGrafica(gra2)
		juego2.addRAM(mem4)


		var juego3 = juegoRepo.findByNombre("Grand Theft Auto V")
		juego3.generoJuegos = mutableListOf()
		juego3.minProcesador = null
		juego3.minTarjetaGrafica = null
		juego3.minMemoriaRAM = null
		juego3.addGeneroJuegos(gen0)
		juego3.addGeneroJuegos(gen5)
		juego3.addGeneroJuegos(gen6)
		juego3.addProcesador(pro3)
		juego3.addGrafica(gra0)
		juego3.addRAM(mem2)


		var juego4 = juegoRepo.findByNombre("Dead by Daylight")
		juego4.generoJuegos = mutableListOf()
		juego4.minProcesador = null
		juego4.minTarjetaGrafica = null
		juego4.minMemoriaRAM = null
		juego4.addGeneroJuegos(gen0)
		juego4.addGeneroJuegos(gen1)
		juego4.addGeneroJuegos(gen2)
		juego4.addProcesador(pro1)
		juego4.addGrafica(gra0)
		juego4.addRAM(mem3)


		var juego5 = juegoRepo.findByNombre("Horizon Zero Dawn")
		juego5.generoJuegos = mutableListOf()
		juego5.minProcesador = null
		juego5.minTarjetaGrafica = null
		juego5.minMemoriaRAM = null
		juego5.addGeneroJuegos(gen3)
		juego5.addGeneroJuegos(gen5)
		juego5.addProcesador(pro4)
		juego5.addGrafica(gra2)
		juego5.addRAM(mem6)


		var juego6 = juegoRepo.findByNombre("Overwatch")
		juego6.generoJuegos = mutableListOf()
		juego6.minProcesador = null
		juego6.minTarjetaGrafica = null
		juego6.minMemoriaRAM = null
		juego6.addGeneroJuegos(gen0)
		juego6.addGeneroJuegos(gen4)
		juego6.addProcesador(pro4)
		juego6.addGrafica(gra0)
		juego6.addRAM(mem1)


		var juego7 = juegoRepo.findByNombre("World of Warcraft")
		juego7.generoJuegos = mutableListOf()
		juego7.minProcesador = null
		juego7.minTarjetaGrafica = null
		juego7.minMemoriaRAM = null
		juego7.addGeneroJuegos(gen0)
		juego7.addGeneroJuegos(gen3)
		juego7.addGeneroJuegos(gen5)
		juego7.addProcesador(pro5)
		juego7.addGrafica(gra1)
		juego7.addRAM(mem3)


		var juego8 = juegoRepo.findByNombre("Borderlands 3")
		juego8.generoJuegos = mutableListOf()
		juego8.minProcesador = null
		juego8.minTarjetaGrafica = null
		juego8.minMemoriaRAM = null
		juego8.addGeneroJuegos(gen0)
		juego8.addGeneroJuegos(gen3)
		juego8.addProcesador(pro6)
		juego8.addGrafica(gra3)
		juego8.addRAM(mem5)


		var juego9 = juegoRepo.findByNombre("Far Cry 5")
		juego9.generoJuegos = mutableListOf()
		juego9.minProcesador = null
		juego9.minTarjetaGrafica = null
		juego9.minMemoriaRAM = null
		juego9.addGeneroJuegos(gen0)
		juego9.addGeneroJuegos(gen4)
		juego9.addGeneroJuegos(gen6)
		juego9.addProcesador(pro4)
		juego9.addGrafica(gra3)
		juego9.addRAM(mem7)


		var juego10 = juegoRepo.findByNombre("The Division 2")
		juego10.generoJuegos = mutableListOf()
		juego10.minProcesador = null
		juego10.minTarjetaGrafica = null
		juego10.minMemoriaRAM = null
		juego10.addGeneroJuegos(gen3)
		juego10.addGeneroJuegos(gen4)
		juego10.addGeneroJuegos(gen5)
		juego10.addProcesador(pro2)
		juego10.addGrafica(gra1)
		juego10.addRAM(mem2)


		juegoRepo.saveAll(mutableListOf(juego1,juego2,juego3,juego4,juego5,juego6,juego7,juego8,juego9,juego10))
		proceRepo.saveAll(mutableListOf(pro1,pro2,pro3,pro4,pro5,pro6))
		graficaRepo.saveAll(mutableListOf(gra0,gra1,gra2,gra3))
		ramRepo.saveAll(mutableListOf(mem1,mem2,mem3,mem4,mem5,mem6,mem7))
		generoRepo.saveAll(mutableListOf(gen0,gen1,gen2,gen3,gen4,gen5,gen6))
	}
}

fun main(args: Array<String>) {
	runApplication<VirtualGamingApplication>(*args)
}
