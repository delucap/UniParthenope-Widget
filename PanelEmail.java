package Widget;
/** Pannello princiape (presente in Widget) 
 * per inviare una mail
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PanelEmail extends JPanel {

	FrameMail window_mail=new FrameMail(); // Frame per invio di mail
	 String user=null;
	 String password=null;
	 JFrame setEmail =new JFrame(); // Questo frame viene utilizzato per settare le credenziali della mail

	//Costruttore del pannello
	public PanelEmail(){
		BorderLayout layout=new BorderLayout(); // Layout del pannello
		final Alert alert=new Alert();
		final JTextField email=new JTextField(18);
		final JPasswordField password2=new JPasswordField(18);
		JButton open_frame_mail=new JButton("Invia Mail");

		JButton set=new JButton("Imposta");
		
		/* Impostazioni della finestra di configurazione email (window_mail) */
		setEmail.setSize(20, 20);
		setEmail.setUndecorated(true);
		setEmail.add(password2,BorderLayout.CENTER);
		setEmail.add(email,BorderLayout.NORTH);
		setEmail.add(set,BorderLayout.SOUTH);
		setEmail.pack();
		setEmail.setResizable(false);
		setEmail.setLocationRelativeTo(null);
		
		/* Il bottone "set" viene aggiunto a setEmail (il quale e' un JFrame) per impostare le crendenziali */
			set.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {	
					user=email.getText().toString();
					password=password2.getText().toString();
					
					/* Controllo se uno dei due campi e' vuoto e se lo e' mostro un avviso altrimenti imposto le credenziali */
					
					if(user.equals("") || password.equals("")){
						alert.show_alert("Uno dei due campi è vuoto , inserisci le credenziali di accesso!");
						
					}
					else{
						/* Imposto le credenziali per l'invio delle mail */
						window_mail.setUser(user); // Il metodo set Appartiene alla classe FrameMail e serve per impostare la variabile di username della classe
						window_mail.setPassword(password); // Questo metodo appartiene sempre a FrameMail e serve per impostare la password
						
					alert.show_alert("Credenziali impostate!");
					setEmail.setVisible(false); // Alla fine nascondo window_mail
					
					/** DEBUG
					System.out.print("USER "+user+"\tPassword "+password);
					*/
				}
				}
				
			});
			
			open_frame_mail.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					/* Controllo sempre che uno dei due campi non sia null , in caso contrario verra' aperto il frame setEmail per settare le credenziali */
					if(user==null || password==null){
						setEmail.setVisible(true);
						
						alert.show_alert("Credenziali mail non impostate");

					}
					else					
					window_mail.setVisible(true);
				}
				
		});
	     
			setLayout(layout);
			add(open_frame_mail,BorderLayout.SOUTH);
			setVisible(false);
	   }

}
