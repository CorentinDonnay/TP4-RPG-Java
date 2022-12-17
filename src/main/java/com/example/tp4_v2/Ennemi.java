package com.example.tp4_v2;

public class Ennemi {
    protected int pointsDeVie;
    protected int degatsArme;
    protected String chemin;


    public int recupPointsDeVie() {
        return pointsDeVie;
    }

    public void reduirePointsDeVie(int n) {
        pointsDeVie = pointsDeVie - n < 0 ? 0 : pointsDeVie - n;
    }

    public String recupChemin() {
        return chemin;
    }

    public boolean attaque(Hero hero) {
        int degats = degatsArme * (1-(hero.recupArmure()/100));

        if(hero.estDefense()){
            degats = (int) (degatsArme *0.7);
        }

        hero.reduirePointsDeVie(degats);
        return true;
    }
}