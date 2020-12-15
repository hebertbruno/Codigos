import java.rmi.*;
import java.rmi.server.*;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.callback.Callback;

public class Caixa extends UnicastRemoteObject implements InterfaceCaixa {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SindicoListener listener;
	private double saldo;
	
	protected Caixa() throws RemoteException {
		super();		
	}	

	@Override
	public void saque(double valor) throws RemoteException {
		if(valor <= saldo){
			saldo = saldo - valor;
			if(listener != null){
				listener.sindicoUsandoCaixa(valor,1);
			}			
		}else{
			if(listener != null){
				listener.sindicoUsandoCaixa(valor,2);
			}
		}
	}

	@Override
	public void deposito(double valor) throws RemoteException {
		saldo = saldo + valor;
		if(listener != null){
			listener.sindicoUsandoCaixa(valor, 3);
		}
	}

	@Override
	public void addSindicoListener(SindicoListener listener) throws RemoteException {		
		this.listener = listener;		
	}
	
	

}
