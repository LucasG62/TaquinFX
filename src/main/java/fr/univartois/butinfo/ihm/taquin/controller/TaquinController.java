package fr.univartois.butinfo.ihm.taquin.controller;

import fr.univartois.butinfo.ihm.taquin.model.Grid;
import fr.univartois.butinfo.ihm.taquin.model.ITaquinController;
import fr.univartois.butinfo.ihm.taquin.model.Taquin;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.net.URL;

/**
 * La classe TaquinController propose un contrôleur permettant de gérer un jeu du Taquin
 * présenté à l'utilisateur sous la forme d'une interface graphique JavaFX.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class TaquinController implements ITaquinController {

    /**
     * Le label affichant le nombre de déplacements réalisés par l'utilisateur.
     */
    @FXML
    private Label nbMoves;

    /**
     * La grille affichant les boutons permettant de jouer au Taquin.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Les boutons représentant les tuiles du jeu du Taquin.
     */
    private Button[][] buttons;

    /**
     * Le modèle du Taquin avec lequel ce contrôleur interagit.
     */
    private Taquin taquin;

    /**
     * Constructeur de la classe ITaquinController.
     */
    public TaquinController() {
    }

    @Override
    public void setModel(Taquin taquin) {
        this.taquin = taquin;
    }

    @Override
    public void initGrid(Grid grid) {
        buttons = new Button[grid.size()][grid.size()];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                initButton(i, j, grid);
            }
        }
    }

    @Override
    public void initEvents(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
                case UP, Z -> taquin.pushDown();
                case LEFT, Q -> taquin.pushRight();
                case DOWN, S -> taquin.pushUp();
                case RIGHT, D -> taquin.pushLeft();
            }
            e.consume();
        });
    }

    @Override
    public void initMoveLabel(IntegerProperty nbMoves) {
        this.nbMoves.textProperty().bind(nbMoves.asString());
    }

    @Override
    public void startGame() {
        setButtonsDisable(false);
    }

    @Override
    public void endGame() {
        setButtonsDisable(true);
    }

    /**
     * Relance une partie sur la vue.
     */
    @FXML
    public void restartGame() {
        taquin.restartGame();
    }

    /**
     * Initialise un bouton à un emplacement défini de la grille.
     *
     * @param i    Numéro de ligne.
     * @param j    Numéro de colonne.
     * @param grid La grille du jeu.
     */
    private void initButton(int i, int j, Grid grid) {
        buttons[i][j] = new Button();
        buttons[i][j].textProperty().bind(grid.get(i, j).getProperty().asString());
        gridPane.add(buttons[i][j], j, i);
        buttons[i][j].setPrefHeight(100);
        buttons[i][j].setPrefWidth(100);
        buttons[i][j].setOnAction(e -> taquin.push(i, j));
        buttons[i][j].visibleProperty().bind(grid.get(i, j).getProperty().isNotEqualTo(0));
        buttons[i][j].setBackground(createBackground(grid.get(i, j).getValue()));
        grid.get(i, j).getProperty().addListener(
                (p, o, n) -> buttons[i][j].setBackground(createBackground(n)));
    }

    /**
     * Désactive/Active les boutons de la vue.
     *
     * @param b Booléen.
     */
    private void setButtonsDisable(boolean b) {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(b);
            }
        }
    }

    /**
     * Détermine l'arrière-plan de la tuile ayant l'indice donné.
     *
     * @param index L'indice de la tuile.
     * @return L'arrière-plan associé à la tuile.
     */
    private Background createBackground(Number index) {
        URL urlImage = getClass().getResource("../view/images/iut-" + index + ".jpg");
        assert urlImage != null;
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(urlImage.toExternalForm(), 100, 100, true, false),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

}
