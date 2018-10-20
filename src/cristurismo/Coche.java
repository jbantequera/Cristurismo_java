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
public class Coche extends Vehiculo{
	
	//public:
		public Coche(){
			super();
		}

		public Coche(int id, String marca, String modelo, String tipoComb, int cil, int cab, int aer, int acel, int man, int vmax, int fren, int precioBase, boolean disponible){
			super(id, marca, modelo, tipoComb, cil, cab, aer, acel, man, vmax, fren, precioBase, disponible);
		}

		public Coche(final Coche c){
			super(c.getId(), c.getMarca(), c.getModelo(), c.getTipoCombustible(), c.getCilindrada(), 
					c.getCaballos(), c.getAerodinamica(), c.getAceleracion(), c.getManejo(), 
					c.getVelocidadMax(), c.getFrenada(), c.getPrecioBase(), c.getDisponible());
		}

		/**
		 * @brief Simula cómo correría un coche, incluyendo la probabilidad de
		 * que este realice un trompo
		 */
		@Override
		public void correr(){
			Random dado = new Random();
			int numrandom = dado.nextInt(100);
			
			if (this.getManejo() >= numrandom)
				this.hacerTrompo();
		}

		public void hacerTrompo(){
			System.out.println("El " + this.getMarca() + this.getModelo() + " hace un trompo");
		}
}
