/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristurismo;

/**
 *
 * @author Javier
 */
public class Pieza {
	//private: 
		private int id;
		private String nombre;

		//Stats 
		private int aerodinamica;
		private int frenada;
		private int velocidadMax;
		private int manejo;
		private int aceleracion;

		private int precio;
	
	//public: 
		public Pieza(){
			//No hay que reservar memoria dinámica y las variables ya se
			//inicializan en Java a 0 o NULL
		}

		public Pieza(final Pieza p){
			this.setID(p.getID());
			this.setNombre(p.getNombre());
			
			this.setFrenada(p.getFrenada());
			this.setVelocidadMax(p.getVelocidadMax());
			this.setManejo(p.getManejo());
			this.setAceleracion(p.getAceleracion());
			this.setPrecio(p.getPrecio());
		}

		public Pieza(int id, String nombre, int aerodinamica, int frenada, int velocidadMax, int manejo, int aceleracion, int precio) {
			this.setID(id);
			this.setNombre(nombre);
			this.setAerodinamica(aerodinamica);
			this.setFrenada(frenada);
			this.setVelocidadMax(velocidadMax);
			this.setManejo(manejo);
			this.setAceleracion(aceleracion);
			this.setPrecio(precio);
		}
		
		public void setID(int ID){
			this.id = ID;
		}

		public int getID(){
			return (this.id);
		}

		public void setNombre(String nombre){
			this.nombre = nombre;
		}

		public String getNombre(){
			return (this.nombre);
		}

		public void setAerodinamica(int aerodinamica){
			this.aerodinamica = aerodinamica;
		}

		public int getAerodinamica(){
			return (this.aerodinamica);
		}

		public void setFrenada(int frenada){
			this.frenada = frenada;
		}

		public int getFrenada(){
			return (this.frenada);
		}

		public void setVelocidadMax(int velocidadMax){
			this.velocidadMax = velocidadMax;
		}

		public int getVelocidadMax(){
			return (this.velocidadMax);
		}

		public void setManejo(int manejo){
			this.manejo = manejo;
		}

		public int getManejo(){
			return (this.manejo);
		}

		public void setAceleracion(int aceleracion){
			this.aceleracion = aceleracion;
		}

		public int getAceleracion(){
			return (this.aceleracion);
		}

		public void setPrecio(int precio){
			this.precio = precio;
		}

		public int getPrecio(){
			return (this.precio);
		}

		/**
		 * @brief Imprime la información mínima de una pieza (Nombre + Precio)
		 */
		public void printMinimo(){
			System.out.println("Pieza: " + this.getNombre() + " (" + this.getPrecio() + "€)");
		}

		/**
		 * @brief Imprime la información completa de una pieza (Stats + Precio)
		*/
		public void printCompleto(){
			System.out.println("*****" + this.getNombre() + "*****");
			System.out.println("Aerodinámica: " + this.getAerodinamica());
			System.out.println("Frenada: " + this.getFrenada());
			System.out.println("Velocidad Max: " + this.getVelocidadMax());
			System.out.println("Manejo: " + this.getManejo());
			System.out.println("Aceleración: " + this.getAceleracion());
			System.out.println("Precio: " + this.getPrecio());
			System.out.println("*************************");
		}

		public static void main() { //main para testear la clase Pieza
			System.out.println("Main de Pieza");
			Pieza p1 = new Pieza();
			p1.printMinimo();
		}
}