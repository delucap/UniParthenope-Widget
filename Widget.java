package Widget;
/**
 * Created by Pasquale De Luca
 * Friday 9 May 2014
 * 
 * UniParthenope Widget v1.0
 * 
 * 
 * 
 * Il Widget e' un'estensione della classe JFrame praticamente vado a ridefinire il costruttore inserendo i miei dati da visualizzare, cosi
 * anche per PanelExam infatti e' un'estensione della classe JPanel. Questa scelta e' dovuta al fatto che il pannello non poteva essere aggiunto
 * al widget se fosse stato un oggetto, ma deve essere un componente J(etc) JPanel JButton ecc.
 */


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;


/* Importo la libreria visuale e estendo la classe JFrame nel main*/
import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

public class Widget extends JFrame  {
	public call_json_api j1;
	public Alert alert =new Alert();
	public JPanel login_panel=new JPanel();
	public JTextField usrname = new JTextField(); 
	public JPasswordField passwd =new JPasswordField();
	private String user;
	private String password;
	public PanelExam panel_exam=new PanelExam();
	public PanelEmail panel_email=new PanelEmail();
	 
		/* Costruttore */
		public Widget(){
			JLabel u1=new JLabel();
			JLabel u2=new JLabel();
			u1.setText("Matricola: ");
			u2.setText("Password:");
			 setLocationRelativeTo(null); // Centra la finestra
			 GridLayout layout=new GridLayout(5,1);
			
			 
			setUndecorated(false); // Impostare a true per eliminare i bordi
			setSize(150,300);
			/** Ex dimensione
			setSize(300,600); //Dimensione del widget
			*/
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setVisible(true); // Variabile booleana che imposta la visibilita del widget
			
			
			/* Pulsante Login */
		 JButton button_login=new JButton();
			button_login.setText("Login");
			button_login.addActionListener(new loginButtonListener());
			
			/* Creo un pannello nel quale inserire gli oggetti necessari al login e con il metodo add li aggiungo nel pannello */
			
			login_panel.add(u1);
			login_panel.add(usrname,BorderLayout.CENTER);
			login_panel.add(u2);
			login_panel.add(passwd,BorderLayout.CENTER);
			login_panel.add(button_login);
			
			
			/* Layout del Pannello Login */
			login_panel.setLayout(layout);
			
			/* Aggiungo i pannelli al frame principale */
			 add(login_panel,BorderLayout.CENTER); // uguale a getContentPane().add(BorderLayout.CENTER,login_panel);
			 add(panel_exam,BorderLayout.NORTH);
			 add(panel_email,BorderLayout.SOUTH);
			 
			setTitle("UniParthenope Widget");
			setVisible(true); // Variabile booleana che imposta la visibilita del widget
			setResizable(false); // non e' possibile modificarne le dimensioni
		
			
		}
	
		public static void main(String[] args){
			new Widget();

			
		}
		
	    public class loginButtonListener implements ActionListener  {  
	        public void actionPerformed(ActionEvent ev){ 
	        	/* Al click del bottone login viene invocato il metodo login() */
	        	try {
					login();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	    }
	    
	    private void login() throws MalformedURLException, JSONException{
	    	if(user==null || password==null){
	    	user=usrname.getText().toString();
	    	password=passwd.getText().toString();
	    	}
	    	
	    	String nome;
	    	String cognome;
	    	String result;
	    	String r;
	    	
	    	/* Instanzio la nuova classe call_json_api */
	    	j1=new call_json_api(user,password);
	    	
	    	/* Imposto l'api che ritorna i dati personali */
	    	j1.set_api_personaldata();
	    	
	    	/* Eseguo la chiamata all'api e assegno il risultato alla variabile result */
	    	result=j1.exec();
	    	
	    	/* Creo un nuovo oggetto JSONObjcet poiche' il valore di result (seguendo la struttura dati json) e' uguale ad una altro oggetto Json */
	    	JSONObject json= new JSONObject(result);
	    	
	    	/* Controllo che sia andata a buon fine la chiamata, controllando il codice ritornato */
	    	r=json.getString("CODE");
	    	if(r.equals("200")){ // Il valore 200 viene messo tra apici perche' sono stringhe
	    		nome=json.getString("NAME");
	    		cognome=json.getString("SURNAME");
	    		alert.show_dialog_login(nome,cognome);
				
				login_panel.setVisible(false);
				
				panel_exam.setUser(user);
				panel_exam.setPassword(password);
				panel_email.setVisible(true);	
				this.add(panel_exam,BorderLayout.NORTH);
				panel_exam.setVisible(true);
				

	    	}
	    	 	
	    	
	    }
	    private static void printf(String texto){
	        System.out.println(texto);
	    }
	    
	   
}
