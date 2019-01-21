package serveur;


import connection.Database;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;


 

public class Serveur {
	 private int port =4000;
	    private ServerSocket serveur = null;
	    private boolean isRunning = true;
	    public Serveur(){
	        try{
	        	serveur = new ServerSocket(port, 100, InetAddress.getLocalHost());
	            System.out.println("Le serveur"+InetAddress.getLocalHost()+ "ecoute sur le port" + port);
	        } catch (UnknownHostException e){
	            e.printStackTrace();
	        } catch (IOException e1){
	            e1.printStackTrace();
	        }
	    }
	    // Ouverture du serveur
	    public void open(){

	    	 Thread t = new Thread(new Runnable() {

		            public void run() {
		                while (isRunning){
		                    try{
		                    	System.out.println("Socket accepted");
		                        Socket sock = serveur.accept();
		                        Thread t = new Thread(new serveur.ServeurOperation(sock,Database.getConnection()));
		                        t.start();
		                    }catch(IOException e){
		                        e.printStackTrace();
		                    } catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                }
		                try{
		                    serveur.close();
		                }catch (IOException e){
		                    e.printStackTrace();
		                }
		            }
		        });
		        t.start();
		    }

	        
	    
	    //methode pour fermer le serveur
	    public void close(){
	        isRunning = false;
	    }
	    
	    public Connection newConnection() throws SQLException {
	        return Database.getConnection();
	    }
}
