import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementBot extends UnicastRemoteObject implements InterfaceBot{
	
	

	protected ImplementBot() throws RemoteException {
		super();
	}

	@Override
	public String getReposta(String texto) throws RemoteException {
		switch(texto){
		case "Ol�":
			return "Oi, turu bo?";
		default:
			return "N�o entendi o que voc� falou";
		}
	}

}
