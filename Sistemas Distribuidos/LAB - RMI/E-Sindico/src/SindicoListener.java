import java.rmi.RemoteException;

public interface SindicoListener {
	
	public void sindicoUsandoCaixa(Double valor, int type) throws RemoteException;
}
