package socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(4444);
		
		System.out.println("server rodando em 4444");
		
		while(true){
			Socket socket = serverSocket.accept();
			
			new SocketMedia(socket).imc();
		}

	}

}
