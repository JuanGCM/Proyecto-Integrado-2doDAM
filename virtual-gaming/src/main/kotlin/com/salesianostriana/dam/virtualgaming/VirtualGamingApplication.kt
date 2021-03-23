package com.salesianostriana.dam.virtualgaming

import com.salesianostriana.dam.virtualgaming.models.*
import com.salesianostriana.dam.virtualgaming.repositories.*
import com.salesianostriana.dam.virtualgaming.services.VideojuegoServicio
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

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

		/*



		var memorias:MutableList<MemoriaRAM> = mutableListOf()
		var procesadores:MutableList<Procesador> = mutableListOf()
		var graficas:MutableList<TarjetaGrafica> = mutableListOf()
		var generos:MutableList<GeneroJuego> = mutableListOf()
		var juegos:MutableList<Videojuego> = mutableListOf()

		//Procesadores
		procesadores.addAll(  arrayListOf(
				Procesador("Intel core 2 duo", 1, mutableListOf(),mutableListOf(),54564654),
				Procesador("Intel core i3", 2, mutableListOf(),mutableListOf(juegos[3]),54533654),
				Procesador("AMD Ryzen 3", 3, mutableListOf(),mutableListOf(juegos[9]),54564694),
				Procesador("Intel core i5", 4, mutableListOf(),mutableListOf(juegos[0],juegos[2]),54588654),
				Procesador("AMD Ryzen 5", 5, mutableListOf(),mutableListOf(juegos[4],juegos[5],juegos[8]),54564774),
				Procesador("Intel core i7", 6, mutableListOf(),mutableListOf(juegos[1],juegos[6]),545666154),
				Procesador("AMD Ryzen 7", 7, mutableListOf(),mutableListOf(juegos[7]),54564603),
				Procesador("Intel core i9", 8, mutableListOf(),mutableListOf(),54564600),
				Procesador("AMD Ryzen 9", 9, mutableListOf(),mutableListOf(),545647054)))


		//Tarjetas Grafica
		graficas.addAll( arrayListOf(
				TarjetaGrafica("NVIDIA GTX 750 ti", 1,mutableListOf(),mutableListOf(juegos[2],juegos[3],juegos[5]),54545485),
				TarjetaGrafica("NVIDIA GTX 780", 2,mutableListOf(),mutableListOf(juegos[0],juegos[6],juegos[9]),54545498),
				TarjetaGrafica("NVIDIA GTX 1050 ti", 3,mutableListOf(),mutableListOf(juegos[1],juegos[4]),54545464),
				TarjetaGrafica("NVIDIA GTX 1080", 4,mutableListOf(),mutableListOf(juegos[7], juegos[8]),545454114),
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
				MemoriaRAM("6GB DDR3",2, mutableListOf(juegos[5]),mutableListOf(),45648964),
				MemoriaRAM("8GB DDR3",3, mutableListOf(juegos[0],juegos[2],juegos[9]),mutableListOf(),45642664),
				MemoriaRAM("8GB DDR4",4, mutableListOf(juegos[3],juegos[6]),mutableListOf(),45640264),
				MemoriaRAM("12GB DDR3",5, mutableListOf(juegos[1]),mutableListOf(),45640664),
				MemoriaRAM("12GB DDR4",6, mutableListOf(juegos[7]),mutableListOf(),456057664),
				MemoriaRAM("16GB DDR3",7, mutableListOf(juegos[4]),mutableListOf(),45640464),
				MemoriaRAM("16GB DDR4",8, mutableListOf(juegos[8]),mutableListOf(),456453764),
				MemoriaRAM("24GB DDR4",9, mutableListOf(),mutableListOf(),45645774),
				MemoriaRAM("24GB DDR5",10, mutableListOf(),mutableListOf(),45641164)))


		//Generos de Juegos
		generos.addAll( arrayListOf(
				GeneroJuego("Accion",mutableListOf(juegos[0],juegos[1],juegos[2],juegos[3],juegos[5],juegos[6],juegos[7],juegos[8]),456415615),
				GeneroJuego("Estrategia",mutableListOf(juegos[3]),4561115615),
				GeneroJuego("Horror",mutableListOf(juegos[3]),45100415615),
				GeneroJuego("RPG",mutableListOf(juegos[4],juegos[6],juegos[7],juegos[9]),4564158505),
				GeneroJuego("Shooter",mutableListOf(juegos[5],juegos[8],juegos[9]),4566555615),
				GeneroJuego("Open World",mutableListOf(juegos[0],juegos[1],juegos[2],juegos[4],juegos[0],juegos[9]),4563320615),
				GeneroJuego("Aventura",mutableListOf(juegos[0],juegos[1],juegos[2],juegos[6],juegos[8]),454810015),
				GeneroJuego("Survival",mutableListOf(juegos[8]),4537005615)) )


		//Videojuegos
		juegos.addAll( arrayListOf(
				Videojuego("Sea of Thieves", "Eres libre y sin ataduras para afrontar la vida y a otros jugadores como plazcas. " +
						"Te encontrarás con otras tripulaciones mientras navegas en solitario o en grupo",25.40,"Steam",mutableListOf(generos[0],generos[5],generos[6]),mutableListOf(),mutableListOf(),procesadores[3],graficas[1],memorias[2],5151516156),

				Videojuego("Red Dead Redemption 2", "Es un juego de acción y aventuras de mundo abierto en el " +
						"que el jugador puede vagar libremente.",32.89,"Rockstar",mutableListOf(generos[0],generos[5],generos[6]),mutableListOf(),mutableListOf(),procesadores[5],graficas[2],memorias[4],5151696156),

				Videojuego("Grand Theft Auto V", "Se trata de una aventura de acción de mundo abierto con multitud " +
						"de misiones,además cuenta con modo Online.",9.89,"Rockstar",mutableListOf(generos[0],generos[5],generos[6]),mutableListOf(),mutableListOf(),procesadores[3],graficas[0],memorias[2],515192556156),

				Videojuego("Dead by Daylight", "Tienes poco tiempo para escapar de un asesino" +
						"que anda buscandote, intenta escapar junto de tus amigos o morir en el intento",5.22,"Steam",mutableListOf(generos[0],generos[1],generos[2]),mutableListOf(),mutableListOf(),procesadores[1],graficas[0],memorias[3],51515105556),

				Videojuego("Horizon Zero Dawn", "Es un RPG de acción en el que controlas a Aloy, una " +
						"superviviente humana en un paisaje distópico del siglo 31",24.73,"Steam",mutableListOf(generos[3],generos[5]),mutableListOf(),mutableListOf(),procesadores[4],graficas[2],memorias[6],515151599656),

				Videojuego("Overwatch", "Es un shooter multijugador en " +
						"primera persona basado en equipos. ",12.98,"Battle.net",mutableListOf(generos[0],generos[4]),mutableListOf(),mutableListOf(),procesadores[4],graficas[0],memorias[1],51515163006),

				Videojuego("World of Warcraft", "Es un juego mundo abierto donde podra hacer " +
						"muchas misiones, hacer amigos y adentrarte en la historia",69.99,"Battle.net",mutableListOf(generos[0],generos[3],generos[5],generos[6]),mutableListOf(),mutableListOf(),procesadores[5],graficas[1],memorias[3],51515161778),

				Videojuego("Borderlands 3", "Es un shooter en primera persona basado en la " +
						"recolección de botín. ",14.99,"Epic Games",mutableListOf(generos[0],generos[3]),mutableListOf(),mutableListOf(),procesadores[6],graficas[3],memorias[5],51515161996),

				Videojuego("Far Cry 5", "Es un alocado y divertido shooter de acción y aventuras en " +
						"primera persona en el que debes enfrentarte a oponentes de los más raros y extravagantes.",11.89,"Uplay",mutableListOf(generos[0],generos[4],generos[6],generos[7]),mutableListOf(),mutableListOf(),procesadores[4],graficas[3],memorias[7],51515161112),

				Videojuego("The Division 2", "La misión de la División consistía en encontrar el" +
						" origen de la enfermedad y hacer lo necesario para mantener y/o restablecer el orden público.",12.86,"Uplay",mutableListOf(generos[3],generos[4],generos[5]),mutableListOf(),mutableListOf(),procesadores[2],graficas[1],memorias[2],51515161300)))


		proceRepo.saveAll(procesadores)
		graficaRepo.saveAll(graficas)
		ramRepo.saveAll(memorias)
		generoRepo.saveAll(generos)
		juegoRepo.saveAll(juegos)

		 */
	}
}

fun main(args: Array<String>) {
	runApplication<VirtualGamingApplication>(*args)
}
