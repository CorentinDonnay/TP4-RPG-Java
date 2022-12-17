package com.example.tp4_v2;;

public class Mage extends LanceurSorts {
    public Mage() {
        chemin ="mage";
    }


    @Override
    public boolean attaque(Ennemi ennemi) {
        if(pointsMana >diminuerMana){
            pointsMana = pointsMana - diminuerMana;
            ennemi.reduirePointsDeVie(degatsArme);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de mana pour attaquer, Veuillez réessayer");
            return false;
        }
    }
}
