import java.net.MalformedURLException;
import java.rmi.*;

public class Server {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		InterfaceCaixa caixa = new Caixa();
		Naming.rebind("rmi://localhost/ESindico", caixa);
		System.out.println("Servidor Iniciado");
	}

}
