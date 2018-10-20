/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristurismo;

import static cristurismo.Cristurismo.cin;
import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public abstract class Vehiculo {
	//protected:
		protected int id;
		protected String marca;
		protected String modelo;
		
		//Depósito
		protected String tipoCombustible;
		protected int cilindrada;
		protected int caballos;
		
		//Estadísticas
		protected int aerodinamica;
		protected int aceleracion;
		protected int manejo;
		protected int velocidadMax;
		protected int frenada;
		
		//Personalización
		protected ArrayList<Pieza> tunning;
		
		//Precios
		protected int precioBase;
		protected int precioActual;
		protected boolean disponible;
		
	//public:
		public Vehiculo(){
			//Creamos un objeto del tipo ArrayList
			this.tunning = new ArrayList<Pieza>();
		}
		
		public Vehiculo(final Vehiculo v){
			this.setId(v.getId());
			this.setMarca(v.getMarca());
			this.setModelo(v.getModelo());
			this.setTipoCombustible(v.getTipoCombustible());
			this.setCilindrada(v.getCilindrada());
			this.setCaballos(v.getCaballos());
			this.setAerodinamica(v.getAerodinamica());
			this.setAceleracion(v.getAceleracion());
			this.setManejo(v.getManejo());
			this.setVelocidadMax(v.getVelocidadMax());
			this.setFrenada(v.getFrenada());
			this.setPrecioBase(v.getPrecioBase());
			this.setDisponible(v.getDisponible());
			
			//Clonamos el vector de tunning del vehiculo v
			this.tunning = (ArrayList) v.tunning.clone();
			
			//Calculamos el precio actual
			this.calcularPrecioActual();
		}

		public Vehiculo(int id, String marca, String modelo, String tipoComb, int cil, int cab, int aer, int acel, int man, int vmax, int fren, int precioBase, boolean disponible){
			this.setId(id);
			this.setMarca(marca);
			this.setModelo(modelo);
			this.setTipoCombustible(tipoComb);
			this.setCilindrada(cil);
			this.setCaballos(cab);
			this.setAerodinamica(aer);
			this.setAceleracion(acel);
			this.setManejo(man);
			this.setVelocidadMax(vmax);
			this.setFrenada(fren);
			this.setPrecioBase(precioBase);
			this.setDisponible(disponible);
			
			this.tunning = new ArrayList<Pieza>();
			
			this.calcularPrecioActual();
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getTipoCombustible() {
			return tipoCombustible;
		}

		public void setTipoCombustible(String tipoCombustible) {
			this.tipoCombustible = tipoCombustible;
		}

		public int getCilindrada() {
			return cilindrada;
		}

		public void setCilindrada(int cilindrada) {
			this.cilindrada = cilindrada;
		}

		public int getCaballos() {
			return caballos;
		}

		public void setCaballos(int caballos) {
			this.caballos = caballos;
		}

		public int getAerodinamica() {
			return aerodinamica;
		}

		public void setAerodinamica(int aerodinamica) {
			this.aerodinamica = aerodinamica;
		}

		public int getAceleracion() {
			return aceleracion;
		}

		public void setAceleracion(int aceleracion) {
			this.aceleracion = aceleracion;
		}

		public int getManejo() {
			return manejo;
		}

		public void setManejo(int manejo) {
			this.manejo = manejo;
		}

		public int getVelocidadMax() {
			return velocidadMax;
		}

		public void setVelocidadMax(int velocidadMax) {
			this.velocidadMax = velocidadMax;
		}

		public int getFrenada() {
			return frenada;
		}

		public void setFrenada(int frenada) {
			this.frenada = frenada;
		}

		public int getPrecioBase() {
			return precioBase;
		}

		public void setPrecioBase(int precioBase) {
			this.precioBase = precioBase;
		}

		public int getPrecioActual() {
			return precioActual;
		}

		/**
		 * 
		 * @brief Suma el precio base del vehículo + El precio de las piezas 
		 * equipadas (Las que existen dentro del vector tunning), y lo asigna 
		 * al atributo precioActual
		 */
		public void calcularPrecioActual() {
			int precioTotal = this.getPrecioBase();
			
			if (!this.tunning.isEmpty()){
				for (int i=0; i < this.tunning.size(); i++){
					precioTotal += this.tunning.get(i).getPrecio();
				}
			}
			
			this.precioActual = precioTotal;
		}

		public boolean getDisponible() {
			return disponible;
		}

		public void setDisponible(boolean disponible) {
			this.disponible = disponible;
		}
		
		/**
		 * @brief Asigna la pieza p al vector de tunning, aumentando el precio actual del vehículo y sus estadísticas
		 */
		public void setPieza(Pieza p){
			//Añadimos la pieza al vector de tunning
			this.tunning.add(p);
			
			//Cambiamos los stats
			this.setVelocidadMax(this.getVelocidadMax() + p.getVelocidadMax());
			this.setAceleracion(this.getAceleracion() + p.getAceleracion());
			this.setFrenada(this.getFrenada() + p.getFrenada());
			this.setAerodinamica(this.getAerodinamica() + p.getAerodinamica());
			this.setManejo(this.getManejo() + p.getManejo());
			
			//Añadimos el precio al vehículo
			this.calcularPrecioActual();
		}
		
		public Pieza getPieza(int pos){
			return (this.tunning.get(pos));
		}
		
		public int getTotalPiezas(){
			return (this.tunning.size());
		}
		
		/**
		 * @brief Quita la pieza que ocupa la casilla pos del vector de tunning
		 * @param pos, la posición de la pieza que queremos eliminar
		 */
		public void quitarPieza(int pos){
			//Cambiamos los stats
			this.setVelocidadMax(this.getVelocidadMax() - this.tunning.get(pos).getVelocidadMax());
			this.setAceleracion(this.getAceleracion() - this.tunning.get(pos).getAceleracion());
			this.setFrenada(this.getFrenada() - this.tunning.get(pos).getFrenada());
			this.setAerodinamica(this.getAerodinamica() - this.tunning.get(pos).getAerodinamica());
			this.setManejo(this.getManejo() - this.tunning.get(pos).getManejo());			
			
			//Eliminamos la pieza del vector tunning
			this.tunning.remove(pos);
		}
		
		public abstract void correr();
		
		/**
		 * @brief Calcula la media de las estadísticas de un vehículo
		 * @return media, la suma de las estadísticas / 5
		 */
		public double calcularMediaVehiculo(){
			double media = 0;
			
			//Sumamos las estadísticas
			media += this.getAerodinamica();
			media += this.getAceleracion();
			media += this.getManejo();
			media += this.getVelocidadMax();
			media += this.getFrenada();
			
			media /= 5;
			
			return (media);
		}
		
		/**
		 * @brief Muestra la información completa de un vehículo
		 */
		public void printCompleto(){			
			System.out.println("*****" + this.getMarca() + " - " + this.getModelo() + "*****");
			System.out.println("Combustible: " + this.getTipoCombustible());
			
			System.out.println("-- STATS --");
			System.out.println("Velocidad Máx.: " + this.getVelocidadMax());
			System.out.println("Aceleración: " + this.getAceleracion());
			System.out.println("Frenada: " + this.getFrenada());
			System.out.println("Aerodinamica: " + this.getAerodinamica());
			System.out.println("Manejo: " + this.getManejo());
			
			System.out.println("");
			this.printTunning();
			
			System.out.println("Valoración: " + this.calcularMediaVehiculo());
			System.out.println("Precio Actual: " + this.getPrecioActual());
			System.out.println("*************************");
		}
		
		/**
		 * @brief Muestra la información mínima de un vehículo
		 */
		public void printMinimo(){
			System.out.println(this.getMarca() + " " + this.getModelo() 
					+ " (Valoración: " + this.calcularMediaVehiculo() 
					+ " || Precio: " + this.getPrecioActual() + ")");
		}
		
		/**
		 * @brief Muestra el vector de tunning de un vehículo
		 */
		public void printTunning(){
			if (!this.tunning.isEmpty()){
				System.out.println("-- TUNNING --");
				
				for (int i=0; i < this.tunning.size(); i++){
					System.out.print("[" + i + "]");
					this.tunning.get(i).printMinimo();
				}
				
				System.out.println("");
			}
		}
		
		/**
		 * @brief Muestra un menú que permite modificar los datos del vehículo
		 */
		public void modificar(){
			int opcion = 0;
			String cadena = null;
			int stat = 0;

			System.out.println("¿Qué datos desea modificar?");
			System.out.println("1. Marca");
			System.out.println("2. Modelo");
			System.out.println("3. Tipo de Combustible");
			System.out.println("4. Caballos");
			System.out.println("0. Salir");
			
			do{
				System.out.print("Opción: ");
				opcion = cin.nextInt();
			}while(opcion > 4);

			switch(opcion){
				case 0:
					break;
				case 1:
					System.out.print("Introduce la nueva marca: ");
					cadena = cin.next();
					this.setMarca(cadena);
					break;
				case 2:
					System.out.print("Introduce el nuevo modelo: ");
					cadena = cin.next();
					this.setModelo(cadena);
					break;
				case 3:
					do{
						System.out.println("Introduce el tipo de combustible (gasolina / diesel): ");
						cadena = cin.next();
					}while((!cadena.equals("gasolina")) && (!cadena.equals("diesel")));
					
					this.setTipoCombustible(cadena);
					break;
				case 4:
					System.out.println("Introduce los nuevos caballos: ");
					stat = cin.nextInt();
					this.setCaballos(stat);
					break;
				default:
					System.out.println("Escoge una opción válida");
			}
		}
}