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
import com.*;


public class Client {

	public static void main(String[] args) throws IOException, JSONException {
		Socket socket = new Socket("localhost", 2001);
		System.out.println("Connecté au serveur");
		OutputStreamWriter ecrire = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
		BufferedReader lire = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		Scanner sc = new Scanner(System.in);
		Boolean insert = true;
		while (insert) {
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
		}

	}
}
