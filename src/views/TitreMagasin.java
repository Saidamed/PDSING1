package views;

import personne.Magasin;

public class TitreMagasin extends Magasin {
    public TitreMagasin(String name)
    {
        super(name ,"title",0,0);
    }
    @Override
    public String toString(){
        return ""+this.getNom()+":";
    }
}
