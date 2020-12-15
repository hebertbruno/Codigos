import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class BotCliente extends JFrame {
	private static String ENDERECO = "rmi://localhost/MyChatbot";

	//Area pra escrever:
	private JTextField txtEnter = new JTextField();
	
	//Area de chat:
	private JTextArea txtChat = new JTextArea();
	
	public BotCliente(InterfaceBot bot) {
		//Artibutos do JFrame:
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("MyChatbot");
		
		//Atributos do txtEnter:
		txtEnter.setLocation(2, 540);
		txtEnter.setSize(590, 30);
		
		//ActionListener do txtEnter:
		txtEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String txtResposta = txtEnter.getText();
				txtChat.append("Você: " + txtResposta + "\n");
				
				try {
					String respostaBot = bot.getReposta(txtResposta);
					botResposta(respostaBot);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				txtEnter.setText("");
			}
		});
		
		//Atributos do txtChat
		txtChat.setLocation(15, 5);
		txtChat.setSize(560, 510);
		txtChat.setEditable(false);
		
		//Adicionando os intens ao JFrame
		this.add(txtEnter);
		this.add(txtChat);
	}
	
	public void botResposta(String s){
		txtChat.append("Bot: " + s + "\n");
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		InterfaceBot bot = (InterfaceBot) Naming.lookup(ENDERECO);
		new BotCliente(bot);
	}

}