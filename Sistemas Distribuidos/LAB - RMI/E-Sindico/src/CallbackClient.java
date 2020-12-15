import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
	import java.rmi.server.UnicastRemoteObject;
	import java.util.Scanner;


public class CallbackClient implements SindicoListener{
	

	    public static void main(String args[])  {
	        try  {
	            SindicoListener client = new CallbackClient();
	            System.out.println("Exporting the client");
	            UnicastRemoteObject.exportObject((Remote) client);    
	            String serverURL = "rmi://127.0.0.1:1099/ServerRMI";
	            InterfaceCaixa server =
	                        (InterfaceCaixa)Naming.lookup(serverURL);
	            server.addSindicoListener(client);
	            String s;
	            String[] aux;
	            while (true) {
	                System.out.println("Insira o método e valor <Metodo> <Valor>:");
	                Scanner sc = new Scanner(System.in);	                
	                s = sc.next();
	                aux = s.split(" ");
	                if (aux[0].equals("sacar")) {
	                    server.saque(Double.parseDouble(aux[1]));
	                }
	                if(aux[0].equals("depositar")){
	                	server.deposito(Double.parseDouble(aux[1]));
	                }
	            }
	                
	        } catch (Exception e) {
	            System.out.println("Exception occured " +
	                                "while adding attraction: " +
	                                "\n" + e.getMessage());
	        }
	    }

		@Override
		public void sindicoUsandoCaixa(Double valor, int type) throws RemoteException {
			
			System.out.println("Cliente usando caixa!");
			
		}
	}