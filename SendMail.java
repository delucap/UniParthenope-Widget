package Widget;


import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMail {
	private String username;
	private String password;
	private String destination; //Destinatario
	private	String subject; //Oggetto della mail
	private String body_mail; //Corpo della mail
	Alert alert=new Alert();
	
	//Creo una sessione per l'autenticazione


	public SendMail() {
	
	}
	
	// set nome utente
	public void setUserName(String user){
		this.username=user;
		System.out.println("User impostato\n");
	}
	// set della password 
	public void setPassword(String password){
		this.password=password;
		System.out.println("password impostato\n");

	}
	
	//set del testo della mail
	public void setMessage(String text){
		this.body_mail=text;
		System.out.println("messaggio impostato\n");

	}
	
	// set dell'oggetto
	public void setSubject(String subject){
		this.subject=subject;
		System.out.println("soggetto impostato\n");

	}
	
	public void setDestination(String destination){
		this.destination=destination;
		System.out.println("destinatario impostato\n");

	}
	
	//Metodo per inviare mail
	public void send(){	
	
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(destination));
			message.setSubject(subject);
			message.setText(body_mail);
 
			Transport.send(message);
 
			System.out.println("Fatto");
			alert.show_alert("Mail inviata correttamente");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
