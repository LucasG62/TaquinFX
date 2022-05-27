package fr.univartois.butinfo.ihm.taquin.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe Tile représente une tuile de la grille du jeu du Taquin.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Tile {

    /**
     * La valeur de cette tuile.
     */
    private final IntegerProperty value;

    /**
     * Construit une nouvelle instance de Tile.
     *
     * @param value La valeur initiale de la tuile.
     */
    public Tile(int value) {
        this.value = new SimpleIntegerProperty(value);
    }

    /**
     * Donne l'objet Property de la tuile.
     *
     * @return L'objet Property de la tuile.
     */
    public IntegerProperty getProperty() {
        return value;
    }

    /**
     * Donne la valeur de cette tuile.
     *
     * @return La valeur de cette tuile.
     */
    public int getValue() {
        return value.getValue();
    }

    /**
     * Modifie la valeur de cette tuile.
     *
     * @param value La nouvelle valeur de la tuile.
     */
    public void setValue(int value) {
        this.value.set(value);
    }

    /**
     * Échange la valeur de cette tuile avec celle d'une tuile donnée.
     *
     * @param other La tuile avec laquelle échanger la valeur.
     */
    public void exchange(Tile other) {
        int tmp = other.getValue();
        other.setValue(getValue());
        this.setValue(tmp);
    }

}
