package fr.univartois.butinfo.ihm.taquin;

import fr.univartois.butinfo.ihm.taquin.controller.TaquinController;
import fr.univartois.butinfo.ihm.taquin.model.Taquin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe TaquinApplication est la classe principale du jeu du Taquin fonctionnant
 * avec JavaFX.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class TaquinApplication extends Application {

    /**
     * Créé un objet de la classe TaquinApplication.
     */
    public TaquinApplication() {
    }

    /**
     * Cette méthode exécute l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Cette méthode permet d'initialiser l'affichage de la fenêtre de l'application.
     *
     * @param stage La fenêtre (initialement vide) de l'application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Il faut d'abord récupérer la description de la vue (au format FXML).
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/taquin.fxml"));
        Parent viewContent = fxmlLoader.load();

        // Ensuite, on la place dans une scène.
        Scene scene = new Scene(viewContent);
        // Que l'on place elle-même dans la fenêtre.
        stage.setScene(scene);

        // On peut ensuite donner un titre à la fenêtre.
        stage.setTitle("Taquin");

        // On crée ensuite le modèle du jeu.
        Taquin taquin = new Taquin(4);

        // On récupère ensuite le contrôleur.
        TaquinController controller = fxmlLoader.getController();

        // On associe le modèle et le contrôleur.
        taquin.setController(controller);
        controller.setModel(taquin);

        // On initialise les events.
        controller.initEvents(scene);

        // Enfin, on lance le jeu et on affiche la fenêtre.
        taquin.startGame();
        stage.show();
    }

}
