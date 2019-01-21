package socket;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pojo.Personne;


public class TestCreateSocket extends AbstractSocket{
	public TestCreateSocket(String nom){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        Personne p = new Personne(nom);
        
        String json = gson.toJson(p);
        try {
            Socket s = new Socket(InetAddress.getLocalHost(),4000);
            PrintWriter w1 = new PrintWriter(s.getOutputStream(), true);
            BufferedInputStream b2 = new BufferedInputStream(s.getInputStream());
            //On previent le serveur d'inserer le nom ds la BD
            String demand = "ll";
            w1.write(demand);
            w1.flush();
            //we wait for server's response
            String reponse = read(b2);
            System.out.println(reponse);
            //On envoie au serveur le message en json avec le nom à ajouter
            w1.write(json);
            w1.flush();

            String retourServer = read(b2);
            System.out.println(retourServer);
            JFrame fenResp = new JFrame();
            JPanel containerResp = new JPanel();
            fenResp.setSize(600, 300);
            fenResp.setLocationRelativeTo(null);
            JLabel jlabResp = new JLabel(retourServer);
            containerResp.add(jlabResp, BorderLayout.CENTER);
            fenResp.setContentPane(containerResp);
            fenResp.setVisible(true);
            s.close();
        }catch(IOException e){ }
    }
}
