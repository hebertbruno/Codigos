package socket.client;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ClientTest extends JFrame {
	
	public ClientTest(){
		Container panel = getContentPane();
		final JTextField n1   = new JTextField(5);
		final JTextField n2   = new JTextField(5);
		final JTextField n3   = new JTextField(5);
		JButton    ok   = new JButton("Calcular");
		final JTextArea  result = new JTextArea(30,30);
		
		
		panel.setLayout(new FlowLayout());
		panel.add(n1);
		panel.add(n2);
		panel.add(n3);
		panel.add(ok);
		panel.add(result);
		
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					String altura = n1.getText();
					String peso = n2.getText();
					String descr;
					
					double[] imc = new double[4];
					imc = new ClientSocket().imc(Double.parseDouble(altura), Double.parseDouble(peso));
					
					if(imc[1] == 1.0){
						descr = "Abaixo do Peso";
					}else if(imc[1] == 2.0){
						descr = "Acima do Peso";
					}else{
						descr = "Normal";
					}
					
					result.append("Seu imc ficou em: ......" + imc[0] + "\n");
					result.append("Você é considerado uma pessoa: " + descr + "\n");
					result.append("Para sua altura seu peso ideal deve ficar entre: " + imc[2] + " e " + imc[3] + " kg.");
				
					/*	area.append("Media: " + media[0]);
					area.append("\nVariância: " + media[1]);
					area.append("\nDesvio Padrão: " + media[2]);*/
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		 ClientTest frame = new ClientTest();
		 frame.setSize(400,300);
		 frame.setVisible(true);
		 
		 frame.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		} );
	}

}
