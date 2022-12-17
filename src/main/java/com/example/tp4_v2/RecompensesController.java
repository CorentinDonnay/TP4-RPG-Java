package com.example.tp4_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.tp4_v2.Jeu.heros;
import static com.example.tp4_v2.Jeu.remiseRecompenses;
import static com.example.tp4_v2.InterfaceGraphiqueJeuControlleur.heroRecompensesCompteur;

public class RecompensesController {

    @FXML 
    private Label labelHero;

    @FXML
    private ImageView heroImage;
    InterfaceGraphiqueJeuControlleur controller;

    public void setMainController(InterfaceGraphiqueJeuControlleur controller){
        this.controller = controller;
    }

    public void initialize(){
        heroImage.setImage(new Image(getClass().getResourceAsStream("/img/"+ Jeu.heros.get(heroRecompensesCompteur).recupChemin()+".png")));
        labelHero.setText("Hero actuel : "+heroRecompensesCompteur);
    }

    @FXML
    protected void addArmor(ActionEvent event) {
        remiseRecompenses(heros.get(heroRecompensesCompteur),"A");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();


    }
    @FXML
    protected void addDamage(ActionEvent event) {
        remiseRecompenses(heros.get(heroRecompensesCompteur),"B");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();


    }
    @FXML
    protected void addConsommable(ActionEvent event) {
        remiseRecompenses(heros.get(heroRecompensesCompteur),"D");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();


    }
    @FXML
    protected void addConsommableEffect(ActionEvent event) {
        remiseRecompenses(heros.get(heroRecompensesCompteur),"C");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();



    }
    @FXML
    protected void addFlecheouMana(ActionEvent event) {
        Hero hero = heros.get(heroRecompensesCompteur);
        if(hero.getClass()== Chasseur.class || LanceurSorts.class.isAssignableFrom(hero.getClass())){
            remiseRecompenses(heros.get(heroRecompensesCompteur),"E");
            heroRecompensesCompteur+=1;

            loadImageHeroRecompenses();
        }

    }


    private void loadImageHeroRecompenses() {
        if(heroRecompensesCompteur< heros.size()){
            heroImage.setImage(new Image(getClass().getResourceAsStream("/img/"+ Jeu.heros.get(heroRecompensesCompteur).recupChemin()+".png")));
            labelHero.setText("Hero actuel : "+heroRecompensesCompteur);
        }
        else{
            controller.generationJeuUI();
        }
    }


}
