package com.example.tp4_v2;;

import java.util.List;

public class Soigneur extends LanceurSorts {

    public Soigneur() {
        chemin ="healer";
    }

    public boolean SoigneAttaque(List<Hero> heroes, int numberOfAimedHero) {


        System.out.println(pointsMana);
        if(pointsMana >diminuerMana){
            pointsMana = pointsMana - diminuerMana;
            heroes.get(numberOfAimedHero).ajoutPointsDeVie(degatsArme);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de mana pour soigner, Veuillez réessayer");
            return false;
        }
    }

    @Override
    public boolean attaque(Ennemi ennemi) {
        return false;
    }


}
