package socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

	public double[] imc(double altura, double peso) throws IOException {
		Socket socket = new Socket("10.208.200.165", 4444);
		
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		
		out.writeDouble(peso);
		out.writeDouble(altura);
		
		double[] imc = new double[4];
		imc[0] = in.readDouble();
		imc[1] = in.readDouble();
		imc[2] = in.readDouble();
		imc[3] = in.readDouble();
		
		
		in.close();
		out.close();
		socket.close();
		
		return imc;
	}

	

}
