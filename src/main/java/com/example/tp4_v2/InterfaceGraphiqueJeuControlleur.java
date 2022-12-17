package com.example.tp4_v2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.tp4_v2.Jeu.*;
import static com.example.tp4_v2.Jeu.ennemis;

import static java.lang.Math.max;

public class InterfaceGraphiqueJeuControlleur {

    @FXML
    int nombreHerosSoigneurValeur = 0;

    @FXML
    int nombreHerosChasseurValeur = 0;

    @FXML
    int nombreHerosGuerrierValeur = 0;

    @FXML
    int nombreHerosMageValeur = 0;

    @FXML
    private Label nombreHealer;
    @FXML
    private Label nombreHunter;
    @FXML
    private Label nombreWarrior;
    @FXML
    private Label nombreMage;


    static Stage stage;

    GridPane root;

    ScrollPane scrollPane;


    private int maximum;


    Text herosSelectionneValeurText;
    Text joueursRestantsText;

    Text degatsValeurText;
    Text potionsValeurText;
    Text nourritureValeurText;
    Text armureValeurText;

    Text joueursRestantsValeurText;
    Text roundValeurText;
    Text tourValeurText;

//    private Hero heroSelectionne;
    int tour = 1;
    int round = 1;
    static int heroRecompensesCompteur;
    public boolean estModeAttaque;

    public void ajoutSoigneurBouton() {
        nombreHerosSoigneurValeur++;
        nombreHealer.setText("Nombre de Soigneurs : " + nombreHerosSoigneurValeur);
    }

    public void diminuerSoigneurBouton() {
        nombreHerosSoigneurValeur = nombreHerosSoigneurValeur >0 ? nombreHerosSoigneurValeur -1 : 0;
        nombreHealer.setText("Nombre de Soigneurs : " + nombreHerosSoigneurValeur);
    }

    public void ajoutChasseurBouton() {
        nombreHerosChasseurValeur++;
        nombreHunter.setText("Nombre de Chasseurs : " + nombreHerosChasseurValeur);
    }
    public void diminuerChasseurBouton() {
        nombreHerosChasseurValeur = nombreHerosChasseurValeur >0 ? nombreHerosChasseurValeur -1 : 0;
        nombreHunter.setText("Nombre de Chasseurs : " + nombreHerosChasseurValeur);
    }
    public void ajoutGuerrierBouton() {
        nombreHerosGuerrierValeur++;
        nombreWarrior.setText("Nombre de Guerriers : " + nombreHerosGuerrierValeur);
    }
    public void diminuerGuerrierBouton() {
        nombreHerosGuerrierValeur = nombreHerosGuerrierValeur >0 ? nombreHerosGuerrierValeur -1 : 0;
        nombreWarrior.setText("Nombre de Guerriers : " + nombreHerosGuerrierValeur);
    }

    public void ajoutMageBouton() {
        nombreHerosMageValeur++;
        nombreMage.setText("Nombre de Mages : " + nombreHerosMageValeur);
    }

    public void diminuerMageBouton() {
        nombreHerosMageValeur = nombreHerosMageValeur >0 ? nombreHerosMageValeur -1 : 0;
        nombreMage.setText("Nombre de Mages : " + nombreHerosMageValeur);
    }

    @FXML
    protected void onPlayButtonClick(ActionEvent event) {
        if(nombreHerosChasseurValeur+nombreHerosGuerrierValeur+nombreHerosSoigneurValeur+nombreHerosMageValeur!=0){
            genererHeros(nombreHerosSoigneurValeur, nombreHerosChasseurValeur, nombreHerosGuerrierValeur,nombreHerosMageValeur);

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            stage = new Stage();
            generationJeuUI();
        }

    }

