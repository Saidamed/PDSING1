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

//import tmp from database
import connexion.Database;
import dao.PersonneDao;
import personne.Personne;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = new ServerSocket(2001);
		System.out.println("Le serveur ecoute sur le port 2001 :");
		Database db = new Database(2);
		try {

			while (true) {
				Socket socket = serverSocket.accept();
				startHandler(socket, db);
			}
		} finally {
			serverSocket.close();

		}
	}

	private static void startHandler(final Socket socket, Database db) throws IOException {
		Thread thread = new Thread() {
			public void run() {

				Boolean running = true;
				Connection database = null;
				try {
					database = db.getConnection();
					OutputStreamWriter ecrire = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
					BufferedReader lire = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					while (running) {
						PersonneDao personneDao = new PersonneDao(database);
						System.out.println("boucle");
						String line = lire.readLine();
						System.out.println("server recieved 1 " + line);
						if (line.equals("insertionClient")) {
							String client = lire.readLine();
							JSONObject jsonObject = new JSONObject(client);  // receive the json object 
							String nameClient = jsonObject.getString("client"); // put the json object inside a String nameClient
							System.out.println("server recieved 2 " + nameClient);
							Personne pers = new Personne(nameClient); 
							personneDao.create(pers);
							

						} else {
							JSONObject jsonObject = new JSONObject(line);
							ecrire.write(jsonObject.toString() + "\n");
							ecrire.flush();
						}
					}
				} catch (IOException | JSONException e) {
					// TODO Auto-generated catch block
					db.close(database);
					// e.printStackTrace();
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
