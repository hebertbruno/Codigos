package socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketMedia {

	private Socket socket;

	public SocketMedia(Socket socket) {
		this.socket = socket;
		
	}

	public void imc() throws IOException {
		
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		
		double peso = in.readDouble();
		double altura = in.readDouble();
		double descr;
		
		double imc = peso/(altura*2); // Calculo do imc
		double pesoMin = 18.5*(altura*2); // Peso Min de acordo com a altura
		double pesoMax = 25*(altura*2);	//Peso maximo de acordo com a altura
		
		if(imc<18.5){
			descr = 1.0;
		}else if(imc>25){
			descr = 2.0;
		} else{
			descr = 3.0;
		}
			
		out.writeDouble(imc);
		out.writeDouble(descr);
		out.writeDouble(pesoMin);
		out.writeDouble(pesoMax);
		
		in.close();
		out.close();
		socket.close();
		
	}

}
