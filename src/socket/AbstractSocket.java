package socket;

import java.io.BufferedInputStream;
import java.io.IOException;

//Classe abstraite qui definie less methodes que les clients vont utiliser 
public class AbstractSocket {

	    // Methode permet la lecture de ce qu'envoie le serveur au client et retourne un string
	 
	     public String read(BufferedInputStream reader) throws IOException {

	        String response = "";

	        int stream;

	        byte[] b = new byte[4096];

	        stream = reader.read(b);

	        response = new String(b, 0, stream);

	        return response;

	    }
	}
