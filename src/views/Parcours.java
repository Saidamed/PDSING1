package views;

import com.google.gson.reflect.TypeToken;
import personne.Magasin;
import personne.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.*;

public class Parcours extends JFrame {
    private OutputStreamWriter ecrire;
    private BufferedReader lire;
    private ArrayList<Personne> liste;
    private DefaultListModel<Personne> listforView;
    private DefaultListModel<ArrayList<Magasin>> listeModelDroite;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JList<Personne> listeView;
    private JTextField textInput;
    JPanel panelDroite = new JPanel();

    private JList<ArrayList<Magasin>> listeDroite;
    public Parcours(OutputStreamWriter ecrire,BufferedReader lire)
    {
        super("Parcours");
        this.ecrire = ecrire;
        this.lire = lire;
        this.liste = new ArrayList<>();
        this.listforView = new DefaultListModel<>();
    }
    //initialisation de la vue
    public void init(){
        //setSize
        this.setSize(500,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));
        //panel gauche
        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new BorderLayout());
        //input text et liste
        this.textInput = new JTextField(25);
        this.textInput.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e) {
               // System.out.println("input!!!"); tester que l'on ecrit
                String val = textInput.getText();
                Gson gson = new GsonBuilder().create();
                try {
                    if (!val.equals("")) {
                        //on envoi au serveur
                        ecrire.write("getClientfrom\n");
                        ecrire.flush();
                        ecrire.write(val+"\n");
                        ecrire.flush();
                        //recuperation du resultat
                        String res = lire.readLine();
                        liste = gson.fromJson(res,new TypeToken<ArrayList<Personne>>() {}.getType());
                        listforView = new DefaultListModel<Personne>();
                        for(Personne i: liste)
                        {
                            listforView.addElement(i);
                        }
                        listeView.setModel(listforView);
                        System.out.println(res);

                    }
                }catch(IOException exception){
                    exception.printStackTrace();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Personne selected = listeView.getSelectedValue();
                System.out.println(selected);
                Gson gson = new GsonBuilder().create();
                if(selected!=null) {
                    //au clique,envoie d'un message au serveur pour afficher le parcours
                    try {
                        ecrire.write("geParcours\n");
                        ecrire.flush();
                        ecrire.write(selected.getId() + "\n");
                        ecrire.flush();
                        //attente du resultat
                        String res = lire.readLine();
                        ArrayList<ArrayList<Magasin>> parcours = gson.fromJson(res, new TypeToken<ArrayList<ArrayList<Magasin>>>() {}.getType());
                        //ajout du resultat à la vue
                        listeModelDroite = new DefaultListModel<ArrayList<Magasin>>();
                        //for(ArrayList<Magasin> i: parcours)
                        //{
                          //listeModelDroite.addElement(i);
                        //}
                        res = lire.readLine();
                       ArrayList<Magasin> global = gson.fromJson(res,new TypeToken<ArrayList<Magasin>>() {}.getType());
                        listeModelDroite.addElement(global);
                        listeDroite.setModel(listeModelDroite);
                        System.out.println(res);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
        MouseListener mouseListener2 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              panelDroite.removeAll();
                listeDroite = new JList<>();
                listeDroite.setCellRenderer(new ParcoursRenderer());

                panelDroite.add(listeDroite);

                listeModelDroite = new DefaultListModel<ArrayList<Magasin>>();
              //remove(listeDroite);
              repaint();

                System.out.println("jeje");

            }

        };
        //btn deconexion
        //et affichage pour plus tard de la map :)
        this.listeView = new JList<>(this.listforView);
        this.listeView.addMouseListener(mouseListener);
        this.textInput.addMouseListener(mouseListener2);
        this.leftPanel.add(this.textInput,BorderLayout.NORTH);
        this.leftPanel.add(this.listeView,BorderLayout.CENTER);
        //liste de droite
        this.listeDroite = new JList<>();
        this.listeDroite.setCellRenderer(new ParcoursRenderer());

        panelDroite.add(this.listeDroite);
        this.add(this.leftPanel);
        this.add(panelDroite);
        //affichage de la fenetre
        this.setVisible(true);
    }
    //fonction de recherche des noms
    //modifie liste
    private void rechercheName(String name){

    }


}
