package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Client {

	public static void main(String[] args) throws IOException, JSONException{
		
		Socket socket = new Socket ("localhost", 2001);
		System.out.println("Connecté au serveur");
		OutputStreamWriter ecrire = new OutputStreamWriter (socket.getOutputStream(), "UTF-8");
		BufferedReader lire = new BufferedReader(new InputStreamReader (socket.getInputStream(), "UTF-8"));		
		
		Scanner sc = new Scanner(System.in);
		Boolean insert = true;
		while(insert) {
			System.out.println("Veuillez saisir le nom du client :");
			String str = sc.nextLine();
			System.out.println("clavier : "+str);
			if(str.equals("\\q")) {
				insert = false;
				System.out.println("bye");
			}else {
			//envoi une message au serveur
				ecrire.write("insertionClient\n");
				ecrire.flush();
				ecrire.write(str+"\n");
				ecrire.flush();
			}
		}
		
	
		
		
	}
}
