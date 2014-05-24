package Widget;
/**
 * In ogni classe importare sempre java.awt.event
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.*;

import org.json.JSONException;

public class PanelExam extends JPanel {
	/* Oggetti necessari al pannello */
	String user;
	String password;
	call_json_api j1; //Creo un oggetto di classe call_json_api per sicurezza poiche' le credenziali vengono impostate dopo il login
	Alert alert=new Alert();
	String risultato=null;
	
	public PanelExam(){
		
		
		/* Creo un Oggetto di Layout */
		GridLayout layout=new GridLayout(5,1);
		
		
		/* Bottoni necessari per la gestione della carriera universitaria */
		JButton exam_given=new JButton ();
		JButton study_plan=new JButton();
		JButton exam_booked=new JButton();
		JButton exam_booking=new JButton();
	
		
		/* Size dei bottoni */
		exam_given.setSize(20,20);
		study_plan.setSize(20,20);
		exam_booked.setSize(20,20);
		exam_booking.setSize(20,20);
		
		exam_given.setText("Esami Sostenuti");
		exam_booked.setText("Esami Prenotati");
		exam_booking.setText("Esami Prenotabili");
		study_plan.setText("Piano di Studi");
		
	

		/* Azione che si verifica all'evento di un bottone */
		
		exam_booking.addActionListener(new ActionListener(){
			
			
				public void actionPerformed(ActionEvent e){
					
					// Sono presenti molti costrutti try-cath poiche' durante la chiamata dei metodi possono esserci malfunzionamenti
					try {
						// Istanzio l'oggetto j1
						
						j1=new call_json_api(user,password);
					} catch (MalformedURLException e2) {
						e2.printStackTrace();
					}
					try {
						// imposto il tipo di api da richiamare
						j1.set_api_booking_exam();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					String result=null;
					try {
						
						//Eseguo la chiamata alle api e il risultato lo concateno in result
						result=j1.exec_booking_exam();
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					
					alert.show_alert(result);
					/** DEBUG
					System.out.print(result);
					*/

				}
			
		});
		exam_given.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					j1=new call_json_api(user,password);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				try {
					j1.set_api_given_exam();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				String result=null;
				try {
					result=j1.exec_given_exam();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				alert.show_alert(result);
				
				
			}
			
		});
		study_plan.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					j1=new call_json_api(user,password);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				try {
					j1.set_api_study_plan();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				String result=null;
				try {
					result=j1.exec_study_plan();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				alert.show_alert(result);
			}
			
		});
		exam_booked.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					j1=new call_json_api(user,password);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				try {
					j1.set_api_booked_exam();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				String result = null;

				try {
					result=j1.exec_booked_exam();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				/** DEBUG
				 * System.out.print(result);
				 */
				alert.show_alert(result);
				
			}
			
		});
		/* Imposto il Layout del Pannello , Ricordo non e' definito l'oggetto prima di setLayout (oggetto.setLayout) perche' questa classe
		 * e' un'estensione di JPanel
		 */
		setLayout(layout);
		
		
		
		add(exam_booking,BorderLayout.SOUTH);
		add(exam_booked,BorderLayout.EAST);
		add(study_plan);
		add(exam_given);
		setVisible(false);
		
		
	}
	void setUser(String user){
		this.user=user;
	}
	
	void setPassword(String password){
		this.password=password;
	}

}
