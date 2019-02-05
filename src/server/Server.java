package server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

//import tmp de database
import connexion.Database;
import dao.PersonneDao;
import personne.Personne;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = new ServerSocket(2001);
		System.out.println("Le serveur ecoute sur le port 2001 :");
		
		try {
			
		
		while (true) {
			Socket socket= serverSocket.accept();
			startHandler(socket);
		}
	} finally {
		serverSocket.close();
		
	}
	}

	private static void startHandler(final Socket socket) throws IOException{
		Thread thread = new Thread() {
			public void run () {
				
				Boolean running = true;
				try {
					Connection database = Database.getConnection();
					OutputStreamWriter ecrire = new OutputStreamWriter (socket.getOutputStream(), "UTF-8");
					BufferedReader lire = new BufferedReader(new InputStreamReader (socket.getInputStream(), "UTF-8"));	
					while(running) {
						PersonneDao personneDao = new PersonneDao(database);
						System.out.println("bocucle");
						String line= lire.readLine();
						System.out.println("server recieved 1 " + line);
						if(line.equals("insertionClient")) {
							String client = lire.readLine();
							System.out.println("server recieved 2 " + client);
							//ici on a le nom du client
							Personne pers = new Personne(client);
							personneDao.create(pers);
							
						}else {
							JSONObject jsonObject = new JSONObject(line);
							ecrire.write(jsonObject.toString() + "\n");
							ecrire.flush();
						}
					}
				} catch (IOException | JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeSocket();
				}
				
			
			
		}
		
		private void closeSocket() {
			try {
			socket.close();
		} catch (IOException e) {
	}
		}
		};
		thread.start();
	}
}


