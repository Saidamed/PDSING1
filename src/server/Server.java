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
import java.util.ArrayList;

import com.google.gson.*;

//import tmp from database
import connexion.Database;
import dao.MagasinDao;
import dao.PersonneDao;
import personne.Magasin;
import personne.Personne;
import personne.Profile;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = new ServerSocket(2001);
		System.out.println("Le serveur ecoute sur le port 2001 :");
		Database db = new Database(2);
		System.out.println("il y a "+db.getNbConnection()+" connexion(s) disponible(s)");
		try {

			while (true) {
				Socket socket = serverSocket.accept();
				if(db.getPool().size()!=0) {
					
				
				startHandler(socket, db);
			
		} else {
			System.out.println("une tentative de connexion echou?e ");
			socket.close();
		}
			
			
			

		}
		}
			finally {
				
				serverSocket.close();
			}
		
	}

	private static void startHandler(final Socket socket,final Database db) throws IOException {
		Thread thread = new Thread() {
			public void run() {
				final GsonBuilder builder = new GsonBuilder();
				final Gson gson = builder.create();
				Boolean running = true;
				Connection database = null;
				try {
					database = db.getConnection();
					OutputStreamWriter ecrire = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
					BufferedReader lire = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					System.out.println("connexion(s) utilis?e(s) :  " + db.getPoolInUse().size());
					System.out.println("il y a " + db.getPool().size() + " connexion(s) disponible(s)");
					PersonneDao personneDao = new PersonneDao(database);
					MagasinDao magasinDao = new MagasinDao(database);
					while (running) {


						String line = lire.readLine();
						System.out.println(line);
						if (line.equals("insertionduClient")) {
							String client = lire.readLine();
							Personne pers = new Personne(client,"",1);
							personneDao.create(pers);
							System.out.println("personne cr??e :  " + pers.getNom());

						}else if (line.equals("getClientfrom")){
							System.out.println("getClient");
							String query = lire.readLine();
							System.out.println(query);
							String res = gson.toJson(personneDao.findlike(query));
							ecrire.write(res+"\n");
							ecrire.flush();
						}else if (line.equals("geParcours")){
							ArrayList<ArrayList<Magasin>> parcours = new ArrayList<>();
							ArrayList<Magasin> global = new ArrayList<>();
							System.out.println("getparcours");
							String query = lire.readLine();
							System.out.println(query);
							//recuperation des profils du client
							ArrayList<Profile> listeP = personneDao.getProfilFromID(query);
							//pour chaque profil, selection des magasin ayec le meme type
							for(Profile p : listeP){
								ArrayList<Magasin> temp = magasinDao.findMagasinFromType(p.getName());
								parcours.add(temp);
								//tri des parcours pour fournir un parcours global (en fonction de tous les profils de l'utilisateur)
								for(Magasin m : temp){
									if(!global.contains(m)){
										//ajout d'une contrainte modifiable par la suite


										//global.add(m);
									}
								}
							}
							ecrire.write(gson.toJson(parcours)+"\n");
							ecrire.write(gson.toJson(global)+"\n");
							ecrire.flush();
						}
						else {

							ecrire.write("nothing\n");
							ecrire.flush();

						}
					}
				} catch (IOException e) {
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