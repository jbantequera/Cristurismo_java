/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristurismo;

import static cristurismo.Cristurismo.cin;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Javier
 */
public class Jugador {
	//private:
		private int id;
		private String login;
		private String passwd;
		private double creditos;
		private int numeroVictorias;
		
		private boolean tipoCarnet[];
		private ArrayList<Vehiculo> garaje;
		
	//public:
		public Jugador(){
			this.tipoCarnet = new boolean [3];
			this.garaje = new ArrayList<Vehiculo>();
		}

		public Jugador(int id, String login, String passwd, double creditos, int numeroVictorias) {
			this.setId(id);
			this.setLogin(login);
			this.setPasswd(passwd);
			this.setCreditos(creditos);
			this.setNumeroVictorias(numeroVictorias);

			this.tipoCarnet = new boolean [3];
			this.garaje = new ArrayList<Vehiculo>();
		}

		public Jugador(final Jugador j){
			this.setId(j.getId());
			this.setLogin(j.getLogin());
			this.setPasswd(j.getPasswd());
			this.setCreditos(j.getCreditos());
			this.setNumeroVictorias(j.getNumeroVictorias());

			this.tipoCarnet = j.tipoCarnet.clone();
			this.garaje = (ArrayList)j.garaje.clone();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public double getCreditos() {
			return creditos;
		}

		public void setCreditos(double creditos) {
			this.creditos = creditos;
		}

		public int getNumeroVictorias() {
			return numeroVictorias;
		}

		public void setNumeroVictorias(int numeroVictorias) {
			this.numeroVictorias = numeroVictorias;
		}

		public void setCarnet(int pos, boolean carnet){
			this.tipoCarnet[pos] = carnet;
		}

		public boolean getCarnet(int pos){
			return (this.tipoCarnet[pos]);
		}

		public int getTotalVehiculos(){
			return (this.garaje.size());
		}
		
		/**
		 * @brief Agrega una copia del vehiculo v al garaje del jugador
		 * @param v, el vehículo que vamos a copiar
		 */
		public void agregarVehiculo(Vehiculo v){
			this.garaje.add(v);
		}

		/**
		 * @brief Devuelve el vehículo que se encuentra en la posición pos 
		 *		del garaje
		 * @param pos
		 * @return El vehículo que se encuentra en garaje[pos]
		 */
		public Vehiculo getVehiculo(int pos){
			return (this.garaje.get(pos));
		}

		/**
		 * @brief Realiza las transacciones necesarias para comprar 
		 *		el vehículo v
		 * @param v, el vehículo que vamos a comprar
		 * @post Créditos reducidos y existe una copia de v en el garaje
		 */
		public void comprarVehiculo(Vehiculo v){
			this.setCreditos(this.getCreditos() - v.getPrecioActual());
			this.agregarVehiculo(v);
		}

		/**
		 * @brief Realiza las transacciones necesarias para vender el
		 *		vehículo v al Jugador almacenado en cliente
		 * @param cliente, el jugador al que vamos a vender v
		 * @param v, el vehículo que vamos a vender
		 * @post Los créditos de this y cliente se ven alterados, v ya no existe
		 * en el garaje de this y existe una copia de v en el garaje de cliente
		 */
		public void venderVehiculo(Jugador cliente, Vehiculo v){
			cliente.comprarVehiculo(v);
			this.setCreditos(this.getCreditos() + v.getPrecioActual());
			this.garaje.remove(v);
		}

		public void asignarPiezaVehiculo(Vehiculo v, Pieza p){
			v.setPieza(p);
		}

		/**
		 * @brief Muestra de forma mínima los vehículos del garaje
		 */
		public void verGaraje(){
			System.out.println("Garaje: ");

			for (int i = 0; i < this.garaje.size(); i++) {
				System.out.print("[" + i + "] - ");
				this.garaje.get(i).printMinimo();
			}
		}
		
		/**
		 * @brief Muestra de forma mínima los vehículos del garaje y permite
		 *		al jugador mostrar con detalles un vehículo a su elección
		 */
		public void consultarGaraje(){
			int pos;
			this.verGaraje();
			
			do{
				System.out.print("¿Qué coche deseas consultar? (Negativo para salir): ");
				pos = cin.nextInt();
				
				if ((pos >= 0) && (pos < this.garaje.size()))
					this.garaje.get(pos).printCompleto();
			}while(pos >= 0);
		}
		
		/**
		 * @brief Muestra la información completa del jugador
		 */
		public void print(){
			System.out.println("*******" + this.getLogin() + "*******");
			System.out.println("Contraseña: " + this.getPasswd());
			System.out.println("Créditos: " + this.getCreditos());
			System.out.println("Victorias: " + this.getNumeroVictorias());
			System.out.println("Carnéts: ");
				System.out.println("\tCoche: " + this.getCarnet(0));
				System.out.println("\tMoto: " + this.getCarnet(1));
				System.out.println("\tCamión: " + this.getCarnet(2));
			this.verGaraje();
		}
		
		/**
		 * @brief Muestra la información mínima de un jugador
		 */
		public void printMinimo(){
			System.out.println(this.getLogin() + " [Creditos: " + this.getCreditos() + " - Victorias: " + this.getNumeroVictorias() + "]");
		}
}
