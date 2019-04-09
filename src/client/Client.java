package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

import com.google.gson.*;
import views.Parcours;


public class Client {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("l92.168.20.13",904);
		System.out.println("Connect? au serveur");
		OutputStreamWriter ecrire = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
		BufferedReader lire = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		Scanner sc = new Scanner(System.in);
		Boolean insert = true;
		Parcours p = new Parcours(ecrire,lire);
		p.init();
		//lancement ihm avec reader et writer
		/*while (insert) {
			System.out.println("Veuillez saisir le nom du client :");
			String str = sc.nextLine();
			System.out.println("nom : " + str);
			if (str.equals("\\q")) {
				insert = false;
				System.out.println("bye"); 
			} else {
				// send a message to the server in a json object
				JSONObject jsonObject = new JSONObject("{client:" + str + "}");
				ecrire.write("insertionduClient\n");
				ecrire.flush();
				ecrire.write(jsonObject.toString() + "\n");
				ecrire.flush();
				 
			}
		}*/

	}
}
