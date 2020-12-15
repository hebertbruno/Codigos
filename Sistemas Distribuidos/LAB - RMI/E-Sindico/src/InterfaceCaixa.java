import java.rmi.*;

public interface InterfaceCaixa extends Remote {
	public String saque(double valor) throws RemoteException;
	public String deposito(double valor) throws RemoteException;
	public void addSindicoListener(SindicoListener listener) throws RemoteException;

}
