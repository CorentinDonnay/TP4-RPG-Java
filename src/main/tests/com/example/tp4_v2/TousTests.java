package com.example.tp4_v2;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;



public class TousTests {

    static List<Hero> renvoyerListeHeros() {
        List<Hero> heroesList = List.of(new Chasseur(), new Soigneur(), new Guerrier(), new Mage());
        return heroesList;
    }

    static List<Hero> renvoyerListeHerosSansHealer() {
        List<Hero> heroesList = List.of(new Chasseur(), new Guerrier(), new Mage());
        return heroesList;
    }


    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesArmure(Hero hero) {

        int AttributBefore = hero.recupArmure();

        Jeu.remiseRecompenses(hero,"A");

        Assertions.assertTrue(hero.recupArmure()>AttributBefore);
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesConsumableEffect(Hero hero) {

        int AttributBefore = hero.recupBonusConsommable();

        Jeu.remiseRecompenses(hero,"C");

        Assertions.assertTrue(hero.recupBonusConsommable()>AttributBefore);
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesConsumableNumber(Hero hero) {

        int AttributBefore = hero.recupPotions().size();
        int AttributBefore2 = hero.recupNourriture().size();

        Jeu.remiseRecompenses(hero,"D");

        Assertions.assertTrue(hero.recupPotions().size()>AttributBefore);
        Assertions.assertTrue(hero.recupNourriture().size()>AttributBefore2);
    }





    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void SiOnPrendPlusDeDegatsQueDePVOnMeurt(Hero hero){


        Jeu.heros = new ArrayList<>();
        Jeu.heros.add(hero);

        Jeu.genererEnnemis();

        Jeu.heros.get(0).reduirePointsDeVie(300);

        Jeu.verifierVie();

        Assertions.assertTrue(Jeu.heros.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("renvoyerListeHerosSansHealer")
    public void attaquerEnnemi(Hero hero){

        Jeu.heros = List.of(hero);

        hero.changerDegatsArme(2147483647);


        Jeu.genererEnnemis(1,0);

        Hero heroActuel = Jeu.heros.get(0);


        Jeu.actionChoix(heroActuel,"Attaquer",0);

        Assertions.assertTrue(Jeu.ennemis.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void defendre(Hero hero){
        Jeu.heros = List.of(hero);

        Jeu.genererEnnemis(1,0);

        Hero heroActuel = Jeu.heros.get(0);

        Jeu.actionChoix(heroActuel,"Defendre",0);

        Assertions.assertTrue(heroActuel.estDefense());
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void consommerTousHeros(Hero hero){
        Jeu.heros = List.of(hero);

        hero.reduirePointsDeVie(50);
        int previousLifePoints = hero.recupVie();


        Jeu.genererEnnemis(1,0);

        Hero heroActuel = Jeu.heros.get(0);

        Jeu.actionChoix(heroActuel,"Potion",1);

        Assertions.assertTrue(heroActuel.recupVie()==previousLifePoints+ new Potion().getValue());
    }



    @Test
    public void SoigneHeros(){
        Soigneur soigneur = new Soigneur();
        Jeu.heros = List.of(soigneur);

        int hpDebut = Jeu.heros.get(0).recupVie();
        System.out.println(hpDebut);
        soigneur.reduirePointsDeVie(1);

        soigneur.SoigneAttaque(Jeu.heros,0);
        System.out.println(soigneur.recupVie());


        Assertions.assertTrue(hpDebut< soigneur.recupVie());

    }




    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void EnnemiAttaqueHeros(Hero hero){

        Jeu.heros = new ArrayList<>();
        Jeu.heros.add(hero);


        Jeu.genererEnnemis(1,0);


        for (int i=0;i<7;i++){
            Jeu.attaqueAleatoire();
            System.out.println("Game heroes health : " + Jeu.heros.get(0).recupVie());
        }
        Jeu.verifierVie();

        Assertions.assertTrue(Jeu.heros.isEmpty());
    }

    @Test
    public void ManaManquantAucuneAttaque(){
        Mage mage = new Mage();
        mage.changerPointsMana(0);
        Assertions.assertTrue(!mage.attaque(new Ennemi()));
    }

    @Test
    public void FlecheManquanteAucuneAttaque(){
        Chasseur chasseur = new Chasseur();
        chasseur.setArrow(0);
        Assertions.assertTrue(!chasseur.attaque(new Ennemi()));
    }

    @Test
    public void FlecheDiminue(){
        Chasseur chasseur = new Chasseur();

        int flecheDebut = chasseur.getArrow();
        chasseur.attaque(new Ennemi());
        Assertions.assertTrue(chasseur.getArrow()==flecheDebut-1);
    }

    @Test
    public void PotionDonneDeLaVieHunter(){

        Chasseur chasseur = new Chasseur();
        int vieAvant = chasseur.recupVie();
        System.out.println(vieAvant);

        chasseur.utiliserConsommable(1);
        System.out.println(chasseur.recupVie());

        Assertions.assertTrue(vieAvant< chasseur.recupVie());
    }

    @Test
    public void PotionDonneDeLaVieCaster(){

        Soigneur soigneur = new Soigneur();
        int vieAvant = soigneur.recupVie();
        System.out.println(vieAvant);

        soigneur.utiliserConsommable(1);
        System.out.println(soigneur.recupVie());

        Assertions.assertTrue(vieAvant< soigneur.recupVie());
    }


    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesDegats(Hero hero) {

        int dmgBefore = hero.recupDegatsArme();

        Jeu.remiseRecompenses(hero,"B");

        Assertions.assertTrue(hero.recupDegatsArme()>dmgBefore);
    }

    @Test
    public void PotionDonneDeLaVieWarrior(){
        Guerrier guerrier = new Guerrier();
        guerrier.changerVie(20);
        int vieAvant = guerrier.recupVie();
        System.out.println(vieAvant);

        guerrier.utiliserConsommable(1);
        System.out.println(guerrier.recupVie());

        Assertions.assertTrue(vieAvant< guerrier.recupVie());
    }
}
