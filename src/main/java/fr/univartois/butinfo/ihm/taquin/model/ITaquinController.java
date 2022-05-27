package fr.univartois.butinfo.ihm.taquin.model;

import javafx.beans.property.IntegerProperty;
import javafx.scene.Scene;

/**
 * L'interface ITaquinController définit le contrat qui doit être respecté par n'importe
 * quel contrôleur du jeu du Taquin.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public interface ITaquinController {

    /**
     * Modifie la façade du jeu du Taquin avec laquelle ce contrôleur interagit.
     *
     * @param taquin La façade du jeu du Taquin avec laquelle interagir.
     */
    void setModel(Taquin taquin);

    /**
     * Initialise la grille du Taquin affichée par ce contrôleur.
     *
     * @param grid La grille du jeu.
     */
    void initGrid(Grid grid);

    /**
     * Initialise les évènements des touches pressées.
     *
     * @param scene La scène qui écoutera les touches pressées.
     */
    void initEvents(Scene scene);

    /**
     * Met à jour l'affichage du nombre de déplacements.
     *
     * @param nbMoves Le nombre de déplacements.
     */
    void initMoveLabel(IntegerProperty nbMoves);

    /**
     * Prépare une nouvelle partie sur la vue.
     */
    void startGame();

    /**
     * Termine la partie en cours sur la vue.
     */
    void endGame();

}
