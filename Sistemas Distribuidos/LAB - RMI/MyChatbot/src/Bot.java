import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Bot extends JFrame {

	//Area pra escrever:
	private JTextField txtEnter = new JTextField();
	
	//Area de chat:
	private JTextArea txtChat = new JTextArea();
	
	public Bot() {
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
				
				if(txtResposta.contains("Oi")){
					botResposta("Fala filha da puta!");
				}
				else if(txtResposta.contains("Tudo bem?")){
					int var = (int) (Math.random()*2+1);
					if(var == 1){
						botResposta("Tava bem antes de você chegar.");
					}
					else if(var == 2){
						botResposta("Queria estar rico, né, se isso é estar bem.");
					}
				}
				else{
					int var = (int) (Math.random()*3+1);
					if(var == 1){
						botResposta("Não entendi, fala outra coisa caralho!");
					}
					else if(var == 2){
						botResposta("Pergunta coisas que eu saiba, vinhado!");
					}
					else if(var == 3){
						botResposta("???");
					}
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
	
	public static void main(String[] args){
		new Bot();
	}

}