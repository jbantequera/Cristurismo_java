/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristurismo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Javier
 */
public class Moto extends Vehiculo{
	//public:
		public Moto(){
			super();
		}

		public Moto(int id, String marca, String modelo, String tipoComb, int cil, int cab, int aer, int acel, int man, int vmax, int fren, int precioBase, boolean disponible){
			super(id, marca, modelo, tipoComb, cil, cab, aer, acel, man, vmax, fren, precioBase, disponible);
		}

		public Moto(final Moto m){
			super(m.getId(), m.getMarca(), m.getModelo(), m.getTipoCombustible(), m.getCilindrada(), 
			m.getCaballos(), m.getAerodinamica(), m.getAceleracion(), m.getManejo(), 
			m.getVelocidadMax(), m.getFrenada(), m.getPrecioBase(), m.getDisponible());
		}

		/**
		 * @brief Simula cómo correría una moto, incluyendo la probabilidad de
		 * que esta realizase un caballito
		 */
		@Override 
		public void correr(){
			Random dado = new Random();
			int numrandom = dado.nextInt(100);
			
			if (this.getManejo() <= numrandom)
				this.hacerCaballito();
		}

		public void hacerCaballito(){
			System.out.println("La " + this.getMarca() + this.getModelo() + " hace un caballito!!");
		}
}
