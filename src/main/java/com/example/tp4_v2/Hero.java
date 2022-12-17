package com.example.tp4_v2;;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {

    public int recupBonusConsommable() {
        return consommableBonus;
    }

    public int recupArmure() {
        return armure;
    }

    public void ajoutArmure() {
        armure = (armure >= armureMax ? armureMax : armure +10);
    }

    final int pointsDeVieBasique = 70;
    boolean defense;
    protected int pointsDeVie = pointsDeVieBasique;

    public void changerDefense(boolean defence) {
        this.defense = defence;
    }

    public boolean estDefense() {
        return defense;
    }

    public String recupChemin() {
        return chemin;
    }

    private int armure;
    protected String chemin;

    protected int degatsArme = 29;

    public void changerDegatsArme(int degats) {
        this.degatsArme = degats;
    }

    int maxVie = 100;
    final int ajoutEffetConsommable = 23;
    int consommableBonus;

    private List<Potion> potions = new ArrayList();
    private List<Nourriture> lembas = new ArrayList();
    private int consomableNombre = 3;
    private int armureMax = 40;


    public List<Potion> recupPotions() {
        return potions;
    }

    public List<Nourriture> recupNourriture() {
        return lembas;
    }

    public int recupDegatsArme() {
        return degatsArme;
    }


    public abstract void ajoutFlechesOuMana();

    public void ajoutNombreConsommable() {
        consomableNombre +=1;
    }

    public void ajoutEffetConsommable() {
        consommableBonus += ajoutEffetConsommable;
    }

    public void changerVie(int lifePoints) {
        this.pointsDeVie = Math.min(lifePoints, maxVie);
    }

    public int recupVie() {
        return pointsDeVie;
    }

    public Hero() {
        resetConsommable();
    }

    public void resetConsommable() {

        potions = new ArrayList<>();
        lembas = new ArrayList<>();

        for(int i = 0; i< consomableNombre; i++){
            potions.add(new Potion());
            lembas.add(new Nourriture());
        }
    }

    public void reduirePointsDeVie(int n){
        pointsDeVie = Math.max(pointsDeVie - n, 0);

    }

    public void ajoutPointsDeVie(int n){
        pointsDeVie = Math.min(pointsDeVie + n, maxVie);
    }

    public abstract boolean attaque(Ennemi ennemi);

    public void defendre(){
        changerDefense(true);
    }

    public boolean utiliserConsommable(int choix){
        if(!potions.isEmpty() && choix==1){
            changerVie(pointsDeVie +potions.get(0).getValue()+ consommableBonus);
            potions.remove(0);
            return true;
        }
        else if (!lembas.isEmpty() && choix==2){
            changerVie(pointsDeVie +lembas.get(0).getValue()+ consommableBonus);
            lembas.remove(0);
            return true;
        }
        else{
            return false;
        }
    }



    public void ajoutDegats() {
        degatsArme +=17;
    }


    public void resetPointsDeVie() {
        pointsDeVie = pointsDeVieBasique;
    }

    public void resetDefense() { defense = false; }


}
