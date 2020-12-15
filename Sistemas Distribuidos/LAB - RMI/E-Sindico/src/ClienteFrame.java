import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteFrame extends JFrame{


	    private JPanel contentPane;
	    private JTextField textField;

	    public ClienteFrame(SindicoListener sindico) {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //setBounds(250, 250, 566, 242);
	        //contentPane = new JPanel();
	        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        //contentPane.setLayout(new BorderLayout(0, 0));
	        //setContentPane(contentPane);
	        Container panel = getContentPane();
			
	    //    JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout());
			
	    //    contentPane.add(panel, BorderLayout.CENTER);
	   //     panel.setLayout(null);
	        
	        //area pra colocar o valor para saque/ deposito
	        textField = new JTextField();
	       // textField.setBounds(54, 104, 332, 54);
	        panel.add(textField);
	        textField.setColumns(7);
	      //  double valor = Double.parseDouble(textField.getText());
	        
	        
	        JButton btnConecta = new JButton("Conectar");
	        btnConecta.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                Cliente.Conecta(sindico);		// conecta com o servidor
	            }
	        });
	        //btnConecta.setBounds(182, 49, 89, 23);
	        panel.add(btnConecta);
	        
	        
	        JButton btnSaque = new JButton("Saque");
	        btnSaque.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {

	           //     sindico.saque(valor); // Passa o valor para a função saque
	            }
	        });
	        //btnSaque.setBounds(410, 110, 89, 23);
	        panel.add(btnSaque);

	        JButton btnDeposito = new JButton("Deposito");
	        btnDeposito.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {

	             //   sindico.deposito(valor); // Passa o valor para a função saque
	            }
	        });
	        //btnDeposito.setBounds(450, 110, 89, 23);
	        panel.add(btnDeposito);
	        
	        final JTextArea  result = new JTextArea(30,30);

			panel.add(result);

	        
	        
	    }
	}