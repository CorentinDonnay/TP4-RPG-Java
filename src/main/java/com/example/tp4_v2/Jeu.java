package com.example.tp4_v2;

import java.util.*;

public class Jeu {

    public static List<Hero> heros = new ArrayList<>();
    public static List<Ennemi> ennemis;

    public static List<Hero> herosRestantsRound;


    public static void main(String[] args) {
        InterfaceGraphiqueMenu.main(args);
    }

    public static void genererEnnemis() {
        genererEnnemis(-1,0);
    }

    public static void genererEnnemis(int taille, int round){
        List<Ennemi> ennemiArrayList = new ArrayList<>();

        if(taille==-1){
            taille = heros.size();
        }

        if (round <5){
            for(int i=0;i<taille;i++) {
                ennemiArrayList.add(new EnnemiBasique());
            }
        }
        else{
            ennemiArrayList.add(new Boss());
        }
        ennemis = ennemiArrayList;
    }





    public static void remiseRecompenses(Hero hero, String choice) {
        int i=0;

        hero.resetPointsDeVie();

        switch (choice) {
            case "A":
                hero.ajoutArmure();
                break;

            case "B":
                hero.ajoutDegats();
                break;

            case "C":
                hero.ajoutEffetConsommable();
                break;
            case "D":
                hero.ajoutNombreConsommable();
                break;
            case "E":
                hero.ajoutFlechesOuMana();
                break;
        }

        if(hero.getClass()== Chasseur.class){
            Chasseur chasseur = (Chasseur) hero;
            chasseur.resetArrow();
        }
        if(LanceurSorts.class.isAssignableFrom(hero.getClass())){
            LanceurSorts lanceurSorts = (LanceurSorts) hero;
            lanceurSorts.resetMana();
        }
        hero.resetConsommable();
    }


    public static void majHerosRestants(Hero hero) {
        herosRestantsRound.remove(hero);
    }


    public static void resetHerosRestants(){
        herosRestantsRound = new ArrayList<>(heros);
    }


    public static void attaqueAleatoire() {
        int aimed;
        Random random = new Random();

        for(int i = 0; i< ennemis.size(); i++){

            aimed = random.nextInt(heros.size());


            ennemis.get(i).attaque(heros.get(aimed));
        }
    }

    public static int actionChoix(Hero heroActuel, String choice) {
        return actionChoix(heroActuel, choice, -1);
    }

    public static int actionChoix(Hero heroActuel, String choice, int aimed) {

        heroActuel.resetDefense();

        switch (choice) {
            case "Attaquer":

                if (heroActuel.getClass() == Soigneur.class) {
                    ((Soigneur) heroActuel).SoigneAttaque(heros,aimed);
                }
                else {
                    heroActuel.attaque(ennemis.get(aimed));
                }

                verifierVie();
                break;


            case "Defendre":
                heroActuel.defendre();
                break;

            case "Potion":
                heroActuel.utiliserConsommable(1);
                break;
            case "Bouffe":
                heroActuel.utiliserConsommable(2);
                break;
        }
        return 0;
    }


    public static void verifierVie() {

        for (int i = 0; i< heros.size(); i++){
            if (heros.get(i).recupVie()==0){
                heros.remove(i);
            }
        }

        for (int j = 0; j< ennemis.size(); j++){
            if (ennemis.get(j).recupPointsDeVie()==0){
                ennemis.remove(j);
            }
        }

        if(heros.isEmpty()){

        }
        else if(ennemis.isEmpty()){

        }

    }

    public static void genererHeros(int nbHealer, int nbHunter, int nbWarrior, int nbMage) {
        heros = new ArrayList<>();

        List<Integer> listNumbers = List.of(nbHealer,nbHunter,nbWarrior,nbMage);

        for(int i=0;i<nbHealer;i++){
            heros.add(new Soigneur());
        }

        for(int i=0;i<nbHunter;i++){
            heros.add(new Chasseur());
        }

        for(int i=0;i<nbWarrior;i++){
            heros.add(new Guerrier());
        }

        for(int i=0;i<nbMage;i++){
            heros.add(new Mage());
        }

        Collections.shuffle(heros);
    }
}
