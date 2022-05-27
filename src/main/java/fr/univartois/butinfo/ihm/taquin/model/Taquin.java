package fr.univartois.butinfo.ihm.taquin.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe Taquin fournit une façade pour le modèle du jeu du Taquin.
 * Elle fournit toutes les méthodes nécessaires pour gérer une partie de ce jeu.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Taquin {

    /**
     * La grille sur laquelle le jeu se déroule.
     */
    private final Grid grid;

    /**
     * Le nombre de déplacements réalisés sur la grille.
     */
    private final IntegerProperty nbMoves;

    /**
     * Le contrôleur de l'application, avec lequel cette façade interagit pour maintenir
     * la cohérence avec la vue, et inversement.
     */
    private ITaquinController controller;

    /**
     * Construit une nouvelle instance du jeu du Taquin.
     *
     * @param size La taille de la grille sur laquelle le jeu se déroule.
     */
    public Taquin(int size) {
        this.nbMoves = new SimpleIntegerProperty(0);
        this.grid = new Grid(size);
    }

    /**
     * Donne la taille de la grille sur laquelle le jeu se déroule.
     *
     * @return La taille de la grille, en nombre de cases par côté.
     */
    public int size() {
        return grid.size();
    }

    /**
     * Modifie le contrôleur avec lequel cette façade interagit pour maintenir la
     * cohérence avec la vue, et inversement.
     *
     * @param controller Le contrôleur avec lequel interagir.
     */
    public void setController(ITaquinController controller) {
        this.controller = controller;
        controller.initGrid(grid);
    }

    /**
     * Démarre une nouvelle partie.
     */
    public void startGame() {
        grid.shuffle();
        controller.initMoveLabel(nbMoves);
        controller.startGame();
    }

    /**
     * Pousse la case à la position donnée dans l'emplacement vide.
     *
     * @param row    La ligne de la case à pousser.
     * @param column La colonne de la case à pousser.
     */
    public void push(int row, int column) {
        if (grid.push(row, column)) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située sous l'emplacement vide dans cet emplacement.
     */
    public void pushUp() {
        if (grid.pushUp()) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située à gauche de l'emplacement vide dans cet emplacement.
     */
    public void pushRight() {
        if (grid.pushRight()) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située au-dessus de l'emplacement vide dans cet emplacement.
     */
    public void pushDown() {
        if (grid.pushDown()) {
            acceptMove();
        }
    }

    /**
     * Pousse la case située à droite de l'emplacement vide dans cet emplacement.
     */
    public void pushLeft() {
        if (grid.pushLeft()) {
            acceptMove();
        }
    }

    /**
     * Valide le dernier déplacement demandé par l'utilisateur.
     */
    private void acceptMove() {
        nbMoves.set(nbMoves.get() + 1);

        if (grid.isOrdered()) {
            controller.endGame();
        }
    }

    /**
     * Redémarre une nouvelle partie.
     */
    public void restartGame() {
        grid.reset();
        nbMoves.set(0);
        startGame();
    }

}
