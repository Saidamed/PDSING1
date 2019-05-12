package views;

import personne.Magasin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ParcoursRenderer extends JList<Magasin> implements ListCellRenderer<ArrayList<Magasin>>{
    DefaultListModel<Magasin> listforView;
    TitreMagasin titre;
    @Override
    public Component getListCellRendererComponent(JList<? extends ArrayList<Magasin>> list, ArrayList<Magasin> value, int index, boolean isSelected, boolean cellHasFocus) {
        //création du model
        //pour ajouter les valeur dans la SubJList
        this.listforView = new DefaultListModel<Magasin>();
        this.titre = new TitreMagasin("Parcours :"+(index+1));
        listforView.addElement(this.titre);
       for(Magasin i: value)
        {
            listforView.addElement(i);
        }
        this.setModel(listforView);
        //verification de la derniere liste

        return this;
    }

    public ParcoursRenderer(){
        setOpaque(true);
    }
}