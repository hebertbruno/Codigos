package br.edu.ufam.icomp.lab_encapsulamento;

import java.util.Random;

public class CarroLuxuoso extends Carro implements Localizavel {

	public CarroLuxuoso(String placa) {
		super(placa);
		// TODO Auto-generated constructor stub
	}

	public Posicao getPosicao() {
		// TODO Auto-generated method stub
		Random r = new Random();
		double latitude = -3.160000 + (-2.960000 - (-3.160000)) * r.nextDouble();
		
		Random r1 = new Random();
		double longitude = -60.120000 + (-59.820000 - (-60.120000)) * r1.nextDouble();
		
		Random r2 = new Random();
		double altitude = 15.0 + (100.0 - 15.0) * r2.nextDouble();
		
		Posicao s = new Posicao(latitude, longitude, altitude);
		return s;
		
		
	}

	public double getErroLocalizacao() {
		// TODO Auto-generated method stub
		return 15.0;
	}

}
