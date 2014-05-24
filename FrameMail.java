package Widget;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrameMail extends JFrame {
	SendMail mail=new SendMail();
	String destination;
	String email;
	String password;
	String message;
	String subject2;
	String[] listaEmail = {
			"Scegli dalla lista",
	         "giulio.giunta@uniparthenope.it",
	         "raffaele.montella@uniparthenope.it",
	         "francesco.camstra@uniparthenope.it",
	         "alessio.ferone@uniparthenope.it",
	         "giuseppe.salvi@uniparthenope.it",
	         "alfredo.petrosino@uniparthenope.it",
	         "pasquale335@gmail.com",
	         "K:mm a,z",
	         "yyyy.MMMMM.dd GGG hh:mm aaa"
	};
	
	public FrameMail(){
		
		/* Field per destinatario e soggetto della mail */
		final JTextField destArea=new JTextField(10);
	      final JTextField subject=new JTextField(10);

	    JPanel chatPanel = new JPanel();
	      
	      chatPanel.add(new JLabel("Email:", SwingConstants.LEFT), BorderLayout.PAGE_START);
	      chatPanel.add(destArea,BorderLayout.CENTER);
	      chatPanel.add(new JLabel("Oggetto:",SwingConstants.LEFT),BorderLayout.CENTER);
	     chatPanel.add(subject);
	    
	      final JTextArea inputField = new JTextArea(8,8);
	      final JComboBox combox =new JComboBox(listaEmail);
	      
	      JButton sendBtn = new JButton("Invia");
	      
	      sendBtn.addActionListener(new ActionListener(){
	    	  
			@Override
			public void actionPerformed(ActionEvent e) {
				
		
				/* Con getSelectedItem.toString estraggo sottoforma di stringa l'elemento selezionato nella comboBox */
				
				/* Controllo se l'elemento selezionato nella lista è ancora il primo allora il destinatario sarà preso da destArea altrimenti verrà impostato dalla comboBox */
				if (combox.getSelectedItem().equals("Scegli dalla lista")){
					destination=destArea.getText().toString();
					System.out.println("Dest impostato da destArea");
				}
				else				{
					
					destination=combox.getSelectedItem().toString();
					System.out.println("dest impostato da combox");
				}
					
				mail.setDestination(destination);
					
					message=inputField.getText().toString();
					mail.setMessage(message);
					
					subject2=subject.getText().toString();
					
					mail.setUserName(email);
					mail.setPassword(password);
					mail.setSubject(subject2);
					
					/* Invio la mail */
					mail.send();
			      
			}
	    	  
	      });
	      
	      
	      JPanel inputPanel = new JPanel();
	      inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
	      

	      inputPanel.add(inputField);
	      inputPanel.add(sendBtn);

	      JButton setemail=new JButton("Set Email");
	      add(combox,BorderLayout.PAGE_START);
	      add(chatPanel,BorderLayout.CENTER);
	     // add(setemail,BorderLayout.SOUTH);
	      add(inputPanel,BorderLayout.SOUTH);
	      

	      
	      setResizable(false); // non e' possibile modificarne le dimensioni	      
	      pack();
	      setVisible(false);
	}
	
	public void setUser(String user){
		this.email=user;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
}
