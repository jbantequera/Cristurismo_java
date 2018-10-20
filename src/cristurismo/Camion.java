/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristurismo;

import java.util.Random;

/**
 *
 * @author Javier
 */
public class Camion extends Vehiculo{
	//private:
		private int capacidadCarga;
	
	//public:
		public Camion(){
			super();
		}

		public Camion(int id, String marca, String modelo, String tipoComb, int cil, int cab, int aer, int acel, int man, int vmax, int fren, int precioBase, boolean disponible, int capacidadCarga){
			super(id, marca, modelo, tipoComb, cil, cab, aer, acel, man, vmax, fren, precioBase, disponible);
			this.setCapacidadCarga(capacidadCarga);
		}

		public Camion(final Camion c){
			super(c.getId(), c.getMarca(), c.getModelo(), c.getTipoCombustible(), c.getCilindrada(), 
			c.getCaballos(), c.getAerodinamica(), c.getAceleracion(), c.getManejo(), 
			c.getVelocidadMax(), c.getFrenada(), c.getPrecioBase(), c.getDisponible());

			this.setCapacidadCarga(c.getCapacidadCarga());
		}
		
		public int getCapacidadCarga() {
			return capacidadCarga;
		}

		public void setCapacidadCarga(int capacidadCarga) {
			this.capacidadCarga = capacidadCarga;
		}
		
		/**
		 * @brief Simula cómo correría un camión, incluyendo la probabilidad de
		 *		que este toque la bocina
		 */
		@Override
		public void correr(){
			System.out.println("Corre como un camión");
		}
		
		public void tocarBocina(){
			Random dado = new Random();
			int numrandom = dado.nextInt(100);
			
			if (50 >= numrandom)
				this.tocarBocina();
		}
		
		/**
		 * @brief Muestra la información completa del camión
		 */
		@Override
		public void printCompleto(){
						System.out.println("*****" + this.getMarca() + " - " + this.getModelo() + "*****");
			System.out.println("Combustible: " + this.getTipoCombustible());
			
			System.out.println("-- STATS --");
			System.out.println("Velocidad Máx.: " + this.getVelocidadMax());
			System.out.println("Aceleración: " + this.getAceleracion());
			System.out.println("Frenada: " + this.getFrenada());
			System.out.println("Aerodinamica: " + this.getAerodinamica());
			System.out.println("Manejo: " + this.getManejo());
			
			System.out.println("-- CAMIÓN --");
			System.out.println("Capacidad Carga: " + this.getCapacidadCarga());
			
			System.out.println("");
			this.printTunning();
			
			System.out.println("Valoración: " + this.calcularMediaVehiculo());
			System.out.println("Precio Actual: " + this.getPrecioActual());
			System.out.println("*************************");
		}
}
