/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristurismo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class Cristurismo {
	//private:
		static Scanner cin = new Scanner (System.in);
	
		private ArrayList<Jugador> listaPlayers;
		private ArrayList<Vehiculo> catalogoVehiculos;
		private ArrayList<Pieza> catalogoPiezas;
		
		private Jugador jugadorActivo;
		
		private String login;
		private String passwd;
		
		boolean partidaCargada;
		private Vehiculo busquedaVehiculos[];
		private Jugador busquedaJugadores[];
		
		private void importarPiezas(){
			//Declaramos un Scanner que vamos a usar para leer el archivo Piezas.xml
			try {
				Scanner fin = new Scanner(new File("xmldata/Piezas.xml"));
				String aux;
				
				//Datos que vamos a leer, para luego llamar al constructor de Pieza por parámetros
				int id = 0;
				String nombre = null;
				int aerodinamica = 0;
				int frenada = 0;
				int velocidadMax = 0;
				int manejo = 0;
				int aceleracion = 0;
				int precio = 0;

				while(fin.hasNext()){
					aux = fin.next();
					
					if (aux.equals("<pieza>")){
						while(!aux.equals("</pieza>")){
							aux = fin.next();
							
							if (aux.equals("<id>"))
								id = fin.nextInt();
							
							if (aux.equals("<nombre>"))
								nombre = fin.next();
							
							if (aux.equals("<aerodinamica>"))
								aerodinamica = fin.nextInt();
							
							if (aux.equals("<frenada>"))
								frenada = fin.nextInt();
							
							if (aux.equals("<velocidadMax>"))
								velocidadMax = fin.nextInt();
							
							if (aux.equals("<manejo>"))
								manejo = fin.nextInt();
							
							if (aux.equals("<aceleracion>"))
								aceleracion = fin.nextInt();
							
							if (aux.equals("<precio>"))
								precio = fin.nextInt();
						} //Terminamos de leer <pieza>
						
						//Añadimos al catálogo una pieza con los datos leídos
						this.catalogoPiezas.add(new Pieza(id, nombre, aerodinamica, frenada, velocidadMax, manejo, aceleracion, precio));
						
						//Reiniciamos los datos
						id = 0;
						nombre = null;
						aerodinamica = 0;
						frenada = 0;
						velocidadMax = 0;
						manejo = 0;
						aceleracion = 0;
						precio = 0;
					}
				}
			} catch (FileNotFoundException ex) {
				System.out.println("El archivo Piezas.xml no existe");
			}
		}
		
		private void importarVehiculos(){
			//Declaramos un Scanner que vamos a usar para leer el archivo Catalogo.xml
			try {
				Scanner fin = new Scanner(new File("xmldata/Catalogo.xml"));
				String aux;
				
				//Tipo de Vehículo
				String tipoV = null;
				//Datos
				String marca = null;
				String modelo = null;
				int id = 0;
				//Depósito
				String tipoCombustible = null;
				int cilindrada = 0;
				//Estadísticas
				int caballos = 0;
				int aerodinamica = 0;
				int aceleracion = 0;
				int manejo = 0;
				int velocidadMax = 0;
				int frenada = 0;
				//Precios
				int precioBase = 0;
				boolean disponible = false;
				//Exclusivo de camion
				int capacidadCarga = 0;
				
				while (fin.hasNext()){
					aux = fin.next();
					
					if (aux.equals("<coche>") || aux.equals("<moto>") || aux.equals("<camion>")){
						tipoV = aux;
						
						while(!aux.equals("</coche>") && !aux.equals("</moto>") && !aux.equals("</camion>")){
							aux = fin.next();
							
							if (aux.equals("<id>"))
								id = fin.nextInt();
							
							if (aux.equals("<marca>"))
								marca = fin.next();
							
							if (aux.equals("<modelo>"))
								modelo = fin.next();
							
							if (aux.equals("<tipoCombustible>"))
								tipoCombustible = fin.next();
							
							if (aux.equals("<cilindrada>"))
								cilindrada = fin.nextInt();
							
							if (aux.equals("<caballos>"))
								caballos = fin.nextInt();
							
							if (aux.equals("<aerodinamica>"))
								aerodinamica = fin.nextInt();
							
							if (aux.equals("<aceleracion>"))
								aceleracion = fin.nextInt();
							
							if (aux.equals("<manejo>"))
								manejo = fin.nextInt();
							
							if (aux.equals("<velocidadMax>"))
								velocidadMax = fin.nextInt();
							
							if (aux.equals("<frenada>"))
								frenada = fin.nextInt();
							
							if (aux.equals("<precioBase>"))
								precioBase = fin.nextInt();
							
							if (aux.equals("<catalogado>"))
								disponible = fin.nextBoolean();
							
							if (aux.equals("<capacidadCarga>"))
								capacidadCarga = fin.nextInt();
						} //Terminamos de leer UN vehículo
						
						//Añadimos el vehículo del tipo que sea al catálogo
						if (tipoV.equals("<coche>"))
							this.catalogoVehiculos.add(new Coche(id, marca, modelo, tipoCombustible, cilindrada, caballos, aerodinamica, 
									aceleracion, manejo, velocidadMax, frenada, precioBase, disponible));
						else if (tipoV.equals("<moto>"))
							this.catalogoVehiculos.add(new Moto(id, marca, modelo, tipoCombustible, cilindrada, caballos, aerodinamica, 
									aceleracion, manejo, velocidadMax, frenada, precioBase, disponible));
						else if (tipoV.equals("<camion>"))
							this.catalogoVehiculos.add(new Camion(id, marca, modelo, tipoCombustible, cilindrada, caballos, aerodinamica, 
									aceleracion, manejo, velocidadMax, frenada, precioBase, disponible, capacidadCarga));
						
						//Reiniciamos los datos
						id = 0;
						marca = null;
						modelo = null;
						tipoCombustible = null;
						cilindrada = 0;
						caballos = 0;
						aerodinamica = 0;
						aceleracion = 0;
						manejo = 0;
						velocidadMax = 0;
						frenada = 0;
						precioBase = 0;
						disponible = false;
					} //Seguimos leyendo el archivo
				}
			} catch (FileNotFoundException ex) {
				System.out.println("El archivo Catalogo.xml no existe");
			}
		}
		
		private void importarJugadores(){
			try {
				//Declaramos un Scanner que vamos a usar para leer el archivo Jugadores.xml
				Scanner fin = new Scanner(new File("xmldata/Jugadores.xml"));
				String aux = null;
				
				//Datos que vamos a parsear
				int id = 0;
				String login = null;
				String passwd = null;
				double creditos = 0;
				int numeroVictorias = 0;

				//Variables para garaje
				Jugador pActual = null;
				Vehiculo vActual = null;
				int idvehiculo = 0;
				String tipoV = null;
				int idpieza = 0;
				
				while(fin.hasNext()){
					aux = fin.next();
					
					if (aux.equals("<jugador>")){
						while (!aux.equals("</jugador>")){
							aux = fin.next();
							
							if (aux.equals("<id>"))
								id = fin.nextInt();
							
							if (aux.equals("<login>"))
								login = fin.next();
							
							if (aux.equals("<passwd>"))
								passwd = fin.next();
							
							if (aux.equals("<creditos>"))
								creditos = fin.nextDouble();
							
							if (aux.equals("<numeroVictorias>")){
								numeroVictorias = fin.nextInt();
								
								//Creamos un jugador con los datos que ya tenemos listos
								pActual = new Jugador(id, login, passwd, creditos, numeroVictorias);
								this.listaPlayers.add(pActual);	
							}
														
							if (aux.equals("<carnetA>"))
								pActual.setCarnet(0, fin.nextBoolean());
							
							if (aux.equals("<carnetB>"))
								pActual.setCarnet(1, fin.nextBoolean());
							
							if (aux.equals("<carnetC>"))
								pActual.setCarnet(2, fin.nextBoolean());
							
							if (aux.equals("<garaje>")){
								while (!aux.equals("</garaje>")){
									aux = fin.next();
									
									if (aux.equals("<coche>") || aux.equals("<moto>") || aux.equals("<camion>")){
										tipoV = aux;
										while(!aux.equals("</coche>") && !aux.equals("</moto>") && !aux.equals("</camion>")){
											aux = fin.next();
											
											if (aux.equals("<id>")){
												idvehiculo = fin.nextInt();
												
												if (tipoV.equals("<coche>"))
													vActual = new Coche((Coche)this.buscarCatalogoVehiculosID(idvehiculo));
												else if (tipoV.equals("<moto>"))
													vActual = new Moto((Moto)this.buscarCatalogoVehiculosID(idvehiculo));
												else if (tipoV.equals("<camion>"))
													vActual = new Camion((Camion)this.buscarCatalogoVehiculosID(idvehiculo));
												
												pActual.agregarVehiculo(vActual);
											}
											
											if (aux.equals("<piezas>")){
												while (!aux.equals("</piezas>")){
													aux = fin.next();
													
													if (!aux.equals("</piezas>")){
														idpieza = Integer.parseInt(aux);
														pActual.asignarPiezaVehiculo(vActual, this.buscarCatalogoPiezasID(idpieza));
													}
												}
											}
										} //Terminamos de leer los datos del vehículo
										
										//Reiniciamos los datos referentes a vehículo
										vActual = null;
										idvehiculo = 0;
										idpieza = 0;
									}
								}
							}
						} //Terminamos de leer los datos de un jugador
						
						//Reiniciamos los datos referentes a jugador
						id = 0;
						login = null;
						passwd = null;
						creditos = 0;
						numeroVictorias = 0;
						pActual = null;
					}
				}
			} catch (FileNotFoundException ex) {
				System.out.println("El archivo Jugadores.xml no existe");
			}
		}
		
		private void exportarVehiculos(){
			try {
				PrintWriter fout = new PrintWriter("xmldata/CatalogoSave.xml", "UTF-8");
				Vehiculo vActual = null;
				Camion cActual = null;

				fout.println("<catalogo>");
				
				for (int i=0; i < this.catalogoVehiculos.size(); i++){
					vActual = this.catalogoVehiculos.get(i);
					
					if (vActual instanceof Coche)
						fout.println("\t<coche>");
					else if (vActual instanceof Moto)
						fout.println("\t<moto>");
					else if (vActual instanceof Camion)
						fout.println("\t<camion>");
					
					fout.println("\t\t<id> " + vActual.getId() + " </id>");
					fout.println("\t\t<marca> " + vActual.getMarca() + " </marca>");
					fout.println("\t\t<modelo> " + vActual.getModelo() + " </modelo>");
					fout.println("\t\t<tipoCombustible> " + vActual.getTipoCombustible() + " </tipoCombustible>");
					fout.println("\t\t<cilindrada> " + vActual.getCilindrada() + " </cilindrada>");
					fout.println("\t\t<caballos> " + vActual.getCaballos() + " </caballos>");
					fout.println("\t\t<aerodinamica> " + vActual.getAerodinamica() + " </aerodinamica>");
					fout.println("\t\t<aceleracion> " + vActual.getAceleracion() + " </aceleracion>");
					fout.println("\t\t<manejo> " + vActual.getManejo() + " </manejo>");
					fout.println("\t\t<velocidadMax> " + vActual.getVelocidadMax() + " </velocidadMax>");
					fout.println("\t\t<frenada> " + vActual.getFrenada() + " </frenada>");
					fout.println("\t\t<precioBase> " + vActual.getPrecioBase() + " </precioBase>");
					if (vActual instanceof Camion){
						cActual = (Camion)vActual;
						fout.println("\t\t<capacidadCarga> " + cActual.getCapacidadCarga() + " </capacidadCarga>");
						cActual = null;
					}
					fout.println("\t\t<catalogado> " + vActual.getDisponible() + " </catalogado>");
					
					if (vActual instanceof Coche)
						fout.println("\t</coche>");
					else if (vActual instanceof Moto)
						fout.println("\t</moto>");
					else if (vActual instanceof Camion)
						fout.println("\t</camion>");
					
					vActual = null;
				}
				
				fout.println("</catalogo>");
				
				fout.close();
			} catch (FileNotFoundException ex) {
				System.out.println("No se encontró el archivo CatalogoSave.xml");
			} catch (UnsupportedEncodingException ex) {
				System.out.println("Codificación no soportada (UTF-8)");
			}
		}
		
		private void exportarJugadores(){
			try {
				PrintWriter fout = new PrintWriter("xmldata/JugadoresSave.xml", "UTF-8");
				Jugador jActual = null;
				Vehiculo vActual = null;
				
				fout.println("<jugadores>");
				
				for (int i=0; i<this.listaPlayers.size(); i++){
					jActual = this.listaPlayers.get(i);
					
					fout.println("\t<jugador>");
					fout.println("\t\t<id> " + jActual.getId() + " </id>");
					fout.println("\t\t<login> " + jActual.getLogin() + " </login>");
					fout.println("\t\t<passwd> " + jActual.getPasswd() + " </passwd>");
					fout.println("\t\t<creditos> " + jActual.getCreditos() + " </creditos>");
					fout.println("\t\t<numeroVictorias> " + jActual.getNumeroVictorias() + " </numeroVictorias>");
					fout.println("\t\t<tipoCarnet>");
						fout.println("\t\t\t<carnetA> " + jActual.getCarnet(0) + " </carnetA>");
						fout.println("\t\t\t<carnetB> " + jActual.getCarnet(1) + " </carnetB>");
						fout.println("\t\t\t<carnetC> " + jActual.getCarnet(2) + " </carnetC>");
					fout.println("\t\t</tipoCarnet>");
					fout.println("\t\t<garaje>");
						for (int j=0; j < jActual.getTotalVehiculos(); j++){
							vActual = jActual.getVehiculo(j);
							
							if (vActual instanceof Coche)
								fout.println("\t\t\t<coche>");
							else if (vActual instanceof Moto)
								fout.println("\t\t\t<moto>");
							else if (vActual instanceof Camion)
								fout.println("\t\t\t<camion>");
							
							fout.println("\t\t\t\t<id> " + vActual.getId() + " </id>");
							
							fout.print("\t\t\t\t<piezas> ");
							for (int k=0; k < vActual.getTotalPiezas(); k++){
								fout.print(vActual.getPieza(k).getID() + " ");
							}
							fout.println("</piezas>");
							
							if (vActual instanceof Coche)
								fout.println("\t\t\t</coche>");
							else if (vActual instanceof Moto)
								fout.println("\t\t\t</moto>");
							else if (vActual instanceof Camion)
								fout.println("\t\t\t</camion>");
						}
					fout.println("\t\t</garaje>");
					fout.println("\t</jugador>");
				}
				fout.println("</jugadores>");
				fout.close();
			} catch (FileNotFoundException ex) {
				System.out.println("No se encontró el archivo CatalogoSave.xml");
			} catch (UnsupportedEncodingException ex) {
				System.out.println("Codificación no soportada (UTF-8)");
			}
		}
		
		/**
		 * @brief Busca un vehículo en el catálogo por su ID
		 * @param id
		 * @return resultado, que almacena el Vehículo que coincide con id
		 */
		private Vehiculo buscarCatalogoVehiculosID(int id){
			Vehiculo resultado = null;
			
			for (int i=0; i<this.catalogoVehiculos.size(); i++){
				if (this.catalogoVehiculos.get(i).getId() == id){
					resultado = this.catalogoVehiculos.get(i);
				}
			}
			
			return (resultado);
		}
		
		/**
		 * @brief Busca una pieza en el catálogo por su ID
		 * @param id
		 * @return resultado, que almacena la Pieza que coincide con id
		 */
		private Pieza buscarCatalogoPiezasID(int id){
			Pieza resultado = null;
			
			for (int i=0; i<this.catalogoPiezas.size(); i++){
				if (this.catalogoPiezas.get(i).getID() == id){
					resultado = this.catalogoPiezas.get(i);
				}
			}
			
			return(resultado);
		}
	
	//public:
		public Cristurismo(){
			this.listaPlayers = new ArrayList<Jugador>();
			this.catalogoVehiculos = new ArrayList<Vehiculo>();
			this.catalogoPiezas = new ArrayList<Pieza>();
			
			this.setLogin("admin");
			this.setPasswd("admin");
		}

		public Jugador getJugadorActivo() {
			return jugadorActivo;
		}

		public void setJugadorActivo(Jugador jugadorActivo) {
			this.jugadorActivo = jugadorActivo;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		public boolean getPartidaCargada() {
			return partidaCargada;
		}

		public void setPartidaCargada(boolean partidaCargada) {
			this.partidaCargada = partidaCargada;
		}

		/**
		 * @brief Muestra la bienvenida al juego
		 */
		public void bienvenida(){
			this.titulo();
			this.autentificacionAdmin();
		}
		
		/**
		 * @brief Muestra el título del juego en ASCII art
		 */
		public void titulo(){
			System.out.println("************************************************************************************************************************************************************************");
			System.out.println("  ,ad8888ba,               88                                               88                                                                ,ad8888ba,  888888888888  ");
			System.out.println(" d8 '    ` 8b                               ,d                                                                                               d8 '    ` 8b      88       ");
			System.out.println("d8'                                         88                                                                                              d8'                88       ");
			System.out.println("88             8b,dPPYba,  88  ,adPPYba,  MM88MMM  88       88  8b,dPPYba,  88  ,adPPYba,  88,dPYba,,adPYba,    ,adPPYba,                   88                 88       ");
			System.out.println("88             88P'    Y8  88  I8[          88     88       88  88P'    Y8  88  I8[        88P'    88      8a  a8       8a     aaaaaaaa     88      88888      88       ");
			System.out.println("Y8,            88          88   ` Y8ba,     88     88       88  88          88   ` Y8ba,   88      88      88  8b       d8                  Y8,        88      88       ");
			System.out.println(" Y8a.    .a8P  88          88  aa    ]8I    88,     8a,   ,a88  88          88  aa    ]8I  88      88      88   8a,   ,a8                    Y8a.    .a88      88       ");
			System.out.println("  ` Y8888Y '   88          88  ` YbbdP '     Y888   ` YbbdP'Y8  88          88  ` YbbdP '  88      88      88   ` YbbdP '                     ` Y88888P        88 		");
			System.out.println("************************************************************************************************************************************************************************");
		}

		/**
		 * @brief Nos permite identificarnos como un administrador en el sistema
		 */
		public void autentificacionAdmin(){
			String login = "";
			String passwd = "";
			
			System.out.println("Por favor, introduzca los credenciales de ADMIN: ");
			do{
				do{
					System.out.print("Login: ");
					login = cin.next();
					
					if (!login.equals(this.getLogin()))
						System.out.println("¡Login incorrecto!");
				}while (!login.equals(this.getLogin()));
				
				System.out.print("Contraseña: ");
				passwd = cin.next();
				
				if (!passwd.equals(this.getPasswd()))
					System.out.println("¡Credenciales incorrectos!");
			}while (!passwd.equals(this.getPasswd()));
			
			System.out.println("¡Credenciales correctos!");
			System.out.println("Has entrado al Sistema como Administrador");
			
			this.menu();
		}
		
		/**
		 * @brief Nos permite identificarnos como un usuario en el sistema
		 */
		public void autentificacionUser(){
			String login = null;
			String passwd = null;
			boolean loginencontrado = false;

			System.out.println("Por favor, introduzca sus credenciales");

			do{
				do{
					loginencontrado = false;
					this.jugadorActivo = null;

					System.out.print("Login: ");
					login = cin.next();

					for (int i=0; i < this.listaPlayers.size(); i++)
						if (this.listaPlayers.get(i).getLogin().equals(login)){
							loginencontrado = true;
							this.jugadorActivo = this.listaPlayers.get(i);
						}

					if (!loginencontrado)
						System.out.println("¡El usuario no existe!");
				}while (!loginencontrado);

				System.out.print("Contraseña: ");
				passwd = cin.next();

				if (!this.jugadorActivo.getPasswd().equals(passwd))
					System.out.println("¡Credenciales incorrectos!");

			}while(!this.jugadorActivo.getPasswd().equals(passwd));

			System.out.println("¡Credenciales correctos!");
			System.out.println("Bienvenido, " + this.jugadorActivo.getLogin());
						
			this.menuJugador();
		}
		
		/**
		 * @brief Muestra un menú que nos permite escoger entre opciones de
		 *		administración o de usuario
		 */
		public void menu(){
			int opcion = 0;

			do{
				System.out.println("******************MENU******************");
				System.out.println("1. Administración del Sistema");
				System.out.println("2. Acciones del Usuario");
				System.out.println("0. Salir");

				System.out.print("Opción: ");
				opcion = cin.nextInt();

				switch(opcion){
					case 0:
						System.out.println("¡Hasta la próxima!");
						break;
					case 1:
						this.menuAdmin();
						break;
					case 2:
						if (this.getPartidaCargada())
							this.autentificacionUser();
						else
							System.out.println("Debes cargar la partida para loguearte");
						break;
					default:
						System.out.println("Escoge una opción válida");
				}
			}while(opcion != 0);
		}
		
		/**
		 * @brief Muestra un menú con las opciones de administración
		 */
		public void menuAdmin(){
			int opcion = 0;
			
			do{
				System.out.println("******Administración del Sistema*******");

				System.out.println("1. Importar Datos al Sistema desde Ficheros *xml");
				System.out.println("2. Exportar Datos del Sistema a Ficheros *.xml");
				System.out.println("3. Ránkings");
				System.out.println("4. Vehículos");
				System.out.println("5. Jugadores");
				System.out.println("0. Volver al menú principal");

				System.out.print("Opción: ");
				opcion = cin.nextInt();

				switch (opcion){
					case 0:
						break;
					case 1:
						this.importarDatos();
						System.out.println("Datos Importados en el Sistema");
						break;
					case 2:
						if (this.getPartidaCargada()){
							this.exportarDatos();
							System.out.println("Datos Exportados");
						}
						else
							System.out.println("No hay ninguna partida cargada");
						break;
					case 3:
						if (this.getPartidaCargada())
							this.menuRankings();
						else
							System.out.println("No hay ninguna partida cargada");
						break;
					case 4:
						if (this.getPartidaCargada())
							this.menuVehiculos();
						else
							System.out.println("No hay ninguna partida cargada");
						break;
					case 5:
						if (this.getPartidaCargada())
							this.menuJugadores();
						else
							System.out.println("No hay ninguna partida cargada");
						break;
					default:
						System.out.println("Escoge una opción válida");
				}
			}while(opcion != 0);
		}
		
		/**
		 * @brief Muestra un menú con las opciones de administración
		 *		de jugadores
		 */
		public void menuJugador(){
			int opcion = 0;
			
			do{
				System.out.println("******* Menú de Jugador *******");
				System.out.println("1. Consultar Garaje");
				System.out.println("2. Comprar Vehículo (a catálogo)");
				System.out.println("3. Vender Vehículo (a jugador)");
				System.out.println("4. Asignar pieza a un vehículo");
				System.out.println("5. Quitar pieza a un vehículo");
				System.out.println("6. Competir");
				System.out.println("0. Volver");

				System.out.print("Opción: ");
				opcion = cin.nextInt();

				switch(opcion){
					case 0:
						break;
					case 1:
						this.jugadorActivo.consultarGaraje();
						break;
					case 2:
						this.menuCompraVehiculo();
						break;
					case 3:
						this.menuVentaVehiculo();
						break;
					case 4:
						this.menuAsignarPieza();
						break;
					case 5:
						this.menuQuitarPieza();
						break;
					case 6:
						this.menuCompeticion();
						break;
					default:
						System.out.println("Escoge una opción válida");
				}
			}while(opcion != 0);
		}
		
		/**
		 * @brief Muestra un menú con las opciones de visualización de ránkings
		 */
		public void menuRankings(){
			int opcion = 0;
			
			do{
				System.out.println("******RÁNKINGS*******");
				System.out.println("1. Top3 General Vehículo");
				System.out.println("2. Top3 Vehículo Específico");
				System.out.println("3. Top3 Jugadores");
				System.out.println("0. Volver al menú de administración");

				System.out.print("Opción: ");
				opcion = cin.nextInt();

				switch(opcion){
					case 0:
						break;
					case 1:
						this.rankingVehiculosGeneral();
						break;
					case 2:
						this.rankingVehiculosEspecifico();
						break;
					case 3:
						this.rankingJugadores();
						break;
					default:
						System.out.println("Escoge una opción válida");
				}
			}while (opcion != 0);
		}
		
		/**
		 * @brief Muestra un menú con las opciones de administración 
		 *		de vehículos
		 */
		public void menuVehiculos(){
			int opcion = 0;
			
			do{
				System.out.println("******Administración de Vehículos******");
				System.out.println("1. Agregar Vehículo a Catálogo");
				System.out.println("2. Descatalogar Vehículo");
				System.out.println("3. Buscar Vehículos");
				System.out.println("4. Modificar Vehiculo");
				System.out.println("5. Consultar Precio Medio de un Vehículo");
				System.out.println("0. Volver al menú de administración");

				System.out.print("Opción: ");
				opcion = cin.nextInt();

				switch(opcion){
					case 0:
						break;
					case 1:
						this.agregarVehiculoCatalogo();
						break;
					case 2:
						this.descatalogarVehiculo();
						break;
					case 3:
						this.busquedaVehiculos();
						break;
					case 4:
						this.modificarVehiculo();
						break;
					case 5:
						this.precioMedioVehiculo();
						break;
					default:
						System.out.println("Escoge una opción válida");
				}
			}while(opcion != 0);
		}
		
		/**
		 * @brief Muestra un menú con las opciones del jugador
		 */
		public void menuJugadores(){
			int opcion = 0;
			
			do{
				System.out.println("******Administración de Jugadores******");
				System.out.println("1. Agregar Jugador al Sistema");
				System.out.println("2. Eliminar Jugador del Sistema");
				System.out.println("3. Buscar Jugador");
				System.out.println("4. Modificar Jugador");
				System.out.println("0. Volver al menú de administración");

				System.out.print("Opción: ");
				opcion = cin.nextInt();

				switch(opcion){
					case 0:
						break;
					case 1:
						this.agregarJugador();
						break;
					case 2:
						this.eliminarJugador();
						break;
					case 3:
						this.busquedaJugador();
						break;
					case 4:
						this.modificarJugador();
						break;
					default:
						System.out.println("Escoge una opción válida");
				}
			}while(opcion != 0);
		}
		
		/**
		 * @brief Carga la partida desde distintos archivos .xml
		 */
		public void importarDatos(){
			this.importarPiezas();
			this.importarVehiculos();
			this.importarJugadores();
			
			//this.verCatalogoPiezas();
			//this.verCatalogoVehiculos();
			//this.verListaPlayers();
			
			this.setPartidaCargada(true);
		}
		
		/**
		 * @brief Exporta la partida a distintos archivos .xml
		 */
		public void exportarDatos(){
			this.exportarVehiculos();
			this.exportarJugadores();
		}
		
		/**
		 * @brief Muestra el catálogo de piezas
		 */
		public void verCatalogoPiezas(){
			System.out.println("***Catálogo de Piezas***");
			for (int i=0; i<this.catalogoPiezas.size(); i++){
				this.catalogoPiezas.get(i).printMinimo();
			}
		}
		
		/**
		 * @brief Muestra el catálogo de vehículos
		 */
		public void verCatalogoVehiculos(){
			System.out.println("***Catálogo de Vehículos***");
			
			for (int i=0; i<this.catalogoVehiculos.size(); i++){
				if (this.catalogoVehiculos.get(i).getDisponible())
					this.catalogoVehiculos.get(i).printMinimo();
			}
		}
		
		/**
		 * @brief Muestra la lista de jugadores
		 */
		public void verListaPlayers(){
			System.out.println("*** LISTA DE JUGADORES ***");
			
			for (int i = 0; i < this.listaPlayers.size(); i++) {
				this.listaPlayers.get(i).print();
			}
		}
		
		/**
		 * @brief Muestra un ránking de vehículos (sin tener en cuenta el tipo)
		 */
		public void rankingVehiculosGeneral(){
			this.busquedaVehiculos = new Vehiculo [5];
			this.busquedaJugadores = new Jugador [5];
			
			Jugador jActual = null;
			boolean yaInsertado = false;
			
			double valoracionMax = 0;
			double valoracionMin;
			int manejoMin;
			int aceleracionMax;
			
			
			//TOP 3 VEHÍCULOS por Valoración Media
			for (int i=0; i<3; i++){
				valoracionMax = 0; //Reiniciamos variable
				
				//Buscamos en catálogo
				for (int j = 0; j < this.catalogoVehiculos.size(); j++){
					yaInsertado = false;
					
					if (this.catalogoVehiculos.get(j).calcularMediaVehiculo() >= valoracionMax){
						for (int k=0; k<=i; k++){
							if (this.busquedaVehiculos[k] == this.catalogoVehiculos.get(j)){
								yaInsertado = true;
							}
						}
						if (!yaInsertado){
							this.busquedaVehiculos[i] = this.catalogoVehiculos.get(j);
							this.busquedaJugadores[i] = null;
							valoracionMax = this.catalogoVehiculos.get(j).calcularMediaVehiculo();
						}
					}
				}
				
				//Luego buscamos en los garajes de cada jugador
				for (int j = 0; j < this.listaPlayers.size(); j++){
					jActual = this.listaPlayers.get(j);
					
					for (int k = 0; k < jActual.getTotalVehiculos(); k++){
						yaInsertado = false;
					
						if (jActual.getVehiculo(k).calcularMediaVehiculo() >= valoracionMax){
							for (int l = 0; l <= i; l++){
								if (this.busquedaVehiculos[l] == jActual.getVehiculo(k)){
									yaInsertado = true;
								}
							}
							
							if (!yaInsertado){
								this.busquedaVehiculos[i] = jActual.getVehiculo(k);
								this.busquedaJugadores[i] = jActual;
								valoracionMax = jActual.getVehiculo(k).calcularMediaVehiculo();
							}
						}
					}
				}
			}
			
			//VEHÍCULO FAT-BAD
			//Primero asignamos el primer vehículo del catálogo (para tener un referente válido)
			this.busquedaVehiculos[3] = this.catalogoVehiculos.get(0);
			this.busquedaJugadores[3] = null;
			valoracionMin = this.busquedaVehiculos[3].calcularMediaVehiculo();
			
			//Buscamos en el resto del catálogo
			for (int i = 1; i < this.catalogoVehiculos.size(); i++){
				if (this.catalogoVehiculos.get(i).calcularMediaVehiculo() <= valoracionMin){
					this.busquedaVehiculos[3] = this.catalogoVehiculos.get(i);
					this.busquedaJugadores[3] = null;
					valoracionMin = this.catalogoVehiculos.get(i).calcularMediaVehiculo();
				}
			}
			
			//Buscamos en los garajes de los jugadores
			for (int i = 0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);
				
				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					if (jActual.getVehiculo(j).calcularMediaVehiculo() <= valoracionMin){
						this.busquedaVehiculos[3] = jActual.getVehiculo(j);
						this.busquedaJugadores[3] = jActual;
						valoracionMin = jActual.getVehiculo(j).calcularMediaVehiculo();
					}
				}
			}
			
			//VEHÍCULO NITRO-INDOMABLE
			//Primero asignamos el primer vehículo del catálogo (para tener un referente válido)
			this.busquedaVehiculos[4] = this.catalogoVehiculos.get(0);
			this.busquedaJugadores[4] = null;
			manejoMin = this.busquedaVehiculos[4].getManejo();
			aceleracionMax = this.busquedaVehiculos[4].getAceleracion();
			
			//Buscamos en el resto del catálogo
			for (int i = 1; i < this.catalogoVehiculos.size(); i++){
				if (this.catalogoVehiculos.get(i).getManejo() <= manejoMin){
					if (this.catalogoVehiculos.get(i).getAceleracion() >= aceleracionMax){
						this.busquedaVehiculos[4] = this.catalogoVehiculos.get(i);
						this.busquedaJugadores[4] = null;
						manejoMin = this.busquedaVehiculos[4].getManejo();
						aceleracionMax = this.busquedaVehiculos[4].getAceleracion();
					}
				}
			}
			
			//Buscamos en los garajes de los jugadores
			for (int i = 0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);
				
				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					if (jActual.getVehiculo(j).getManejo() <= manejoMin){
						if (jActual.getVehiculo(j).getAceleracion() >= aceleracionMax){
							this.busquedaVehiculos[4] = jActual.getVehiculo(j);
							this.busquedaJugadores[4] = jActual;
							manejoMin = this.busquedaVehiculos[4].getManejo();
							aceleracionMax = this.busquedaVehiculos[4].getAceleracion();
						}
					}
				}
			}
			
			//MOSTRAMOS LOS RESULTADOS
			System.out.println("--- TOP 3 ---");
			for (int i=0; i<3; i++){
				if (this.busquedaVehiculos[i] != null){
					System.out.print("[" + (i+1) + "] - [Propietario: ");

					if (this.busquedaJugadores[i] == null)
						System.out.print("Catálogo");
					else
						System.out.print(this.busquedaJugadores[i].getLogin());

					System.out.print("] - ");
					this.busquedaVehiculos[i].printMinimo();
				}
			}
			
			System.out.println("--- FAT BAD ---");
			System.out.print("[Propietario: ");
			
			if (this.busquedaJugadores[3] == null)
				System.out.print("Catálogo");
			else
				System.out.print(this.busquedaJugadores[3].getLogin());

			System.out.print("] - ");
			this.busquedaVehiculos[3].printMinimo();
			
			System.out.println("--- NITRO INDOMABLE ---");
			System.out.print("[Propietario: ");
			
			if (this.busquedaJugadores[4] == null)
				System.out.print("Catálogo");
			else
				System.out.print(this.busquedaJugadores[4].getLogin());

			System.out.print("] - ");
			this.busquedaVehiculos[4].printMinimo();
			
			this.busquedaVehiculos = null;
			this.busquedaJugadores = null;
		}
		
		/**
		 * @brief Muestra un ránking de vehículos del tipo especificado
		 */
		public void rankingVehiculosEspecifico(){
			this.busquedaVehiculos = new Vehiculo[5];
			this.busquedaJugadores = new Jugador[5];
			
			String tipoV = null;
			Jugador jActual = null;
			Vehiculo vActual = null;
			int posIni = 0;
			boolean referenteAsignado = false;
			boolean yaInsertado = false;
			
			double valoracionMax = 0;
			double valoracionMin = 0;
			int manejoMin = 0;
			int aceleracionMax = 0;
			
			do{
				System.out.print("¿De qué tipo de vehículo quieres mostrar el ránking? (Coche | Moto | Camion): ");
				tipoV = cin.next();
			}while ((!tipoV.equals("Coche")) && (!tipoV.equals("Moto")) && (!tipoV.equals("Camion")));
			
			//TOP 3 VEHÍCULOS por Valoración Media
			for (int i=0; i<3; i++){
				valoracionMax = 0; //Reiniciamos variable
				
				//Buscamos en catálogo
				for (int j = 0; j < this.catalogoVehiculos.size(); j++){
					vActual = this.catalogoVehiculos.get(j);
					yaInsertado = false;
					
					if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
						if (vActual.calcularMediaVehiculo() >= valoracionMax){
							for (int k = 0; k <= i; k++){
								if (this.busquedaVehiculos[k] == vActual){
									yaInsertado = true;
								}
							}
							
							if (!yaInsertado){
								this.busquedaVehiculos[i] = vActual;
								this.busquedaJugadores[i] = null;
								valoracionMax = vActual.calcularMediaVehiculo();
							}
						}
					}
				}
				
				//Luego buscamos en los garajes de cada jugador
				for (int j = 0; j < this.listaPlayers.size(); j++){
					jActual = this.listaPlayers.get(j);
					
					for (int k = 0; k < jActual.getTotalVehiculos(); k++){
						vActual = jActual.getVehiculo(k);
						yaInsertado = false;
						
						if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
							if (vActual.calcularMediaVehiculo() >= valoracionMax){
								for (int l = 0; l <= i; l++){
									if (this.busquedaVehiculos[l] == vActual){
										yaInsertado = true;
									}
								}
								
								if (!yaInsertado){
									this.busquedaVehiculos[i] = vActual;
									this.busquedaJugadores[i] = jActual;
									valoracionMax = vActual.calcularMediaVehiculo();
								}
							}
						}
					}
				}
			}
			
			//VEHÍCULO FAT-BAD
			//Primero asignamos el primer vehículo del tipo específico del catálogo (para tener un referente válido)
			for (int i=0; (i<this.catalogoVehiculos.size()) && (!referenteAsignado); i++){
				vActual = this.catalogoVehiculos.get(i);
				if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
					this.busquedaVehiculos[3] = vActual;
					this.busquedaJugadores[3] = null;
					valoracionMin = vActual.calcularMediaVehiculo();
					posIni = i;
					referenteAsignado = true;
				}
			}
			
			if (referenteAsignado){ //Si hemos encontrado un referente
				//Buscamos en el resto del catálogo
				for (int i = posIni; i < this.catalogoVehiculos.size(); i++){
					vActual = this.catalogoVehiculos.get(i);
					if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
						if (vActual.calcularMediaVehiculo() <= valoracionMin){
							this.busquedaVehiculos[3] = vActual;
							this.busquedaJugadores[3] = null;
							valoracionMin = vActual.calcularMediaVehiculo();
						}
					}
				}

				//Buscamos en los garajes de los jugadores
				for (int i = 0; i < this.listaPlayers.size(); i++){
					jActual = this.listaPlayers.get(i);

					for (int j = 0; j < jActual.getTotalVehiculos(); j++){
						vActual = jActual.getVehiculo(j);
						if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
							if (vActual.calcularMediaVehiculo() <= valoracionMin){
								this.busquedaVehiculos[3] = vActual;
								this.busquedaJugadores[3] = jActual;
								valoracionMin = vActual.calcularMediaVehiculo();
							}
						}
					}
				}
			}
			
			//VEHÍCULO NITRO-INDOMABLE
			referenteAsignado = false;
			
			//Primero asignamos el primer vehículo del tipo específico del catálogo (para tener un referente válido)
			for (int i=0; (i<this.catalogoVehiculos.size()) && (!referenteAsignado); i++){
				vActual = this.catalogoVehiculos.get(i);
				if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
					this.busquedaVehiculos[4] = vActual;
					this.busquedaJugadores[4] = null;
					manejoMin = vActual.getManejo();
					aceleracionMax = vActual.getAceleracion();
					posIni = i;
					referenteAsignado = true;
				}
			}
			
			if (referenteAsignado){ //Si hemos encontrado un referente
				//Buscamos en el resto del catálogo
				for (int i = posIni; i < this.catalogoVehiculos.size(); i++){
					vActual = this.catalogoVehiculos.get(i);
					if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
						if (vActual.getManejo() <= manejoMin){
							if (vActual.getAceleracion() >= aceleracionMax){
								this.busquedaVehiculos[4] = vActual;
								this.busquedaJugadores[4] = null;
								manejoMin = vActual.getManejo();
								aceleracionMax = vActual.getAceleracion();
							}
						}
					}
				}

				//Buscamos en los garajes de los jugadores
				for (int i = 0; i < this.listaPlayers.size(); i++){
					jActual = this.listaPlayers.get(i);

					for (int j = 0; j < jActual.getTotalVehiculos(); j++){
						vActual = jActual.getVehiculo(j);
						if ((tipoV.equals("Moto") && vActual instanceof Moto) || (tipoV.equals("Coche") && vActual instanceof Coche) || (tipoV.equals("Camion") && vActual instanceof Camion)){
							if (vActual.getManejo() <= manejoMin){
								if (vActual.getAceleracion() >= aceleracionMax){
									this.busquedaVehiculos[4] = vActual;
									this.busquedaJugadores[4] = jActual;
									manejoMin = vActual.getManejo();
									aceleracionMax = vActual.getAceleracion();
								}
							}
						}
					}
				}
			}
			
			//MOSTRAMOS LOS RESULTADOS
			System.out.println("--- TOP 3 ---");
			for (int i=0; i<3; i++){
				if (this.busquedaVehiculos[i] != null){
					System.out.print("[" + (i+1) + "] - [Propietario: ");

					if (this.busquedaJugadores[i] == null)
						System.out.print("Catálogo");
					else
						System.out.print(this.busquedaJugadores[i].getLogin());

					System.out.print("] - ");
					this.busquedaVehiculos[i].printMinimo();
				}
			}
			
			System.out.println("--- FAT BAD ---");
			System.out.print("[Propietario: ");
			
			if (this.busquedaJugadores[3] == null)
				System.out.print("Catálogo");
			else
				System.out.print(this.busquedaJugadores[3].getLogin());

			System.out.print("] - ");
			this.busquedaVehiculos[3].printMinimo();
			
			System.out.println("--- NITRO INDOMABLE ---");
			System.out.print("[Propietario: ");
			
			if (this.busquedaJugadores[4] == null)
				System.out.print("Catálogo");
			else
				System.out.print(this.busquedaJugadores[4].getLogin());

			System.out.print("] - ");
			this.busquedaVehiculos[4].printMinimo();
			
			this.busquedaVehiculos = null;
			this.busquedaJugadores = null;
		}
		
		/**
		 * @brief Muestra un ránking de jugadores
		 */
		public void rankingJugadores(){
			this.busquedaJugadores = new Jugador [3];
			Jugador jActual = null;
			
			double mediaMax = 0;
			double mediaActual = 0;
			int nVehiculosMax = 0;
			
			int nVictoriasMax = 0;
			
			double mediaMin = 0;
			int nVictoriasMin;
			
			//Jugador FREAK (Más Vehículos y mejor Valoración media)
			for (int i=0; i<this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);
				if (jActual.getTotalVehiculos() >= nVehiculosMax){
					mediaActual = 0;
					
					for (int j=0; j<jActual.getTotalVehiculos(); j++){
						mediaActual += jActual.getVehiculo(j).calcularMediaVehiculo();
					}
					
					mediaActual /= jActual.getTotalVehiculos();
					
					if (mediaActual >= mediaMax){
						this.busquedaJugadores[0] = jActual;
						mediaMax = mediaActual;
						nVehiculosMax = jActual.getTotalVehiculos();
					}
				}
			}
			
			//Jugador ReadyPlayerOne (Más número de victorias)
			for (int i=0; i<this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);
				
				if (jActual.getNumeroVictorias() >= nVictoriasMax){
					this.busquedaJugadores[1] = jActual;
					nVictoriasMax = jActual.getNumeroVictorias();
				}
			}
			
			//Jugador MESSI (Menos Victorias y Peor Valoración Media)
			//Asignamos el primer jugador para tener un referente
			jActual = this.listaPlayers.get(0);
			this.busquedaJugadores[2] = jActual;
			
			nVictoriasMin = jActual.getNumeroVictorias();
			
			for (int i=0; i<jActual.getTotalVehiculos(); i++){
				mediaMin += jActual.getVehiculo(i).calcularMediaVehiculo();
			}
			
			mediaMin /= jActual.getTotalVehiculos();
					
			for (int i=1; i<this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);
				
				if (jActual.getNumeroVictorias() <= nVictoriasMin){
					mediaActual = 0;
					
					for (int j=0; j<jActual.getTotalVehiculos(); j++){
						mediaActual += jActual.getVehiculo(j).calcularMediaVehiculo();
					}
					
					mediaActual /= jActual.getTotalVehiculos();
					
					if (mediaActual <= mediaMin){
						this.busquedaJugadores[2] = jActual;
						mediaMin = mediaActual;
						nVictoriasMin = jActual.getNumeroVictorias();
					}
				}
			}
			
			
			//Imprimimos resultados
			System.out.println("--- RÁNKING DE JUGADORES ---");
			System.out.println("FREAK: " + this.busquedaJugadores[0].getLogin());
			System.out.println("READYPLAYERONE: " + this.busquedaJugadores[1].getLogin());
			System.out.println("MESSI: " + this.busquedaJugadores[2].getLogin());
			
			this.busquedaJugadores = null;
		}
		
		/**
		 * @brief Menú que permite agregar un vehículo al catálogo
		 */
		public void agregarVehiculoCatalogo(){
			String tipoV = null;
			
			int id = this.catalogoVehiculos.size();
			String marca = null;
			String modelo = null;
			String tipoCombustible = null;
			int cilindrada = 0;
			int caballos = 0;

			//Estadísticas
			int aerodinamica = 0;
			int aceleracion = 0;
			int manejo = 0;
			int velocidadMax = 0;
			int frenada = 0;

			//Camión
			int capacidadCarga = 0;

			//Precios
			int precioBase = 0;

			boolean vehiculoExistente = false;
			
			System.out.println("--- Añadir Vehículo a Catálogo ---");
			do{
				System.out.print("¿Qué tipo de vehículo vas a añadir? (Coche | Moto | Camion): ");
				tipoV = cin.next();
			}while ((!tipoV.equals("Coche")) && (!tipoV.equals("Moto")) && (!tipoV.equals("Camion")));
			
			do{	
				vehiculoExistente = false;
				System.out.print("Introduzca la marca del nuevo vehículo: ");
				marca = cin.next();

				System.out.print("Introduzca el modelo del nuevo vehículo: ");
				modelo = cin.next();

				for (int i=0; i < this.catalogoVehiculos.size(); i++){
					if (((this.catalogoVehiculos.get(i).getMarca().equals(marca))) && ((this.catalogoVehiculos.get(i).getModelo().equals(modelo)))){
						vehiculoExistente = true;
						System.out.println("Ya existe este vehículo en el sistema");
					}
				}
			}while(vehiculoExistente);
			
			do{
				System.out.print("Introduzca el tipo de combustible (gasolina / diesel): ");
				tipoCombustible = cin.next();
			}while((!tipoCombustible.equals("gasolina")) && (!tipoCombustible.equals("diesel")));
			
			System.out.print("Cilindrada: ");
			cilindrada = cin.nextInt();
			
			System.out.print("Caballos: ");
			caballos = cin.nextInt();
			
			System.out.print("Velocidad Máx.: ");
			velocidadMax = cin.nextInt();
			
			System.out.print("Aceleración: ");
			aceleracion = cin.nextInt();
			
			System.out.print("Aerodinámica: ");
			aerodinamica = cin.nextInt();
			
			System.out.print("Manejo: ");
			manejo = cin.nextInt();
			
			System.out.print("Frenada: ");
			frenada = cin.nextInt();
			
			if (tipoV.equals("Camion")){
				System.out.print("Capacidad Carga: ");
				capacidadCarga = cin.nextInt();
			}
			
			System.out.print("Precio Base: ");
			precioBase = cin.nextInt();
			
			//Agregamos el Vehículo
			if (tipoV.equals("Coche"))
				this.catalogoVehiculos.add(new Coche(id, marca, modelo, tipoCombustible, cilindrada, 
						caballos, aerodinamica, aceleracion, manejo, velocidadMax, frenada, precioBase, true));
			else if (tipoV.equals("Moto"))
				this.catalogoVehiculos.add(new Moto(id, marca, modelo, tipoCombustible, cilindrada, 
						caballos, aerodinamica, aceleracion, manejo, velocidadMax, frenada, precioBase, true));
			else if (tipoV.equals("Camion"))
				this.catalogoVehiculos.add(new Camion(id, marca, modelo, tipoCombustible, cilindrada, 
						caballos, aerodinamica, aceleracion, manejo, velocidadMax, frenada, precioBase, true, capacidadCarga));
		}
		
		/**
		 * @brief Menú que permite descatalogar un vehículo del catálogo
		 */
		public void descatalogarVehiculo(){
			String marca = null;
			String modelo = null;	
			Vehiculo vehiculoEliminado = null;

			System.out.print("Introduzca la marca del vehículo: ");
			marca = cin.next();

			System.out.print("Introduzca el modelo del vehículo: ");
			modelo = cin.next();

			//Buscamos el vehículo en el sistema
			for (int i=0; i < this.catalogoVehiculos.size(); i++)
				if (((this.catalogoVehiculos.get(i).getMarca().equals(marca))) && ((this.catalogoVehiculos.get(i).getModelo().equals(modelo)))){
					vehiculoEliminado = this.catalogoVehiculos.get(i);
				}

			//Mensaje de error
			if (vehiculoEliminado == null)
				System.out.println("No existe este vehículo en el sistema");
			else //Si existe
				if (vehiculoEliminado.getDisponible())
					vehiculoEliminado.setDisponible(false);
				else
					System.out.println("El vehículo ya está descatalogado");
		}
		
		/**
		 * @brief Menú que permite buscar vehículos según distintos criterios
		 */
		public void busquedaVehiculos(){
			int opcion = 0;
			
			do{
				System.out.println("--- Búsqueda de Vehículos ---");
				System.out.println("1. Marca y modelo");
				System.out.println("2. Precio (menor)");
				System.out.println("3. Precio (mayor)");
				System.out.println("4. Valoración media (mayor)");
				System.out.println("0. Volver");

				do{
					System.out.print("Opción: ");
					opcion = cin.nextInt();
				}while(opcion < 0);

				switch(opcion){
					case 0:
						break;
					case 1:
						this.busquedaMarcaModelo();
						break;
					case 2:
						this.busquedaPrecioMenor();
						break;
					case 3:
						this.busquedaPrecioMayor();
						break;
					case 4:
						this.busquedaValoracionMedia();
						break;
					default:
						System.out.println("Escoja una opción válida");
				}
			}while(opcion != 0);
		}
		
		/**
		 * @brief Permite modificar un vehículo en el sistema
		 */
		public void modificarVehiculo(){
			int tam = 0;
			int util = 0;
			int pos = 0;

			Jugador jActual = null;
			Vehiculo vActual = null;

			String marca = null;
			String modelo = null;

			tam += this.catalogoVehiculos.size();

			for (int i=0; i<this.listaPlayers.size(); i++)
				tam += this.listaPlayers.get(i).getTotalVehiculos();

			this.busquedaVehiculos = new Vehiculo [tam];
			this.busquedaJugadores = new Jugador [tam];

			System.out.print("Introduzca la marca del vehículo que desea modificar: ");
			marca = cin.next();

			System.out.print("Introduzca el modelo del vehículo que desea modificar: ");
			modelo = cin.next();

			//Buscamos primero en catálogo
			for (int i=0; i < this.catalogoVehiculos.size(); i++){
				vActual = this.catalogoVehiculos.get(i);
				
				if ((vActual.getMarca().equals(marca)) && (vActual.getMarca().equals(marca))){
					this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = null;
					util++;
				}
			}

			//Buscamos en cada jugador
			for (int i=0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);

				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					vActual = jActual.getVehiculo(j);
					if ((vActual.getMarca().equals(marca)) && (vActual.getMarca().equals(marca))){
						this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = jActual;
						util++;			
					}
				}	
			}

			//Por último, imprimimos el resultado de la búsqueda
			System.out.println("");
			for (int i=0; i < util; i++){
				System.out.print("[" + i + "] - Propietario: ");
				
				if (this.busquedaJugadores[i] == null)
					System.out.print("Catálogo");
				else
					System.out.print(this.busquedaJugadores[i].getLogin());
				
				System.out.print(" - ");
				this.busquedaVehiculos[i].printMinimo();
			}

			do{
				System.out.println("\nSeleccione el vehículo que desea modificar (Negativo para salir): ");
				pos = cin.nextInt();
			}while(pos >= util);

			if (pos >= 0)
				this.busquedaVehiculos[pos].modificar();

			//Nos deshacemos del vector de busqueda actual
			this.busquedaVehiculos = null;
			this.busquedaJugadores = null;
		}
		
		/**
		 * @brief Calcula el precio medio de un vehículo realizando una media
		 * basada en el precio actual de ese vehículo en los garajes de los
		 * jugadores
		 */
		public void precioMedioVehiculo(){
			int tamaniomax = 0;
			int util = 0;

			String marca = null;
			String modelo = null;

			Jugador jActual = null;
			Vehiculo vActual = null;
			double preciomedio = 0;

			//Calculamos el tamaño máximo del vector de búsqueda
			for (int i=0; i < this.listaPlayers.size(); i++)
				tamaniomax += this.listaPlayers.get(i).getTotalVehiculos();

			//Ahora que ya lo tenemos calculado, reservamos el espacio necesario
			this.busquedaVehiculos = new Vehiculo [tamaniomax];

			//Preguntamos por la marca y el modelo
			System.out.print("Introduce la marca del vehículo: ");
			marca = cin.next();
			System.out.print("Introduce el modelo del vehículo: ");
			modelo = cin.next();

			//Procedemos a buscar en los garajes de cada usuario
			for (int i=0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);

				for (int j=0; j < jActual.getTotalVehiculos(); j++){
					vActual = jActual.getVehiculo(j);
					
					if ((vActual.getMarca().equals(marca)) && (vActual.getMarca().equals(marca))){
						this.busquedaVehiculos[util] = vActual;
						util++;			
					}
				}
			}

			//Si hemos encontrado alguno, procedemos a calcular el precio medio
			if (util > 0){
				for (int i=0; i < util; i++)
					preciomedio += busquedaVehiculos[i].getPrecioActual();

				//Dividimos entre las útiles
				preciomedio = preciomedio / util;

				System.out.println("Precio medio de " + marca + " " + modelo + ": " + preciomedio);
			}
			else
				System.out.println("No se ha encontrado el vehículo en ningún garaje");

			busquedaVehiculos = null;
		}
		
		/**
		 * @brief Busca vehículos que tengan la misma marca y modelo indicados
		 */
		public void busquedaMarcaModelo(){
			int tam = 0;
			int util = 0;
			int pos = 0;

			Jugador jActual = null;
			Vehiculo vActual = null;

			String marca = null;
			String modelo = null;

			tam += this.catalogoVehiculos.size();

			for (int i=0; i<this.listaPlayers.size(); i++)
				tam += this.listaPlayers.get(i).getTotalVehiculos();

			this.busquedaVehiculos = new Vehiculo [tam];
			this.busquedaJugadores = new Jugador [tam];

			System.out.print("Introduzca la marca del vehículo que desea buscar: ");
			marca = cin.next();

			System.out.print("Introduzca el modelo del vehículo que desea buscar: ");
			modelo = cin.next();

			//Buscamos primero en catálogo
			for (int i=0; i < this.catalogoVehiculos.size(); i++){
				vActual = this.catalogoVehiculos.get(i);
				
				if ((vActual.getMarca().equals(marca)) && (vActual.getMarca().equals(marca))){
					this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = null;
					util++;
				}
			}

			//Buscamos en cada jugador
			for (int i=0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);

				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					vActual = jActual.getVehiculo(j);
					if ((vActual.getMarca().equals(marca)) && (vActual.getMarca().equals(marca))){
						this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = jActual;
						util++;			
					}
				}	
			}

			//Por último, imprimimos el resultado de la búsqueda
			System.out.println("");
			for (int i=0; i < util; i++){
				System.out.print("[" + i + "] - Propietario: ");
				
				if (this.busquedaJugadores[i] == null)
					System.out.print("Catálogo");
				else
					System.out.print(this.busquedaJugadores[i].getLogin());
				
				System.out.print(" - ");
				this.busquedaVehiculos[i].printMinimo();
			}
		}
		
		/**
		 * @brief Busca vehículos que tengan un precio menor al indicado
		 */
		public void busquedaPrecioMenor(){
			int tam = 0;
			int util = 0;

			Jugador jActual = null;
			Vehiculo vActual = null;

			int precioMax = 0;

			tam += this.catalogoVehiculos.size();

			for (int i=0; i<this.listaPlayers.size(); i++)
				tam += this.listaPlayers.get(i).getTotalVehiculos();

			this.busquedaVehiculos = new Vehiculo [tam];
			this.busquedaJugadores = new Jugador [tam];

			System.out.print("Introduzca el precio máximo del vehículo: ");
			precioMax = cin.nextInt();

			//Buscamos primero en catálogo
			for (int i=0; i < this.catalogoVehiculos.size(); i++){
				vActual = this.catalogoVehiculos.get(i);
				
				if (vActual.getPrecioActual() <= precioMax){
					this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = null;
					util++;
				}
			}

			//Buscamos en cada jugador
			for (int i=0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);

				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					vActual = jActual.getVehiculo(j);
					if (vActual.getPrecioActual() <= precioMax){
						this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = jActual;
						util++;			
					}
				}	
			}

			//Por último, imprimimos el resultado de la búsqueda
			System.out.println("");
			for (int i=0; i < util; i++){
				System.out.print("[" + i + "] - Propietario: ");
				
				if (this.busquedaJugadores[i] == null)
					System.out.print("Catálogo");
				else
					System.out.print(this.busquedaJugadores[i].getLogin());
				
				System.out.print(" - ");
				this.busquedaVehiculos[i].printMinimo();
			}
		}
		
		/**
		 * @brief Busca vehículos que tengan un precio mayor al indicado
		 */
		public void busquedaPrecioMayor(){
			int tam = 0;
			int util = 0;

			Jugador jActual = null;
			Vehiculo vActual = null;

			int precioMin = 0;

			tam += this.catalogoVehiculos.size();

			for (int i=0; i<this.listaPlayers.size(); i++)
				tam += this.listaPlayers.get(i).getTotalVehiculos();

			this.busquedaVehiculos = new Vehiculo [tam];
			this.busquedaJugadores = new Jugador [tam];

			System.out.print("Introduzca el precio mínimo del vehículo: ");
			precioMin = cin.nextInt();

			//Buscamos primero en catálogo
			for (int i=0; i < this.catalogoVehiculos.size(); i++){
				vActual = this.catalogoVehiculos.get(i);
				
				if (vActual.getPrecioActual() >= precioMin){
					this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = null;
					util++;
				}
			}

			//Buscamos en cada jugador
			for (int i=0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);

				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					vActual = jActual.getVehiculo(j);
					if (vActual.getPrecioActual() >= precioMin){
						this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = jActual;
						util++;			
					}
				}	
			}

			//Por último, imprimimos el resultado de la búsqueda
			System.out.println("");
			for (int i=0; i < util; i++){
				System.out.print("[" + i + "] - Propietario: ");
				
				if (this.busquedaJugadores[i] == null)
					System.out.print("Catálogo");
				else
					System.out.print(this.busquedaJugadores[i].getLogin());
				
				System.out.print(" - ");
				this.busquedaVehiculos[i].printMinimo();
			}
		}
		
		/**
		 * @brief Busca vehículos que tengan una valoración media superior a 
		 * la indicada
		 */
		public void busquedaValoracionMedia(){
			int tam = 0;
			int util = 0;

			Jugador jActual = null;
			Vehiculo vActual = null;

			double mediaMin = 0;

			tam += this.catalogoVehiculos.size();

			for (int i=0; i<this.listaPlayers.size(); i++)
				tam += this.listaPlayers.get(i).getTotalVehiculos();

			this.busquedaVehiculos = new Vehiculo [tam];
			this.busquedaJugadores = new Jugador [tam];

			System.out.print("Introduzca la valoración media mínima del vehículo: ");
			mediaMin = cin.nextInt();

			//Buscamos primero en catálogo
			for (int i=0; i < this.catalogoVehiculos.size(); i++){
				vActual = this.catalogoVehiculos.get(i);
				
				if (vActual.calcularMediaVehiculo() >= mediaMin){
					this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = null;
					util++;
				}
			}

			//Buscamos en cada jugador
			for (int i=0; i < this.listaPlayers.size(); i++){
				jActual = this.listaPlayers.get(i);

				for (int j = 0; j < jActual.getTotalVehiculos(); j++){
					vActual = jActual.getVehiculo(j);
					if (vActual.calcularMediaVehiculo() >= mediaMin){
						this.busquedaVehiculos[util] = vActual;
					this.busquedaJugadores[util] = jActual;
						util++;			
					}
				}	
			}

			//Por último, imprimimos el resultado de la búsqueda
			System.out.println("");
			for (int i=0; i < util; i++){
				System.out.print("[" + i + "] - Propietario: ");
				
				if (this.busquedaJugadores[i] == null)
					System.out.print("Catálogo");
				else
					System.out.print(this.busquedaJugadores[i].getLogin());
				
				System.out.print(" - ");
				this.busquedaVehiculos[i].printMinimo();
			}	
		}
		
		/**
		 * @brief Muestra un menú que permite agregar un jugador al sistema
		 */
		public void agregarJugador(){
			int id = this.listaPlayers.size();
			String login = null;
			String passwd = null;
			double creditos = 10000;
			int numeroVictorias = 0;

			boolean loginExistente = false;

			System.out.println("--- Creación de Usuario ---");
			
			do{	
				loginExistente = false;
				System.out.print("Introduzca el login del nuevo usuario: ");
				login = cin.next();

				for (int i=0; i < this.listaPlayers.size(); i++)
					if (this.listaPlayers.get(i).getLogin().equals(login)){
						loginExistente = true;
						System.out.println("Ya existe un usuario con este login");
					}
			}while(loginExistente);

			System.out.print("Introduzca la contraseña del nuevo usuario: ");
			passwd = cin.next();

			//Agregamos el jugador
			this.listaPlayers.add(new Jugador(id, login, passwd, creditos, numeroVictorias));
		}
		
		/**
		 * @brief Muestra un menú que permite eliminar a un jugador por su login
		 */
		public void eliminarJugador(){
			String login = null;
			boolean loginExistente = false;
			int pos = 0;

			System.out.print("Introduzca el login del usuario que desea eliminar: ");
			login = cin.next();

			//Buscamos el usuario
			for (int i=0; i < this.listaPlayers.size(); i++)
				if (this.listaPlayers.get(i).getLogin().equals(login)){
					loginExistente = true;
					pos = i;
				}

			if (loginExistente)
				this.listaPlayers.remove(pos);
			else
				System.out.println("No existe un usuario con ese login en el sistema");
		}
		
		/**
		 * @brief Muestra un menú que permite buscar a jugador según varios 
		 *		criterios
		 *		1. Mostrar todos los jugadores
		 *		2. Buscar un jugador por login
		 */
		public void busquedaJugador(){
				int opcion = 0;
				String login = null;
				Jugador jugadorBuscado = null;

				do{
					System.out.println("--- BÚSQUEDA DE USUARIOS ---");
					System.out.println("1. Mostrar todos los usuarios del sistema");
					System.out.println("2. Buscar usuario por Login");
					System.out.println("0. Volver");
					
					System.out.print("Opción: ");
					opcion = cin.nextInt();

					switch(opcion){
						case 0:
							break;
						case 1:
							for (int i=0; i < this.listaPlayers.size(); i++){
								System.out.print("[" + i + "] - ");
								this.listaPlayers.get(i).printMinimo();
							}
							break;
						case 2:
							System.out.print("Introduzca el login deseado: ");
							login = cin.next();

							for (int i=0; i < this.listaPlayers.size(); i++)
								if (this.listaPlayers.get(i).getLogin().equals(login))
									jugadorBuscado = this.listaPlayers.get(i);

							if (jugadorBuscado != null)
								jugadorBuscado.print();
							else
								System.out.println("No existe ese jugador en el sistema");

							break;
						default:
							System.out.println("Escoge una opción válida");	
					}
				}while(opcion != 0);
		}
		
		/**
		 * @brief Muestra un menú que permite al administrador modificar los datos de
		 *		un jugador específico (buscado por Login)
		 */
		public void modificarJugador(){
			String loginBusqueda = null;
			Jugador jugadorModificado = null;
			int opcion = 0;

			String login = null;
			String passwd = null;
			double creditos = 0;

			System.out.print("Introduzca el login del usuario que desea modificar: ");
			loginBusqueda = cin.next();

			//Buscamos el usuario
			for (int i=0; i < this.listaPlayers.size(); i++)
				if (this.listaPlayers.get(i).getLogin().equals(loginBusqueda))
					jugadorModificado = this.listaPlayers.get(i);

			if (jugadorModificado != null){
				do{
					System.out.println("¿Qué dato desea modificar?");
					System.out.println("1. Login");
					System.out.println("2. Contraseña");
					System.out.println("3. Créditos");
					System.out.println("0. Volver");
					
					System.out.print("Opción: ");
					opcion = cin.nextInt();
					
					switch(opcion){
						case 0:
							break;
						case 1:
							System.out.print("Introduzca el nuevo login: ");
							login = cin.next();
							jugadorModificado.setLogin(login);
							break;
						case 2:
							System.out.print("Introduzca la nueva contraseña: ");
							passwd = cin.next();
							jugadorModificado.setPasswd(passwd);
							break;
						case 3:
							System.out.print("Introduzca la nueva cantidad de crédtos: ");
							creditos = cin.nextInt();
							jugadorModificado.setCreditos(creditos);
							break;
						default:
							System.out.println("Escoge una opción válida");		
					}
				}while(opcion != 0);
			}
			else
				System.out.println("El usuario no existe en el sistema");
		}
		
		/**
		 * Muestra un menú que permite al jugador actual comprar un vehículo al
		 * catálogo (siempre que tenga créditos suficientes y esté catalogado)
		 */
		public void menuCompraVehiculo(){
			this.busquedaVehiculos = new Vehiculo [this.catalogoVehiculos.size()];
			int utiles = 0;
			int pos = 0;

			//Añadimos a resultadoBusqueda los vehículos disponibles del catálogo
			for(int i=0; i < this.catalogoVehiculos.size(); i++){
				if (this.catalogoVehiculos.get(i).getDisponible()){
					this.busquedaVehiculos[utiles] = this.catalogoVehiculos.get(i);
					utiles++;
				}
			}

			//Los mostramos al usuario
			System.out.println("--- CATÁLOGO ---");
			for (int i=0; i < utiles; i++){
				System.out.print("[" + i + "] - ");
				this.busquedaVehiculos[i].printMinimo();
			}

			System.out.println("Créditos disponibles: " + this.jugadorActivo.getCreditos());
			do{
				System.out.print("¿Qué vehículo desea comprar? (Negativo para salir): ");
				pos = cin.nextInt();
			}while(pos >= utiles);
				
			if (pos >= 0){
				//Comprobamos si el jugador tiene créditos suficientes
				if (this.busquedaVehiculos[pos].getPrecioActual() <= this.jugadorActivo.getCreditos())
					this.jugadorActivo.comprarVehiculo(this.busquedaVehiculos[pos]);
				else
					System.out.println("¡No dispones de los créditos suficientes!");
			}

			this.busquedaVehiculos = null;
		}
		
		/**
		 * @brief Muestra un menú que permite al jugador actual vender 
		 * un vehículo de su garaje a un jugador distinto
		 */
		public void menuVentaVehiculo(){
			String login = null;
			int posV = 0;
			
			Jugador cliente = null;
			Vehiculo vActual = null;
			
			System.out.print("¿A qué jugador deseas vender el vehículo?: ");
			login = cin.next();

			//Buscamos al jugador
			for (int i = 0; i < this.listaPlayers.size(); i++){
				if (this.listaPlayers.get(i).getLogin().equals(login))
					cliente = this.listaPlayers.get(i);
			}

			//Si hemos encontrado al jugador
			if (cliente != null){
				jugadorActivo.verGaraje();

				do{
					System.out.print("¿Qué vehículo deseas vender? (Negativo para salir): ");
					posV = cin.nextInt();
				}while(posV >= this.jugadorActivo.getTotalVehiculos());

				if (posV >= 0){
					vActual = jugadorActivo.getVehiculo(posV);

					if (cliente.getCreditos() >= vActual.getPrecioActual()){
						this.jugadorActivo.venderVehiculo(cliente, jugadorActivo.getVehiculo(posV));
					}
					else
						System.out.println("¡El cliente no dispone de los créditos suficientes!");
				}
			}	
			else
				System.out.println("El jugador no existe en el sistema");
		}
		
		/**
		 * @brief Muestra un menú que permite al jugador actual añadir una pieza
		 * a uno de sus vehículos
		 */
		public void menuAsignarPieza(){
			Vehiculo vActual = null;
			Pieza piezaActual = null;

			int posV = 0;
			int posP = 0;

			boolean piezaEquipada = false;

			this.jugadorActivo.verGaraje();

			do{
				System.out.print("\n¿A qué coche deseas asignarle la pieza? (Negativo para salir): ");
				posV = cin.nextInt();
			}while(posV >= jugadorActivo.getTotalVehiculos());

			if (posV >= 0){
				vActual = jugadorActivo.getVehiculo(posV);

				//Imprimimos las piezas del catálogo
				for (int i=0; i < this.catalogoPiezas.size(); i++){
					System.out.print("[" + i + "] - ");
					this.catalogoPiezas.get(i).printMinimo();
				}

				do{
					System.out.println("Créditos disponibles: " + this.jugadorActivo.getCreditos());
					System.out.print("¿Qué pieza deseas comprar? (Negativo para salir): ");
					posP = cin.nextInt();
				}while(posP >= this.catalogoPiezas.size());

				if (posP >= 0){
					piezaActual = this.catalogoPiezas.get(posP);

					if (piezaActual.getPrecio() <= this.jugadorActivo.getCreditos()){
						//Recorremos el vector de tunning del vehículo para comprobar que la pieza no está ya asignada
						for (int i=0; i < vActual.getTotalPiezas(); i++){
							if (vActual.getPieza(i).getID() == piezaActual.getID()){
								System.out.println("El vehículo ya tiene equipada esta pieza");
								piezaEquipada = true;
							}
						}

						if (!piezaEquipada){
							vActual.setPieza(piezaActual);
							System.out.println(piezaActual.getNombre() + " comprado");
						}
					}
					else
						System.out.println("No dispones de los créditos suficientes");
				}
			}
		}
		
		/**
		 * @brief Muestra un menú que permite al jugador actual quitar una pieza
		 * a uno de sus vehículos
		 */
		public void menuQuitarPieza(){
			Vehiculo vActual = null;

			int posV = 0;
			int posP = 0;

			//Imprimimos el garaje del jugador activo	
			this.jugadorActivo.verGaraje();

			do{
				System.out.print("¿A qué coche deseas asignarle la pieza? (Negativo para salir): ");
				posV = cin.nextInt();
			}while(posV >= this.jugadorActivo.getTotalVehiculos());

			if (posV >= 0){
				vActual = this.jugadorActivo.getVehiculo(posV);

				//Mostramos las piezas equipadas a ese vehículo
				vActual.printTunning();

				do{
					System.out.println("¿Qué pieza desea eliminar? (Negativo para salir): ");
					posP = cin.nextInt();
				}while(posP >= vActual.getTotalPiezas());

				if (posP >= 0){
					vActual.quitarPieza(posP);
				}
			}
		}
		
		/**
		 * @brief Muestra un menú que permite al jugador actual competir
		 */
		public void menuCompeticion(){
			Vehiculo v1 = null;

			Jugador contrincante = null;
			Vehiculo v2 = null;

			String loginCont = null;
			int modalidad = 0;
			int pos = 0;

			boolean condAuxiliar = false;

			System.out.println("--- COMPETICIÓN ---");
			System.out.println("¿En qué modalidad desea competir?: ");
			System.out.println("1. Coches");
			System.out.println("2. Motos");
			System.out.println("3. Camión");
			System.out.println("0. Salir");

			do{
				System.out.print("Opción: ");
				modalidad = cin.nextInt();
			}while((modalidad > 3) || (modalidad < 0));
			
			if (this.jugadorActivo.getCarnet(modalidad - 1)){
				this.jugadorActivo.verGaraje();
				do{
					do{
						System.out.print("¿Con qué vehículo quieres competir? (Negativo para salir): ");
						pos = cin.nextInt();
					}while(pos >= this.jugadorActivo.getTotalVehiculos());

					if (pos >= 0){
						v1 = this.jugadorActivo.getVehiculo(pos);
						
						//Comprobamos que el vehículo que escogemos es válido para la categoría escogida
						if ((modalidad == 1 && v1 instanceof Coche) || (modalidad == 2 && v1 instanceof Moto) || (modalidad == 3 && v1 instanceof Camion)){
							condAuxiliar = true;
						}
						if (!condAuxiliar)
							System.out.println("Has escogido un vehículo de otra modalidad");
					}
					else
						condAuxiliar = true;
				}while(!condAuxiliar);

				if (pos >= 0){
					do{
						System.out.print("¿Contra quién quieres competir?: ");
						loginCont = cin.next();
					}while(this.jugadorActivo.getLogin().equals(loginCont));

					//Buscamos al contrincante
					for (int i=0; i < this.listaPlayers.size(); i++){
						if (this.listaPlayers.get(i).getLogin().equals(loginCont))
							contrincante = this.listaPlayers.get(i);
					}

					if (contrincante != null){ //Si existe el contrincante
						if (contrincante.getCarnet(modalidad - 1)){ //Y tiene el carnét necesario para competir
							contrincante.verGaraje();
							do{
								do{
									System.out.print("¿Contra qué vehículo quieres competir? (Negativo para salir): ");
									pos = cin.nextInt();
								}while(pos >= contrincante.getTotalVehiculos());

								if (pos >= 0){
									v2 = contrincante.getVehiculo(pos);

									//Comprobamos que el vehículo que escogemos es válido para la categoría escogida
									condAuxiliar = false;
									if ((modalidad == 1 && v2 instanceof Coche) || (modalidad == 2 && v2 instanceof Moto) || (modalidad == 3 && v2 instanceof Camion)){
										condAuxiliar = true;
									}
									
									if (!condAuxiliar)
										System.out.println("Has escogido un vehículo de otra modalidad");
								}
								else
									condAuxiliar = true;
							}while(!condAuxiliar);

							if (pos >= 0){
								//Hacemos una competición entre los dos vehículos
								this.competicion(this.jugadorActivo, v1, contrincante, v2);
							}
						}
						else
							System.out.println("El contrincante no dispone del carnét necesario");
					}
					else
						System.out.println("El contrincante no existe en el sistema");
				}
			}
			else
				System.out.println("No dispones del carnét necesario");
		}
		
		/**
		 * @brief Realiza una carrera básica entre un jugador y otro, basando el
		 *		resultado en intervalos (el más grande para el mejor vehículo)
		 * @param j1, el primer jugador
		 * @param v1, el vehículo del primer jugador
		 * @param j2, el segundo jugador
		 * @param v2, el vehículo del segundo jugador
		 * @post El jugador ganador recibe 500 créditos y ve incrementadas
		 *		sus victorias
		 */
		public void competicion(Jugador j1, Vehiculo v1, Jugador j2, Vehiculo v2){
			Random dado = new Random();
			int numrandom = dado.nextInt(100);
			
			Vehiculo vehiculos[] = new Vehiculo [2];
			Jugador jugadores[] = new Jugador [2];
			
			double formula1 = (0.1*v1.getManejo()) + (0.2*v1.getAceleracion()) + (0.2*v1.getFrenada()) + (0.5*v1.getVelocidadMax());
			double formula2 = (0.1*v2.getManejo()) + (0.2*v2.getAceleracion()) + (0.2*v2.getFrenada()) + (0.5*v2.getVelocidadMax());
		
			if (formula1 > formula2){
				vehiculos[0] = v1;
				jugadores[0] = j1;
				vehiculos[1] = v2;
				jugadores[1] = j2;
			}
			else{
				vehiculos[0] = v2;
				jugadores[0] = j2;
				vehiculos[1] = v1;
				jugadores[1] = j1;
			}

			if (numrandom > 70){
				System.out.println("¡Ha ganado " + jugadores[0].getLogin() + " con su flamante " + vehiculos[0].getMarca() + " " + vehiculos[0].getModelo() + "!");
				jugadores[0].setNumeroVictorias(jugadores[0].getNumeroVictorias() + 1);
				jugadores[0].setCreditos(jugadores[0].getCreditos() + 500);
			}
			else{
				System.out.println("¡Ha ganado " + jugadores[1].getLogin() + " con su flamante " + vehiculos[1].getMarca() + " " + vehiculos[1].getModelo() + "!");
				jugadores[1].setNumeroVictorias(jugadores[1].getNumeroVictorias() + 1);
				jugadores[1].setCreditos(jugadores[1].getCreditos() + 500);
			}
		}
		
		/**
		 * @brief Realiza una carrera avanzada entre un jugador y otro, basando el
		 *		resultado en la aceleración y la velocidad máxima de los vehículos
		 *		durante 10 turnos.
		 * @param j1, el primer jugador
		 * @param v1, el vehículo del primer jugador
		 * @param j2, el segundo jugador
		 * @param v2, el vehículo del segundo jugador
		 * @post El jugador ganador recibe 500 créditos y ve incrementadas
		 *		sus victorias
		 */
		public void competicionAvanzada(Jugador j1, Vehiculo v1, Jugador j2, Vehiculo v2){
			int turnos = 10;
			Vehiculo pos1 = null;
			Vehiculo pos2 = null;
			
			if (v1.getAceleracion() >= v2.getAceleracion()){
				pos1 = v1;
				pos2 = v2;
			}
			else{
				pos1 = v2;
				pos2 = v1;
			}
			
			for (int i = 0; i < turnos; i++){
				System.out.println("---- Turno " + i + " ----");
				
				if (pos1 == v1)
					System.out.println(j1.getLogin() + " va en cabeza");
				else
					System.out.println(j2.getLogin() + " va en cabeza");
				
				pos1.correr();
				pos2.correr();
				
				if (i == 5){
					if (v1.getVelocidadMax() <= v2.getVelocidadMax()){
						pos1 = v2;
						pos2 = v1;
					}
					else{
						pos1 = v1;
						pos2 = v2;
					}
				}
			}
			
			if (pos1 == v1){
				System.out.println("¡Ha ganado " + j1.getLogin() + " con su flamante " + v1.getMarca() + " " + v1.getModelo() + "!");
				j1.setNumeroVictorias(j1.getNumeroVictorias() + 1);
				j1.setCreditos(j1.getCreditos() + 500);
			}
			else{
				System.out.println("¡Ha ganado " + j2.getLogin() + " con su flamante " + v2.getMarca() + " " + v2.getModelo() + "!");
				j2.setNumeroVictorias(j2.getNumeroVictorias() + 1);
				j2.setCreditos(j2.getCreditos() + 500);
			}
		}
		
		public static void main(String[] args) {
			Cristurismo sistemaCT = new Cristurismo();
			
			sistemaCT.bienvenida();
		}
}