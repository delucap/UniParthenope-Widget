package Widget;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Alert {
	
	void show_dialog_login_failed()
    {
	 JPanel pannello=new JPanel();
	 
	 /* Creo il layout 2 righe e 1 colonna */
	 GridLayout layout=new GridLayout(2,1);
	 
	 /* Imposto il layout del pannello che contiene gli oggetto del DialogBox */

	 pannello.setLayout(layout);
	 
	 final JDialog dialog_box=new JDialog();
	 
		JButton ok=new JButton();
		ok.setText("OK");
		ok.setSize(20,20);
		ok.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            dialog_box.setVisible(false);
			        }
			    }
			);
		
		JLabel label1=new JLabel();
		label1.setText("Attenzione c'e' qualcosa che non va\n Controlla le credenziali!");
		
		pannello.add(label1,BorderLayout.CENTER);
		pannello.add(ok,BorderLayout.CENTER);
		
		dialog_box.setLocationRelativeTo(null);
		dialog_box.add(pannello);
		dialog_box.setUndecorated(true);
		dialog_box.pack();
		dialog_box.setVisible(true);
		
		}

	void show_dialog_login(String nome,String cognome)
    {
	 JPanel pannello=new JPanel();
	 
	 final JDialog dialog_box=new JDialog();
	 
	 GridLayout layout=new GridLayout(2,1);
	 
	 	pannello.setLayout(layout);
		JButton ok=new JButton();
		ok.setText("OK");
		ok.setSize(20,20);
		ok.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            dialog_box.setVisible(false);
			            
			        }
			    }
			);
		
		JLabel label1=new JLabel();
		label1.setText("Benvenuto "+nome+" "+cognome+"!");
		
		pannello.add(label1,BorderLayout.CENTER);
		pannello.add(ok,BorderLayout.CENTER);
		
		dialog_box.setLocationRelativeTo(null);
		dialog_box.add(pannello);
		dialog_box.setUndecorated(true);
		dialog_box.pack();
		dialog_box.setVisible(true);
		}
	
	void show_dialog_missing_param()
    {
	 JPanel pannello=new JPanel();
	 
	 final JDialog dialog_box=new JDialog();
	 
		JButton ok=new JButton();
		ok.setText("OK");
		ok.setSize(20,20);
		ok.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            dialog_box.setVisible(false);
			        }
			    }
			);
		
		JLabel label1=new JLabel();
		label1.setText("Attenzione uno dei due campi e' vuoto!");
		
		pannello.add(label1,BorderLayout.CENTER);
		pannello.add(ok,BorderLayout.CENTER);
		
		dialog_box.setLocationRelativeTo(null);
		dialog_box.add(pannello);
		dialog_box.setUndecorated(true);
		dialog_box.pack();
		dialog_box.setVisible(true);
		
		}
	
	void show_alert(String text){
		 final JDialog dialog_box=new JDialog();
		 
			JButton ok=new JButton();
			ok.setText("OK");
			
			ok.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            dialog_box.setVisible(false);
				        }
				    }
				);
			
			 JLabel label=new JLabel(text);

			
			dialog_box.add(label,BorderLayout.CENTER);
			dialog_box.add(ok,BorderLayout.SOUTH);
			dialog_box.setUndecorated(true);
			dialog_box.pack();
			dialog_box.setVisible(true);
			dialog_box.setLocationRelativeTo(null); // Centra la finestra

	}

}
