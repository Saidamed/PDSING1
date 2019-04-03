package views;

import personne.Magasin;

public class TitreMagasin extends Magasin {
    public TitreMagasin(String name)
    {
        super(name,"title");
    }
    @Override
    public String toString(){
        return ""+this.getNom()+":";
    }
}
