import java.rmi.Naming;

public class BotServer {
	private static String ENDERECO = "rmi://localhost/MyChatbot";
	BotServer(){
		try{
			InterfaceBot bot = new ImplementBot();
			Naming.rebind(ENDERECO, bot);
						
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new BotServer();
		System.out.println("Servidor rodando!");
	}
}
