package dao;

import personne.Magasin;
import personne.Personne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagasinDao extends Dao<Magasin> {
    public MagasinDao(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(Magasin obj) {
        return false;
    }

    @Override
    public boolean delete(Magasin obj) {
        return false;
    }

    @Override
    public boolean update(Magasin obj) {
        return false;
    }

    @Override
    public Magasin find() {
        return null;
    }

    @Override
    public Personne find(String nom) {
        return super.find(nom);
    }
    public ArrayList<Magasin> findMagasinFromType(String type){
        ArrayList<Magasin> liste = new ArrayList<>();
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM magasin Where typeMagasin like '%" + type + "%'order by etageEmpl,numEmpl");
            while (result.next()) {
                System.out.println("client found");
                Magasin p = new Magasin(result.getString("nomMagasin"),result.getString("typeMagasin"));
                liste.add(p);
            }
            return liste;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
