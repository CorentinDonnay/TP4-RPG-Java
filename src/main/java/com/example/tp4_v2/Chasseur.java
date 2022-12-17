package com.example.tp4_v2;;

public class Chasseur extends Hero {


    private int baseArrow = 10;
    private int arrow;


    public Chasseur() {
        this.arrow = baseArrow;
        chemin ="hunter";
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public void resetArrow(){
        this.arrow = baseArrow;
    }



    @Override
    public boolean attaque(Ennemi ennemi) {
        if(arrow>0){
            arrow-=1;

            ennemi.reduirePointsDeVie(degatsArme);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de flèches pour attaquer, Veuillez réessayer");
            return false;
        }
    }



    public int getArrow() {
        return arrow;
    }

    @Override
    public void ajoutFlechesOuMana() {
        baseArrow+=10;
    }
}
