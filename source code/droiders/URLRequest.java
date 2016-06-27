package client.urlrequest;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//Questa classe è la superclasse che permette di comunicare con il server, il metodo execute() imposta la chiamata al server basandosi sulle variabili istanziate dal costruttore. Le sue sottoclassi semplicemente creano il body e impostano le variabili a seconda delle necessità tramite il costruttore di URLRequest. Il metodo execute() sarà usato da RequestMaker all'interno delle chiamate effettuate.

//NOTA: per le callback bisogna capire come funzionano i Listener di volley, quindi probabilmente non servira' implementarle ma solo gestirle

class URLRequest {
	private final Request.Method httpMethod; //da verificare se cosi' funziona, altrimenti bisogna capire se puo' funzionare in un altro modo, male che vada si fa un enum usando uno switch nell'execute()
	private final String url; //contiene l'url per la chiamata al server
	private final Map<String, String> headers; //contiene gli headers per la chiamata al server
	private final JSONObject body; //da verificare se effettivamente useremo questo tipo di oggetto
	private final URLRequestCallback callback; //da verificare se effettivamente useremo questo tipo di oggetto
	private final boolean requiresAuthentication; //true se viene richiesta l'autenticazione, false altrimenti

	void execute() { //effettua la chiamata al server, sarà chiamato da RequestMaker, il tipo di ritorno sara' poi la callback (Listener o del tipo che definiamo noi)
		StringRequest Request = new StringRequest(http.method, url, ) //TODO
	}

	URLRequest(Request.method httpMethod, String url, Map<String, String> headers, JSONObject body, boolean authentication) { //il costruttore inizializza i campi dati. La callback è standard per tutti, quindi non e' richiesta come input per il costruttore
		this.httpMethod=httpMethod; //vengono inizializzati tutti i dati in base a quelli immessi nel costruttore
		this.url=url;
		this.headers=headers;
		this.body=body;
		//inizializzazione della callback
		requiresAuthentication=authentication;
	}
}
