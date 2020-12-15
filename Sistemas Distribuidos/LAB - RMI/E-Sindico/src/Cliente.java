import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

public class Cliente implements SindicoListener{

    private static ClienteFrame frame;
    private static InterfaceCaixa serv;
    private String result;

    public static void main(String[] args) {
        SindicoListener sindico = new Cliente();
        UnicastRemoteObject.exportObject((Remote) sindico);        
        
        frame = new ClienteFrame(sindico);
        frame.setVisible(true);

    }

    public static void Conecta() {
        try {
            serv = (InterfaceCaixa) Naming.lookup("rmi://localhost/Caixa");
            JOptionPane.showMessageDialog(null, "cliente conectado ");
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "cliente MalformedURLException ");
            e.printStackTrace();
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "cliente RemoteException ");
            e.printStackTrace();
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "cliente NotBoundException ");
            e.printStackTrace();
        }
    }
    
    
    public void saque(double valor) {
        try {
           result = serv.saque(valor);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Operação não realizada");
            e.printStackTrace();
        }
       
    }

    public void deposito(double valor) {
        try {
           result = serv.deposito(valor);
           
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Operação não realizada");
            e.printStackTrace();
        }

    }

	@Override
	public void sindicoUsandoCaixa(Double valor, int type) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	

}