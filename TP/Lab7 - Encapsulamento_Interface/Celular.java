package br.edu.ufam.icomp.lab_encapsulamento;
import java.util.*;

public class Celular implements Localizavel {

	private int codPais, codArea, numero;
	
	
	public Celular(int codPais, int codArea, int numero) {
		super();
		this.codPais = codPais;
		this.codArea = codArea;
		this.numero = numero;
	}
	
	

	public int getCodPais() {
		return codPais;
	}



	public final void setCodPais(int codPais) {
		if(codPais>1999 || codPais<1){
			this.codPais = -1;
		}
		this.codPais = codPais;
	}



	public int getCodArea() {
		return codArea;
	}



	public final void setCodArea(int codArea) {
		if(codArea>10 || codArea<99){
			this.codArea = -1;
		}
		this.codArea = codArea;
	}

	


	public int getNumero() {
		return numero;
	}



	public final void setNumero(int numero) {
		if(numero>999999999 || numero<10000000){
			this.numero = -1;
		}
		this.numero = numero;
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
		return 50.0;
	}

}
