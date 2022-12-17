package com.example.tp4_v2;;

public abstract class LanceurSorts extends Hero {

    public LanceurSorts() {
        this.pointsMana = manaPointsBase;
        this.diminuerMana = 12;
    }

    protected int manaPointsBase = 90;
    protected int pointsMana;
    protected int diminuerMana;




    public void resetMana() {
        pointsMana = manaPointsBase;
    }

    @Override
    public void ajoutFlechesOuMana() {
        manaPointsBase +=20;
    }

    public void changerPointsMana(int pointsMana) {
        this.pointsMana = pointsMana;
    }

    public abstract boolean attaque(Ennemi ennemi);

    public int getMana() {
        return pointsMana;
    }

}
