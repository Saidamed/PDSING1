package serveur;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
//import java.sql.SQLException;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connection.Database;
import dao.*;
import pojo.*;


// Classe avec sockets et operations avec la BD 
 
public class ServeurOperation implements Runnable {
	
	private Socket sock;
	private PrintWriter writer = null;
	private BufferedInputStream reader=null;
	

	public ServeurOperation(Socket s, Connection connection2){
		
		this.sock=s;

	}

	@Override
	public void run() {
		//boolean closeConnection = false;
		while(!sock.isClosed()) {
			try {
				writer = new PrintWriter(sock.getOutputStream());
				reader = new BufferedInputStream(sock.getInputStream());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String demand = read();
				switch(demand){
				case "ll":
					//le serveur comprend la demande
					String toSend = "OK for insert";
					//Le serveur attend de revoir le nom
					writer.write(toSend);
					writer.flush();
					//the server lit la donnée
					String request = read();
					Personne p = gson.fromJson(request, Personne.class);
					PersonneDAO Insererpers = new PersonneDAO(Database.getConnection());
					
					Insererpers.create(p);
						String reponseServ = "" + p.getNom()+ " est ajouté";
						writer.write(reponseServ);
						writer.flush();
					
				}
			}catch (IOException /**| SQLException*/ e){
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	// Cette methode lis le message du client
	 
	private String read() throws IOException {
		String response ="";
		int stream;
		byte[] b = new byte[4096];
		stream = reader.read(b);
		response = new String(b, 0, stream);
		return response;
	}
	
}