import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementBot extends UnicastRemoteObject implements InterfaceBot{
	
	

	protected ImplementBot() throws RemoteException {
		super();
	}

	@Override
	public String getReposta(String texto) throws RemoteException {
		switch(texto){
		case "Olá":
			return "Oi, turu bo?";
		default:
			return "Não entendi o que você falou";
		}
	}

}
