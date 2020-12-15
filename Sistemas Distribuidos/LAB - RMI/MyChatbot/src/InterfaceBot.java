import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceBot extends Remote{
	
	public String getReposta(String texto) throws RemoteException;
}
