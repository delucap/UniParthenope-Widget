package Widget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/** VENGONO CREATI piu' oggetti JSON perche' la struttura dati delle api e' oggetti e non array (vedi json.org) */


public class call_json_api {
	URL url;
	public String matricola ;
	public String password;
	public String output;
	
	public Alert alert =new Alert();

	
	public  call_json_api(String matricola,String password) throws MalformedURLException{
		
		//il this sta ad indicare la variabile della classe e non quella passata come parametro
		this.matricola=matricola;
		this.password=password;
	}
	
	public String exec(){
        try{
            //make connection
            URLConnection urlc = url.openConnection();
            
            //It Content Type is so important to support JSON call
            urlc.setRequestProperty("Content-Type", "application/xml");
            printf("Connessione in corso con: " + url.toString());
            
            //use post mode
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //send query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
          
            ps.close();

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            
            
            
            while ((l=br.readLine())!=null) {
                /** la variabile output serve per acquisire l'input dello stream ricevuto dall'api (in questo caso OUTPUT perche' e' un dato che serve all'utente) */
            	//Questo controllo e' presente perche' durante l'aquisizione del buffer venive acquisito anche il testo "null" quindi se viene trovato il testo null allora quello sara' sostituito con \b che e' backspace cioe' DELETE
            	if(output==null)
            		output="\b";
            	//Concateno il risultato del buffer nella stringa output
            	output+=l; 
                	// Prova di stampa del buffer ricevuto mano mano -> printf(l);
              
            }
            
            //Chiudo il Buffer di lettura
            br.close();
            
	}
	    catch (Exception e){
	    	alert.show_dialog_login_failed();
	    	// Queste sono righe necessarie per il debug
           //printf("Errore: ");
           //printf(e.toString());
       }
		return output;

	}

    private static void printf(String texto){
        System.out.println(texto);
    }
    
    public void set_api_personaldata() throws MalformedURLException{
		URL url_r=new URL("https://api.uniparthenope.it/UniService/"+matricola+"/personaldata?password="+password);
    	this.url=url_r;    	
    }
    
    public void set_api_given_exam() throws MalformedURLException{
		URL url_exam_given=new URL("https://api.uniparthenope.it/UniService/"+matricola+"/exams/given?password="+password);
		this.url=url_exam_given;
    }
    
    public void set_api_booking_exam() throws MalformedURLException{
		URL url_exam_given=new URL("https://api.uniparthenope.it/UniService/"+matricola+"/exams/booking?password="+password);
		this.url=url_exam_given;
    }
    
    public void set_api_study_plan() throws MalformedURLException{
		URL url_study_plan=new URL("https://api.uniparthenope.it/UniService/"+matricola+"/studyplan?password="+password);
		this.url=url_study_plan;
    }
    
    public void set_api_booked_exam() throws MalformedURLException{
  		URL url_booked_exam=new URL("https://api.uniparthenope.it/UniService/"+matricola+"/exams/booked?password="+password);
  		this.url=url_booked_exam;
      }
    
    
    public String exec_booking_exam() throws JSONException{
    	exec();
    	
    	JSONObject json=new JSONObject(output);
        JSONObject j1=json.getJSONObject("EXAM_LIST");
        JSONObject j2;
        String number_exam="0";
        String result="<html><h1>ESAMI PRENOTABILI</h1>"; 
        /** i viene dichiarato Integer poiche' la classe mette a disposizione metodi per la trasformazione in String
         * cosi' che la variabilel String number_exam (necessaria per scalare gli esami nelle api) assuma il valore di i
         * ad ogni iterazione
         */
        
        	for(Integer i=0;i<j1.length();i++){
        		number_exam=i.toString();
        		j2=j1.getJSONObject(number_exam);
        	          result+=j2.getString("EXAM");
        	          result+="<br>";
        	}
            result+="<br><br></html>";

		return result;
    }
    
    public String exec_given_exam() throws JSONException{
    	exec();
    	JSONObject json=new JSONObject(output);
        JSONObject j1=json.getJSONObject("EXAMS_LIST");
        JSONObject j2;
        String number_exam="0";
        
        /* La stringa stampata è sottoforma di codice html perchè il codice di nuova riga "\n" non funzionava quindi è stato usato <br> */
        String result="<html><h1>ESAMI SOSTENUTI</h1>"; // Inizializzo il codice html
        
        /** (i) viene dichiarato Integer poiche' la classe mette a disposizione metodi per la trasformazione in String
         * cosi' che la variabilel String number_exam (necessaria per scalare gli esami nelle api) assuma il valore di i
         * ad ogni iterazione
         */
        
        	for(Integer i=0;i<j1.length();i++){
        		number_exam=i.toString();
        		j2=j1.getJSONObject(number_exam);
        	          result+=j2.getString("EXAM_NAME");
        	          result+="<br>";
        	}
        	
        result+="<br><br></html>"; //Chiudo il tag html
		return result;
    }
    
    public String exec_study_plan() throws JSONException{
    	exec();
    	JSONObject json=new JSONObject(output);
        JSONObject j1=json.getJSONObject("EXAMS_LIST");
        JSONObject j2;
        String number_exam="0";
        String result="<html><h1>PIANO DI STUDI</h1>";
        
        /** (i) viene dichiarato Integer poiche' la classe mette a disposizione metodi per la trasformazione in String
         * cosi' che la variabilel String number_exam (necessaria per scalare gli esami nelle api) assuma il valore di i
         * ad ogni iterazione
         */
        
        	for(Integer i=0;i<j1.length();i++){
        		number_exam=i.toString();
        		j2=j1.getJSONObject(number_exam);
        	          result+=j2.getString("TEACHING");
        	          result+="<br>";
        	}
        result+="<br><br></html>";
        
		return result;
    }
    public String exec_booked_exam() throws JSONException{
    	 String result="<html><h1>ESAMI PRENOTATI</h1>";
    	try{
            URLConnection urlc = url.openConnection();
            
            urlc.setRequestProperty("Content-Type", "application/xml");
            printf("Connessione in corso con: " + url.toString());
            
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            PrintStream ps = new PrintStream(urlc.getOutputStream());
          
            ps.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            
            
            
            while ((l=br.readLine())!=null) {
            	//Questo controllo e' presente perche' durante l'aquisizione del buffer venive acquisito anche il testo "null" quindi se viene trovato il testo null allora quello sara' sostituito con \b che e' backspace cioe' DELETE
            	if(output==null)
            		output="\b";
            	output+=l; 
              
            }
            
            //Chiudo il Buffer di lettura
            br.close();
            JSONObject json=new JSONObject(output);
            JSONObject j1=json.getJSONObject("EXAMS_LIST");
            JSONObject j2;
            String number_exam="0";
           
            
            /** i viene dichiarato Integer poiche' la classe mette a disposizione metodi per la trasformazione in String
             * cosi' che la variabilel String number_exam (necessaria per scalare gli esami nelle api) assuma il valore di i
             * ad ogni iterazione
             * 
             */
            
            	for(Integer i=0;i<j1.length();i++){
            		number_exam=i.toString();
            		j2=j1.getJSONObject(number_exam);
            	          result+=j2.getString("TEACHING");
            	          result+="<br>";
            	}
                result+="<br><br></html>";

            
	}
	    catch (Exception e){
	    	result="Nessun esame prenotato";
	    	// Queste sono righe necessarie per il debug
           //printf("Errore: ");
           //printf(e.toString());
       }
		return result;

	}

    }
