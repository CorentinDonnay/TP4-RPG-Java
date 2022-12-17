package com.example.tp4_v2;;

public class Guerrier extends Hero {

    public Guerrier() {
        chemin ="warrior";
    }

    @Override
    public boolean attaque(Ennemi ennemi) {
        ennemi.reduirePointsDeVie(degatsArme);
        return true;
    }



    @Override
    public void ajoutFlechesOuMana() {
        ajoutDegats();
    }
}