    public void generationJeuUI() {

        joueursRestantsText = null;
        joueursRestantsValeurText = null;
        armureValeurText  = null;
        roundValeurText  = null;
        tourValeurText  = null;
        herosSelectionneValeurText = null;
        degatsValeurText  = null;
        potionsValeurText  = null;
        nourritureValeurText  = null;

        heroRecompensesCompteur= 0;
        tour = 1;

        genererEnnemis(-1,round);

        maximum = max(ennemis.size(), heros.size());

        resetHerosRestants();


        scrollPane = new ScrollPane();

        root = new GridPane();

//        genererPremiereLigneUI();
        majUI();


        scrollPane.setFitToWidth(true);
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane, InterfaceGraphiqueMenu.SCREEN_SIZE, InterfaceGraphiqueMenu.SCREEN_SIZE);
        stage.setTitle("Jeu RPG");
        stage.setScene(scene);
        stage.show();
    }



    private void verifierHerosRestants() {
        if(herosRestantsRound.isEmpty()){
            tour +=1;
            Jeu.attaqueAleatoire();
            verifierVie();
            resetHerosRestants();
            if(heros.isEmpty()){
                afficherDefaite();
            }
        }
        majUI();
    }


    private void verifierEnnemisRestants() {
        verifierVie();
        if(ennemis.isEmpty() && round < 5){
            afficherRecompenses();
        }
        else if (ennemis.isEmpty() && round >= 5){
            afficherVictoire();
        }
    }


    private void afficherRecompenses() {


        Parent newRoot;
        try {
            FXMLLoader loader = new FXMLLoader(InterfaceGraphiqueMenu.class.getResource("recompenses.fxml"));
            newRoot = loader.load();

            RecompensesController controller = loader.getController();
            controller.setMainController(this);


            round++;
            Scene scene3 = new Scene(newRoot, InterfaceGraphiqueMenu.SCREEN_SIZE, InterfaceGraphiqueMenu.SCREEN_SIZE);
            stage.setTitle("Remise des récompenses !");
            stage.setScene(scene3);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void afficherDefaite() {
        stage.close();

        Stage stage2 = new Stage();

        Parent root2 = null;
        try {
            root2 = new FXMLLoader(InterfaceGraphiqueMenu.class.getResource("defaite.fxml")).load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scene scene2 = new Scene(root2, InterfaceGraphiqueMenu.SCREEN_SIZE, InterfaceGraphiqueMenu.SCREEN_SIZE);
        stage2.setTitle("Vous avez perdu");
        stage2.setScene(scene2);
        stage2.show();
    }

    private void afficherVictoire() {
        stage.close();

        Stage stage3 = new Stage();

        Parent root2 = null;
        try {
            root2 = new FXMLLoader(InterfaceGraphiqueMenu.class.getResource("victoire.fxml")).load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scene scene2 = new Scene(root2, InterfaceGraphiqueMenu.SCREEN_SIZE, InterfaceGraphiqueMenu.SCREEN_SIZE);
        stage3.setTitle("Vous avez gagné");
        stage3.setScene(scene2);
        stage3.show();
    }


    private void majUI(){
        majUIPrincipale();
//        majUIBas();
        genererTextesFin();
    }

    private void majHerosRestants(Hero hero) {
        Jeu.majHerosRestants(hero);
        verifierHerosRestants();
    }

    private void majUIPrincipale(){

        Image sol = new Image(getClass().getResourceAsStream("/img/sol.png"), InterfaceGraphiqueMenu.SCREEN_SIZE / 11, InterfaceGraphiqueMenu.SCREEN_SIZE / 11, false, false);


        for (int i=0;i<11;i++){

            for(int j=1;j<maximum+1;j++){
                root.add(new ImageView(sol),i,j);


                /*
                Génération des héros et des clicks liés aux héros
                 */
                if(heros.size()>=j){
                    if(i==2){
                        ImageView imageHero = new ImageView(new Image(getClass().getResourceAsStream("/img/"+ heros.get(j-1).recupChemin()+".png"), InterfaceGraphiqueMenu.SCREEN_SIZE / 11, InterfaceGraphiqueMenu.SCREEN_SIZE / 11, false, false));
                        root.add(imageHero,i,j);


                        int finalJ = j;
                    }

                    else if(i==1){
                        Text textPVHero = new Text(""+ heros.get(j-1).recupVie());
                        GridPane.setHalignment(textPVHero, HPos.CENTER);
                        textPVHero.setFont(Font.font("Arial", FontWeight.BOLD, 50));
                        root.add(textPVHero,i,j);
                    }

                    else if (i == 0) {
                        if(LanceurSorts.class.isAssignableFrom(heros.get(j-1).getClass()) || heros.get(j-1).getClass()== Chasseur.class){
                            if(LanceurSorts.class.isAssignableFrom(heros.get(j-1).getClass())){
                                LanceurSorts lanceurSorts = (LanceurSorts) heros.get(j-1);
                                Text textManaHero = new Text(""+ lanceurSorts.getMana());

                                textManaHero.setFont(Font.font("Arialk", FontWeight.BOLD, 50));

                                GridPane.setHalignment(textManaHero, HPos.CENTER);

                                root.add(textManaHero,i,j);
                            }
                            else{
                                Chasseur chasseur = (Chasseur) heros.get(j-1);
                                Text textArrowHero = new Text(""+ chasseur.getArrow());
                                textArrowHero.setFont(Font.font("Arial", FontWeight.BOLD, 50));

                                GridPane.setHalignment(textArrowHero, HPos.CENTER);
                                root.add(textArrowHero,i,j);
                            }

                        }
                    }

                }


                if (ennemis.size()>=j){
                    if(i==9){
                        ImageView imageEnemy = new ImageView(new Image(getClass().getResourceAsStream("/img/"+ ennemis.get(j-1).recupChemin()+".png"), InterfaceGraphiqueMenu.SCREEN_SIZE / 11, InterfaceGraphiqueMenu.SCREEN_SIZE / 11, false, false));
                        root.add(imageEnemy,i,j);
                    }
                    else if(i==10){
                        Text textPVEnemy = new Text(""+ ennemis.get(j-1).recupPointsDeVie());
                        GridPane.setHalignment(textPVEnemy, HPos.CENTER);
                        textPVEnemy.setFont(Font.font("Arial", FontWeight.BOLD, 50));
                        root.add(textPVEnemy,i,j);
                    }
                }
            }
        }
    }

    private void genererTextesFin() {

        TextField firstField = new TextField();
        TextField secondField = new TextField();

        Text firstText = new Text("Action : ");
        Text secondText = new Text("Joueurs :");

        Text firstTextRight = new Text("1 Attaquer");
        Text firstTextRightRight = new Text("2 Défense");
        Text firstTextRightRightRight = new Text("3 Consommer");

        Text secondTextRight = new Text("Numero");

        root.add(firstText,0,6);
        root.add(secondText,0,7);

        root.add(firstField,1,6);
        root.add(secondField,1,7);

        root.add(firstTextRight,2,6);
        root.add(secondTextRight,2,7);

        root.add(firstTextRightRight,3,6);
        root.add(firstTextRightRightRight,4,6);

        secondField.setOnAction(actionEvent -> {

            if(!secondField.getText().isEmpty() && !firstField.getText().isEmpty()){
                String fText = firstField.getText();
                String sText = secondField.getText();


                if(fText.equals("1")){
                    actionChoix(heros.get((Integer.parseInt(sText)/10)-1),"Attaquer",(Integer.parseInt(sText)%10)-1);
                    majHerosRestants(heros.get((Integer.parseInt(sText)/10)-1));
                    verifierEnnemisRestants();
                }
                else if (fText.equals("2")){
                    actionChoix(heros.get(Integer.parseInt(sText)-1),"Defendre");
                    majHerosRestants(heros.get(Integer.parseInt(sText)-1));

                }
                else if (fText.equals("3")){
                    actionChoix(heros.get(Integer.parseInt(sText)-1),"Potion");
                    majHerosRestants(heros.get(Integer.parseInt(sText)-1));
                }

                majUI();
                System.out.println(herosRestantsRound);
            }

        });
    }
}