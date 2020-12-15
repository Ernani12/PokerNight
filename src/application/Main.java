package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

	private Image applicationIcon;
	private IniciarProgramas main;
	private Stage verification;
	private Pane  PainelEntrada;
	private Label  language;
	private Scene scene;
	private Rectangle2D bounds;
	private Button Ingles;

	@Override
	public void start(Stage primaryStage) {

		main = new IniciarProgramas();
		verification = new Stage();
		PainelEntrada = new Pane();


		// Button français
		Ingles = new Button("English");
		Ingles.setLayoutX(80);
		Ingles.setLayoutY(30);
		Ingles.setOnAction(e -> {
			main.start(primaryStage);
			verification.close();
		});

		

		// Mets l'icon de l'application
		applicationIcon = new Image(getClass().getResourceAsStream("poker.png"));
		primaryStage.getIcons().add(applicationIcon);

		// Lien du CSS pour l'application && définie les 'ID' pour les objets
		Application.setUserAgentStylesheet(getClass().getResource("application.css").toExternalForm());
		PainelEntrada.setId("back");
		
		// Création de la page principale
		PainelEntrada.getChildren().addAll(Ingles);
		scene = new Scene(PainelEntrada, 230, 140);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Poker");
		primaryStage.setResizable(false);
		primaryStage.show();

		// 'Stage' vais être toujours centré
		bounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2);
		primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);
		verification.setX((bounds.getWidth() - primaryStage.getWidth()) / 2);
		verification.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);
	}

	public static void main(String[] args) {
		launch(args);         //passsa  argumentos de um para outro
	}

}
