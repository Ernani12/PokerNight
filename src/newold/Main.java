package newold;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Main extends Application {

	// Application icon
	private Image applicationIcon;

	// Elements of the main menu
	private Stage stage;
	private Button comp, net;
	private Label title;
	private Pane root;
	private Scene mainScene;

	// Elements of the name scene
	private Label label2, label3;
	private TextField textField1, textField2;
	private Button next, back;
	private Pane singlePane, networkPane;
	private Scene singleScene, networkScene;

	// Players name
	private String player1Name, player2Name;

	// Elements of the manual
	private Image manualIcon;
	private ImageView manualChipRed, manualChipBlack, manualChipWhite, manualChipGreen, manualChipBlue;
	private Stage manualStage;
	private Scene manualScene;
	private Pane manualPane;
	private Button test, test1, test2, test3, test4;
	private Label explanation, explanation1;
	private String manualText1, manualText2;

	// Images of the cards
	private String dealer, var;
	private Map<String, ImageView> map;
	private HashMap<String, ArrayList<Integer>> map2;

	// TextField to raise or to bet
	private TextField raiseAmount;

	// Random number
	private int rand;

	// Sound of Buttons
	private MediaPlayer buttonSound;

	// Player2 variables
	private Stage player2stage;
	private Scene playe2scene;

	// Show/Remove option to bet/raise
	private int raiseId = 1;

	// To know how many turns it has been
	private int turnId = 1;
	private int playerId = 1;

	// Initial cash
	private int limit = 1000;

	// Total numbers of players
	private int totalPlayers = 2;

	// Text restrictions
	private Boolean checkWarning = true, checkWarning1 = true;

	// Labels
	private Map<String, Label> map4;

	// Buttons
	private String nom;
	private Map<String, MediaPlayer> map5;
	private Map<String, Button> map3;

	// Panes of players
	private Map<String, Pane> map6;

	// Coins
	private Map<String, ImageView> map7;

	// MedipPLayer
	private MediaPlayer swoosh;

	@Override
	public void start(Stage primaryStage) {

		//
		swoosh = new MediaPlayer(new Media(new File("swoosh.mp3").toURI().toString()));

		// Declaration of multiple important variables
		root = new Pane();
		stage = primaryStage;
		title = new Label("Texas Holdem Poker");
		title.setLayoutY(10);
		title.setLayoutX(10);

		// Puts the application icon
		applicationIcon = new Image(getClass().getResourceAsStream("poker.png"));
		stage.getIcons().add(applicationIcon);

		// Label2
		label2 = new Label("What is the first player's name?");
		label2.setLayoutX(30.0);
		label2.setLayoutY(5.0);

		// Label3
		label3 = new Label("What is the second player's name?");
		label3.setLayoutX(15.0);
		label3.setLayoutY(70.0);

		// Back button
		back = new Button("Back");
		back.setOnAction(e -> start(stage));

		// Sound of button clicks
		buttonSound = new MediaPlayer(new Media(new File("click.mp3").toURI().toString()));

		// TextField1
		textField1 = new TextField();
		textField1.setLayoutX(50.0);
		textField1.setLayoutY(35.0);
		textField1.setAlignment(Pos.CENTER);
		textField1.textProperty().addListener((ov, oldValue, newValue) -> {
			MediaPlayer md1 = new MediaPlayer(new Media(new File("type.mp3").toURI().toString()));
			md1.play();
			if (textField1.getText().length() == 1) {
				textField1.setText(newValue.toUpperCase());
			}
		});

		// TextField2
		textField2 = new TextField();
		textField2.setAlignment(Pos.CENTER);
		textField2.setLayoutX(50.0);
		textField2.setLayoutY(100.0);
		textField2.textProperty().addListener((ov, oldValue, newValue) -> {
			MediaPlayer md1 = new MediaPlayer(new Media(new File("type.mp3").toURI().toString()));
			md1.play();
			if (textField2.getText().length() == 1) {
				textField2.setText(newValue.toUpperCase());
			}
		});

		// Multiplayer button
		singlePane = new Pane();
		singlePane.setId("back");
		comp = new Button("Multiplayer");
		comp.setLayoutX(53);
		comp.setLayoutY(50);
		comp.setOnAction(e -> {
			back.setLayoutX(70.0);
			back.setLayoutY(155.0);
			next.setLayoutX(165.0);
			next.setLayoutY(155.0);
			singlePane.getChildren().addAll(label2, textField1, label3, textField2, next, back);
			singleScene = new Scene(singlePane, 300, 210);
			stage.setScene(singleScene);
			buttonSound.play();
		});

		// Single player
		networkPane = new Pane();
		networkScene = new Scene(networkPane, 100, 200);
		net = new Button("Computer");
		net.setLayoutX(58);
		net.setLayoutY(100);
		net.setOnAction(e -> {
			stage.setScene(networkScene);
			buttonSound.play();

		});

		// Next Button
		next = new Button("Next");
		next.setOnAction(e -> {
			player1Name = textField1.getText();
			player2Name = textField2.getText();
			labels();
			buttonSound.play();
			cards();
			player1Conditions();
			player2Conditions();
		});

		// TextField of bet/raise
		raiseAmount = new TextField();
		raiseAmount.setAlignment(Pos.CENTER);
		raiseAmount.setLayoutX(1211);
		raiseAmount.setLayoutY(588);
		raiseAmount.setPrefWidth(90);

		// Calls multiple methods
		variables();
		numberPanes();
		labels();
		buttons();
		textRestrictions();
		manual();
		tokens();

		// Disable buttons on player2
		map3.get("player2call").setDisable(true);
		map3.get("player2raise").setDisable(true);
		map3.get("player2fold").setDisable(true);
		map3.get("player2check").setDisable(true);
		map3.get("player2bet").setDisable(true);

		// Disable buttons on player1
		map3.get("player1check").setDisable(true);
		map3.get("player1raise").setDisable(true);
		map3.get("player1call").setDisable(true);

		// Setting IDs for CSS
		root.setId("back");
		title.setId("mainTitle");
		textField2.setId("field");
		textField1.setId("field");
		raiseAmount.setId("field");
		test.setId("manualButton");
		test1.setId("manualButton");
		test2.setId("manualButton");
		test3.setId("manualButton");
		test4.setId("manualButton");
		explanation1.setId("dollars");
		manualPane.setId("background");
		Application.setUserAgentStylesheet(getClass().getResource("application.css").toExternalForm());

		// Setting up first page
		root.getChildren().addAll(net, comp, title);
		mainScene = new Scene(root, 200, 156);
		stage.setScene(mainScene);
		stage.setTitle("Poker");
		stage.setResizable(false);
		stage.show();
	}

	public void textRestrictions() {
		raiseAmount.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > oldValue.intValue()) {
					char ch = raiseAmount.getText().charAt(oldValue.intValue());
					System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

					// Check if the new character is the number or other's
					if (!(ch >= '0' && ch <= '9')) {

						// if it's not number then just setText to previous one
						raiseAmount.setText(raiseAmount.getText().substring(0, raiseAmount.getText().length() - 1));
					}

					if (checkWarning == false) {
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("warning"));
						checkWarning = true;
					}
					if (checkWarning1 == false) {
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("warning1"));
						checkWarning1 = true;
					}
				}

			}

		});

		raiseAmount.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {

					// Check if the new character is greater than the limit
					if (Integer.valueOf(raiseAmount.getText()) > limit && checkWarning) {
						raiseAmount.setText(String.valueOf(limit));
						map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("warning"));
						checkWarning = false;
						// Check if the new character is greater than the limit
					} else if (Integer.valueOf(raiseAmount.getText()) < bet && checkWarning1) {
						raiseAmount.setText(String.valueOf(bet));
						map4.get("warning1").setText("The minimum is " + bet);
						map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("warning1"));
						checkWarning1 = false;
					} else {
						test();
						raiseAmount.setText("");

					}

				}
			}
		});

	}

	public void test() {
		if (turnId == 1) {
			if (Integer.valueOf(raiseAmount.getText()) > bet) {
				bet = Integer.valueOf(raiseAmount.getText());
				raiseAmount.setText(String.valueOf(bet));
			}

			if (playerId == 1) {
				System.out.println(
						map.size() + map2.size() + map3.size() + map4.size() + map5.size() + map6.size() + map7.size());

				value = Integer.valueOf(raiseAmount.getText());

				cost();
				map3.get("player1fold").setDisable(true);
				map3.get("player1bet").setDisable(true);

				// map6.get("player" + playerId +
				// "pane").getChildren().addAll(map4.get("help"), raiseAmount);

				map3.get("player2call").setDisable(false);
				map3.get("player2fold").setDisable(false);
				map3.get("player2raise").setDisable(false);
				go();

				playerId = 2;
			} else if (playerId == 2) {
				value = Integer.valueOf(raiseAmount.getText());
				cost();
				map3.get("player1fold").setDisable(false);
				map3.get("player1bet").setDisable(false);
				map3.get("player1check").setDisable(false);
				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);
				go();
				playerId = 1;
				turnId = 2;
				map4.get("player1numberTurn").setText("Turn: " + turnId);
				map4.get("player2numberTurn").setText("Turn: " + turnId);

				firstTurn();
			}
		} else if (turnId == 2) {

			if (playerId == 1) {
				value = Integer.valueOf(raiseAmount.getText());
				cost();
				map3.get("player1fold").setDisable(true);
				map3.get("player1bet").setDisable(true);
				map3.get("player1check").setDisable(true);
				go();
				map3.get("player2call").setDisable(false);
				map3.get("player2fold").setDisable(false);
				map3.get("player2raise").setDisable(false);
				playerId = 2;
				value = 0;
			} else if (playerId == 2) {
				cost();
				map3.get("player1fold").setDisable(false);
				map3.get("player1bet").setDisable(false);
				map3.get("player1check").setDisable(false);
				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);
				go();
				playerId = 1;
				turnId = 3;
				map4.get("player1numberTurn").setText("Turn: " + turnId);
				map4.get("player2numberTurn").setText("Turn: " + turnId);
				secondTurn();
				value = 0;
			}
		} else if (turnId == 3) {
                    
                         if (map8.get("player1value") <= 0) {
                                JOptionPane.showMessageDialog (null, "Player 1 perdeu");

                    }
                    
                    if (map8.get("player2value") <= 0) {
                                JOptionPane.showMessageDialog (null, "Player 2 perdeu");

                    }
                    
			if (playerId == 1) {
				value = Integer.valueOf(raiseAmount.getText());
				cost();
				map3.get("player1fold").setDisable(true);
				map3.get("player1bet").setDisable(true);
				map3.get("player1check").setDisable(true);
				go();
				map3.get("player2call").setDisable(false);
				map3.get("player2fold").setDisable(false);
				map3.get("player2raise").setDisable(false);
				playerId = 2;
				value = 0;
			} else if (playerId == 2) {
				value = Integer.valueOf(raiseAmount.getText());
				cost();
				map3.get("player1fold").setDisable(false);
				map3.get("player1bet").setDisable(false);
				map3.get("player1check").setDisable(false);
				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);
				go();
				playerId = 1;
				turnId = 4;
				map4.get("player1numberTurn").setText("Turn: " + turnId);
				map4.get("player2numberTurn").setText("Turn: " + turnId);
				thirdTurn();
				value = 0;
			}
		} else if (turnId == 4) {
                    
                    
               
                    
			if (playerId == 1) {
				value = Integer.valueOf(raiseAmount.getText());
				cost();
				map3.get("player1fold").setDisable(true);
				map3.get("player1bet").setDisable(true);
				map3.get("player1check").setDisable(true);
				go();
				map3.get("player2call").setDisable(false);
				map3.get("player2fold").setDisable(false);
				map3.get("player2raise").setDisable(false);
				playerId = 2;
				value = 0;
			} else if (playerId == 2) {
				value = Integer.valueOf(raiseAmount.getText());
				cost();
				map3.get("player1fold").setDisable(false);
				map3.get("player1bet").setDisable(false);
				map3.get("player1check").setDisable(false);
				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);
				go();
				playerId = 1;
				turnId = 5;
				map4.get("player1numberTurn").setText("Turn: " + turnId);
				map4.get("player2numberTurn").setText("Turn: " + turnId);
				value = 0;
			}
		}

	}

	int whitess;

	public void cost() {

		if (value <= (map8.get("player" + playerId + "total"))) {
			if (map8.get("player" + playerId + "total") != 0) {
				map8.replace("player" + playerId + "total",
						new Integer((map8.get("player" + playerId + "total")) - value));
				if (value % 10 != 0) {
					// deal avec les unites
					whitess = 10;

					int ones = value % 10;
					value = value - ones;
					while (ones != 0) {
						if (ones < 5 && map9.get("player" + playerId + "numbersToken").get(0) != 0) {
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "white" + map9.get("player" + playerId + "numbersToken").get(0)));

							map9.get("player" + playerId + "numbersToken").set(0,
									map9.get("player" + playerId + "numbersToken").get(0) - 1);
							ones--;
						} else if (ones >= 5 && map9.get("player" + playerId + "numbersToken").get(1) != 0) {

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));
							ones = ones - 5;
							System.out.println(map9.get("player" + playerId + "numbersToken").get(1));

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) - 1);
						} else if (map9.get("player" + playerId + "numbersToken").get(1) == 0 && ones == 5
								&& map9.get("player" + playerId + "numbersToken").get(0) >= 5) {
							for (int i = 0; i < 5; i++) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
								ones--;
							}
						} else if (map9.get("player" + playerId + "numbersToken").get(0) == 0) {
							if (map9.get("player" + playerId + "numbersToken").get(1) != 0
									&& map9.get("player" + playerId + "numbersToken").get(0) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);

								for (int i = 1; i <= 5; i++) {

									map9.get("player" + playerId + "numbersToken").set(0,
											map9.get("player" + playerId + "numbersToken").get(0) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "white"
													+ map9.get("player" + playerId + "numbersToken").get(0)));

								}
							} else if (map9.get("player" + playerId + "numbersToken").get(2) != 0
									&& map9.get("player" + playerId + "numbersToken").get(1) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);

								for (int i = 1; i <= 2; i++) {

									map9.get("player" + playerId + "numbersToken").set(1,
											map9.get("player" + playerId + "numbersToken").get(1) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "red"
													+ map9.get("player" + playerId + "numbersToken").get(1)));

								}
							} else if (map9.get("player" + playerId + "numbersToken").get(3) != 0
									&& map9.get("player" + playerId + "numbersToken").get(2) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) - 1);

								for (int i = 1; i <= 2; i++) {

									map9.get("player" + playerId + "numbersToken").set(2,
											map9.get("player" + playerId + "numbersToken").get(2) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "blue"
													+ map9.get("player" + playerId + "numbersToken").get(2)));

								}

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							} else if (map9.get("player" + playerId + "numbersToken").get(4) != 0
									&& map9.get("player" + playerId + "numbersToken").get(3) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "black" + map9.get("player" + playerId + "numbersToken").get(4)));

								map9.get("player" + playerId + "numbersToken").set(4,
										map9.get("player" + playerId + "numbersToken").get(4) - 1);

								for (int i = 1; i <= 4; i++) {

									map9.get("player" + playerId + "numbersToken").set(3,
											map9.get("player" + playerId + "numbersToken").get(3) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "green"
													+ map9.get("player" + playerId + "numbersToken").get(3)));

								}
							}
						}
					}
				}
				if (value % 100 != 0) {
					// deal avec les dizaines
					int haha = value % 100;
					value = value - haha;
					while (haha != 0) {

						if (haha == 10 && map9.get("player" + playerId + "numbersToken").get(2) != 0) {

							System.err.println("10-1");
							haha = haha - 10;

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							map9.get("player" + playerId + "numbersToken").set(2,
									map9.get("player" + playerId + "numbersToken").get(2) - 1);
						} else if (haha == 10 && map9.get("player" + playerId + "numbersToken").get(1) >= 2) {

							System.err.println("10-2");
							haha = haha - 10;
							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 10 && map9.get("player" + playerId + "numbersToken").get(1) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(0) >= 5) {

							System.err.println("10-3");
							haha = haha - 10;
							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) - 1);
						} else if (haha == 10 && map9.get("player" + playerId + "numbersToken").get(0) >= 10) {

							System.err.println("10-4");
							haha = haha - 10;
							for (int i = 1; i <= 10; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}
						} else if (haha == 20 && map9.get("player" + playerId + "numbersToken").get(2) >= 2) {

							System.err.println("20-1");
							haha = haha - 20;

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}
						} else if (haha == 20 && map9.get("player" + playerId + "numbersToken").get(2) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 2) {

							System.err.println("20-1");
							haha = haha - 20;
							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							map9.get("player" + playerId + "numbersToken").set(2,
									map9.get("player" + playerId + "numbersToken").get(2) - 1);
						} else if (haha == 20 && map9.get("player" + playerId + "numbersToken").get(1) >= 4) {

							System.err.println("20-2");
							haha = haha - 20;
							for (int i = 1; i <= 4; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 20 && map9.get("player" + playerId + "numbersToken").get(0) >= 5
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 3) {

							System.err.println("20-3");
							haha = haha - 20;
							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							for (int i = 1; i <= 3; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 20 && map9.get("player" + playerId + "numbersToken").get(0) >= 10
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 2) {

							System.err.println("20-4");
							haha = haha - 20;
							for (int i = 1; i <= 10; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(3) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 1) {

							System.err.println("30-1");
							haha = haha - 30;

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) - 1);

							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);
						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(2) >= 3) {

							System.err.println("30-2");
							haha = haha - 30;

							for (int i = 1; i <= 3; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}
						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(2) >= 2
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 2) {

							System.err.println("30-3");
							haha = haha - 30;
							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}

						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(2) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 4) {

							System.err.println("30-4");
							haha = haha - 30;
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							map9.get("player" + playerId + "numbersToken").set(2,
									map9.get("player" + playerId + "numbersToken").get(2) - 1);
							for (int i = 1; i <= 4; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);

							}

						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(1) >= 6) {

							System.err.println("30-5");
							haha = haha - 30;
							for (int i = 1; i <= 6; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(0) >= 5
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 5) {

							System.err.println("30-6");
							haha = haha - 30;
							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 30 && map9.get("player" + playerId + "numbersToken").get(0) >= 10
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 4) {

							System.err.println("30-7");
							haha = haha - 30;
							for (int i = 1; i <= 10; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							for (int i = 1; i <= 4; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(3) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 3) {

							System.err.println("40-1");
							haha = haha - 40;

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));
							for (int i = 1; i <= 3; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}

							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(3) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 2
								&& map9.get("player" + playerId + "numbersToken").get(0) >= 5) {

							System.err.println("40-2");
							haha = haha - 40;

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));
							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(3) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(0) >= 10) {

							System.err.println("40-3");
							haha = haha - 40;

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));
							for (int i = 1; i <= 0; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) - 1);
							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(2) >= 4) {

							System.err.println("40-4");
							haha = haha - 40;

							for (int i = 1; i <= 4; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(2) >= 3
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 2) {

							System.err.println("40-5");
							haha = haha - 40;

							for (int i = 1; i <= 3; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(2) >= 2
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 4) {

							System.err.println("40-6");
							haha = haha - 40;
							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}

							for (int i = 1; i <= 4; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}

						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(2) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 6) {

							System.err.println("40-7");
							haha = haha - 40;
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							map9.get("player" + playerId + "numbersToken").set(2,
									map9.get("player" + playerId + "numbersToken").get(2) - 1);
							for (int i = 1; i <= 6; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);

							}

						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(1) >= 8) {

							System.err.println("40-8");
							haha = haha - 40;
							for (int i = 1; i <= 8; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(0) >= 5
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 7) {

							System.err.println("40-9");
							haha = haha - 40;
							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							for (int i = 1; i <= 7; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(0) >= 10
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 6) {

							System.err.println("40-10");
							haha = haha - 40;
							for (int i = 1; i <= 10; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) - 1);
							}

							for (int i = 1; i <= 6; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}
						} else if (haha == 40 && map9.get("player" + playerId + "numbersToken").get(3) != 0) {

							System.err.println("25 TO 2*10+5");
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));

							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);

							for (int i = 1; i <= 2; i++) {

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							}

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) + 1);
							map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

						}

						else if (haha == 50 && map9.get("player" + playerId + "numbersToken").get(3) >= 2) {
							haha = haha - 50;

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) - 1);
							}
						} else if (haha == 50 && map9.get("player" + playerId + "numbersToken").get(3) >= 2
								&& map9.get("player" + playerId + "numbersToken").get(2) >= 5) {
							haha = haha - 50;

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) - 1);
							}

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) - 1);
							}
						} else if (haha == 50 && map9.get("player" + playerId + "numbersToken").get(3) >= 2) {
							haha = haha - 50;

							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) - 1);
							}
						} else if (haha == 50 && map9.get("player" + playerId + "numbersToken").get(3) >= 1
								&& map9.get("player" + playerId + "numbersToken").get(2) >= 2
								&& map9.get("player" + playerId + "numbersToken").get(1) >= 1) {
							haha = haha - 50;

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));
							for (int i = 1; i <= 2; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}
							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) - 1);

							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);

						} else if (haha == 50 && map9.get("player" + playerId + "numbersToken").get(2) >= 5) {
							haha = haha - 50;

							for (int i = 1; i <= 5; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);
							}

						} else if (haha == 50 && map9.get("player" + playerId + "numbersToken").get(1) >= 10) {
							haha = haha - 50;

							for (int i = 1; i <= 10; i++) {

								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);
							}

						} else if (haha == 60) {
							haha = haha - 60;

						} else if (haha == 70) {
							haha = haha - 70;

						} else if (haha == 80) {
							haha = haha - 80;

						} else if (haha == 90) {
							haha = haha - 90;

						} else if (map9.get("player" + playerId + "numbersToken").get(1) != 0
								&& map9.get("player" + playerId + "numbersToken").get(0) == 0) {
							System.err.println("5 TO 5*1");
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) - 1);

							for (int i = 1; i <= 5; i++) {

								map9.get("player" + playerId + "numbersToken").set(0,
										map9.get("player" + playerId + "numbersToken").get(0) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "white" + map9.get("player" + playerId + "numbersToken").get(0)));

							}
						} else if (map9.get("player" + playerId + "numbersToken").get(2) != 0
								&& map9.get("player" + playerId + "numbersToken").get(1) == 0) {
							System.err.println("10 TO 2*5");
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							map9.get("player" + playerId + "numbersToken").set(2,
									map9.get("player" + playerId + "numbersToken").get(2) - 1);

							for (int i = 1; i <= 2; i++) {

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							}
						} else if (map9.get("player" + playerId + "numbersToken").get(3) != 0
								&& map9.get("player" + playerId + "numbersToken").get(2) == 0) {
							System.err.println("25 TO 2*10+5");
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));

							map9.get("player" + playerId + "numbersToken").set(3,
									map9.get("player" + playerId + "numbersToken").get(3) - 1);

							for (int i = 1; i <= 2; i++) {

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

							}

							map9.get("player" + playerId + "numbersToken").set(1,
									map9.get("player" + playerId + "numbersToken").get(1) + 1);
							map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
									+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

						} else if (map9.get("player" + playerId + "numbersToken").get(4) != 0
								&& map9.get("player" + playerId + "numbersToken").get(3) == 0) {
							System.err.println("100 TO 4*25");
							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "black" + map9.get("player" + playerId + "numbersToken").get(4)));

							map9.get("player" + playerId + "numbersToken").set(4,
									map9.get("player" + playerId + "numbersToken").get(4) - 1);

							for (int i = 1; i <= 4; i++) {

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));

							}

						}
					}
				}
			}
			if (value % 1000 != 0) {
				// deal avec les centaines
				int haha = value % 1000;
				value = value - haha;
				while (haha != 0) {

					if (haha >= 100 && haha <= 999) {
						if (haha == 100 && map9.get("player" + playerId + "numbersToken").get(4) != 0) {

							map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
									+ "black" + map9.get("player" + playerId + "numbersToken").get(2)));

							map9.get("player" + playerId + "numbersToken").set(2,
									map9.get("player" + playerId + "numbersToken").get(2) - 2);

							haha = haha - 100;
						}
						if (haha / 25 == 4) {

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 12));

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 11));

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 10));

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 9));
						} else if (haha / 25 == 3) {

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 12));

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 11));

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 10));

						} else if (haha / 25 == 2) {

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 12));

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 11));

						} else if (haha / 25 == 1) {

							map6.get("player" + playerId + "pane").getChildren()
									.removeAll(map7.get("player" + playerId + "green" + 12));

						} else if (map9.get("player" + playerId + "numbersToken").get(0) == 0) {
							if (map9.get("player" + playerId + "numbersToken").get(1) != 0
									&& map9.get("player" + playerId + "numbersToken").get(0) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) - 1);

								for (int i = 1; i <= 5; i++) {

									map9.get("player" + playerId + "numbersToken").set(0,
											map9.get("player" + playerId + "numbersToken").get(0) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "white"
													+ map9.get("player" + playerId + "numbersToken").get(0)));

								}
							} else if (map9.get("player" + playerId + "numbersToken").get(2) != 0
									&& map9.get("player" + playerId + "numbersToken").get(1) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

								map9.get("player" + playerId + "numbersToken").set(2,
										map9.get("player" + playerId + "numbersToken").get(2) - 1);

								for (int i = 1; i <= 2; i++) {

									map9.get("player" + playerId + "numbersToken").set(1,
											map9.get("player" + playerId + "numbersToken").get(1) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "red"
													+ map9.get("player" + playerId + "numbersToken").get(1)));

								}
							} else if (map9.get("player" + playerId + "numbersToken").get(3) != 0
									&& map9.get("player" + playerId + "numbersToken").get(2) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

								map9.get("player" + playerId + "numbersToken").set(3,
										map9.get("player" + playerId + "numbersToken").get(3) - 1);

								for (int i = 1; i <= 2; i++) {

									map9.get("player" + playerId + "numbersToken").set(2,
											map9.get("player" + playerId + "numbersToken").get(2) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "blue"
													+ map9.get("player" + playerId + "numbersToken").get(2)));

								}

								map9.get("player" + playerId + "numbersToken").set(1,
										map9.get("player" + playerId + "numbersToken").get(1) + 1);
								map6.get("player" + playerId + "pane").getChildren().add(map7.get("player" + playerId
										+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

							} else if (map9.get("player" + playerId + "numbersToken").get(4) != 0
									&& map9.get("player" + playerId + "numbersToken").get(3) == 0) {
								map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player"
										+ playerId + "black" + map9.get("player" + playerId + "numbersToken").get(4)));

								map9.get("player" + playerId + "numbersToken").set(4,
										map9.get("player" + playerId + "numbersToken").get(4) - 1);

								for (int i = 1; i <= 4; i++) {

									map9.get("player" + playerId + "numbersToken").set(3,
											map9.get("player" + playerId + "numbersToken").get(3) + 1);
									map6.get("player" + playerId + "pane").getChildren()
											.add(map7.get("player" + playerId + "green"
													+ map9.get("player" + playerId + "numbersToken").get(3)));

								}

							}
						}

					}
				}
			}
			if (value % 10000 == 1000) {
				for (int i = 1; i <= 12; i++) {

					map6.get("player" + playerId + "pane").getChildren().removeAll(map7
							.get("player" + playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

					map9.get("player" + playerId + "numbersToken").set(2,
							map9.get("player" + playerId + "numbersToken").get(2) - 1);

					map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get(
							"player" + playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

					map9.get("player" + playerId + "numbersToken").set(3,
							map9.get("player" + playerId + "numbersToken").get(3) - 1);
				}

				for (int i = 1; i <= 14; i++) {
					map6.get("player" + playerId + "pane").getChildren().removeAll(map7
							.get("player" + playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

					map9.get("player" + playerId + "numbersToken").set(1,
							map9.get("player" + playerId + "numbersToken").get(1) - 1);
				}
				for (int i = 1; i <= 10; i++) {

					map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get(
							"player" + playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

					map9.get("player" + playerId + "numbersToken").set(0,
							map9.get("player" + playerId + "numbersToken").get(0) - 1);

				}

				for (int i = 1; i <= 5; i++) {

					map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get(
							"player" + playerId + "black" + map9.get("player" + playerId + "numbersToken").get(4)));

					map9.get("player" + playerId + "numbersToken").set(4,
							map9.get("player" + playerId + "numbersToken").get(4) - 1);

				}
			}

		}
		if (value > (map8.get("player" + playerId + "total"))) {
			System.out.println("BET TOO HIGH!");
		}
		if (map8.get("player" + playerId + "total") <= 0) {
			System.out.println("NO MORE MONEY!");
		}
		System.out.println("money " + map8.get("player" + playerId + "total"));
	}

	private int value;

	private void one(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
					+ "white" + map9.get("player" + playerId + "numbersToken").get(0)));

			map9.get("player" + playerId + "numbersToken").set(0,
					map9.get("player" + playerId + "numbersToken").get(0) - 1);
		}
	}

	private void five(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
					+ "red" + map9.get("player" + playerId + "numbersToken").get(1)));

			map9.get("player" + playerId + "numbersToken").set(1,
					map9.get("player" + playerId + "numbersToken").get(1) - 1);
		}
	}
	
	private void ten(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
					+ "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

			map9.get("player" + playerId + "numbersToken").set(2,
					map9.get("player" + playerId + "numbersToken").get(2) - 1);
		}
	}

	private void twentyFive(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
					+ "green" + map9.get("player" + playerId + "numbersToken").get(3)));

			map9.get("player" + playerId + "numbersToken").set(3,
					map9.get("player" + playerId + "numbersToken").get(3) - 1);
		}
	}

	private void hundred(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(map7.get("player" + playerId
					+ "black" + map9.get("player" + playerId + "numbersToken").get(4)));

			map9.get("player" + playerId + "numbersToken").set(4,
					map9.get("player" + playerId + "numbersToken").get(4) - 1);
		}
	}
	
	private void value(){
		// divise chaque nombre
	}

	public void manual() {
		// Manual interface
		manualChipRed = new ImageView(new Image(getClass().getResourceAsStream("rouge.png")));
		manualChipRed.setLayoutX(159);
		manualChipRed.setLayoutY(640);
		manualChipBlack = new ImageView(new Image(getClass().getResourceAsStream("noir.png")));
		manualChipBlack.setLayoutX(570);
		manualChipBlack.setLayoutY(640);
		manualChipWhite = new ImageView(new Image(getClass().getResourceAsStream("blanc.png")));

		manualChipWhite.setLayoutX(22);
		manualChipWhite.setLayoutY(640);
		manualChipGreen = new ImageView(new Image(getClass().getResourceAsStream("vert.png")));
		manualChipGreen.setLayoutX(433);
		manualChipGreen.setLayoutY(640);
		manualChipBlue = new ImageView(new Image(getClass().getResourceAsStream("bleu.png")));

		manualChipBlue.setLayoutX(296);
		manualChipBlue.setLayoutY(640);

		test = new Button("Check");
		test.setDisable(true);
		test.setLayoutX(15);
		test.setLayoutY(20);
		test1 = new Button("Call");
		test1.setDisable(true);
		test1.setLayoutX(22);
		test1.setLayoutY(160);
		test2 = new Button("Fold");
		test2.setDisable(true);
		test2.setLayoutX(19);
		test2.setLayoutY(260);
		test3 = new Button("Raise");
		test3.setDisable(true);
		test3.setLayoutX(17);
		test3.setLayoutY(357);
		test4 = new Button("Bet");
		test4.setDisable(true);
		test4.setLayoutX(22);
		test4.setLayoutY(539);

		manualText1 = new String(
				"If there is no wager on the current betting round, a player may check. The act \nof checking passes the action to the next person, immediately clockwise from \nthe player. A check does not forfeit interest in the pot, only the current right \nto bet. If all active players check during a round of betting, the round is \nconsidered complete."
						+ "\n\n\nIf there has been a bet on the current round of poker play, a player may call. \nThe act of calling requires the player to match the current bet made by his or \nher opponent(s)."
						+ "\n\n\nThe act of folding forfeits all interest in the pot. A player who folds is not \nrequired or allowed to wager any further money during the current poker \nhand, but cannot win that hand either."
						+ "\n\n\nIf there has been a bet on the current betting round, a player may raise. The \nact of raising requires the poker player to match the current bet, and then \nmake a greater one. All subsequent players are required to call the raise or \nraise again (re-raise) to maintain interest in the pot. If there is not yet a wager \non the current betting round, a player may bet. If a player bets, the player \nimmediately clockwise from him or her (and any subsequent players) may fold, \nraise, or call."
						+ "\n\n\nIf there is not yet a wager on the current betting round, a player may bet. \nIf a player bets, the player immediately clockwise from him or her (and any \nsubsequent players) may fold, raise, or call.");
		manualText2 = new String("1$\t     5$\t10$\t    25$\t100$");

		explanation = new Label();
		explanation.setWrapText(true);
		explanation.setLayoutX(120);
		explanation.setLayoutY(10);
		explanation.setText(manualText1);
		explanation1 = new Label();
		explanation1.setWrapText(true);
		explanation1.setLayoutX(50);
		explanation1.setLayoutY(700);
		explanation1.setText(manualText2);

		manualPane = new Pane();
		manualPane.getChildren().addAll(test, test1, test2, test3, test4, explanation, explanation1, manualChipRed,
				manualChipBlack, manualChipGreen, manualChipWhite, manualChipBlue);

		manualScene = new Scene(manualPane, 700, 770);

		// Puts the application icon
		manualIcon = new Image(getClass().getResourceAsStream("manual.png"));

		manualStage = new Stage();
		manualStage.getIcons().add(manualIcon);
		manualStage.setResizable(false);
		manualStage.setScene(manualScene);
		manualStage.setAlwaysOnTop(true);
		manualStage.setTitle("Manual");

		map3.get("player1manual").setOnAction(e -> {
			buttonSound.play();
			manualStage.show();
		});
	}

	public void numberPanes() {
		map6 = new HashMap<String, Pane>();

		for (int i = 1; i <= totalPlayers; i++) {
			map6.put("player" + i + "pane", new Pane());
		}
	}

	public void player1Conditions() {
		map6.get("player1pane").setId("background");

		map6.get("player1pane").getChildren().addAll(map.get("player1bigFace1"), map.get("player1bigFace2"),
				map.get("player1smallBack1"), map.get("player1smallBack2"), map3.get("player1mainMenu"),
				map3.get("player1raise"), map3.get("player1fold"), map3.get("player1call"), map3.get("player1bet"),
				map3.get("player1check"), map3.get("player1manual"), map4.get("player1turn"),
				map4.get("player1numberTurn"));

		map3.get("player1fold").setOnAction(e -> {

			swoosh.play();
			map3.get("player1fold").setDisable(true);
			map3.get("player1call").setDisable(true);
			map3.get("player1raise").setDisable(true);
			map3.get("player1bet").setDisable(true);
			map3.get("player1check").setDisable(true);

			map3.get("player2call").setDisable(true);
			map3.get("player2fold").setDisable(true);
			map3.get("player2raise").setDisable(true);
			map3.get("player2bet").setDisable(true);
			map3.get("player2check").setDisable(true);

			map6.get("player1pane").getChildren().removeAll(map.get("player1bigFace1"), map.get("player1bigFace2"));
			map6.get("player1pane").getChildren().addAll(map.get("player1bigBack1"), map.get("player1bigBack2"),
					map.get("player1smallFace1"), map.get("player1smallFace2"));

			map6.get("player2pane").getChildren().removeAll(map.get("player2bigFace1"), map.get("player2bigFace2"));
			map6.get("player2pane").getChildren().addAll(map.get("player2bigBack1"), map.get("player2bigBack2"),
					map.get("player2smallFace1"), map.get("player2smallFace2"));

		});
		map3.get("player1bet").setOnAction(e -> {
			go();
		});

		map3.get("player1raise").setOnAction(e -> {
			go();
		});

		map3.get("player1check").setOnAction(e -> {
			playerId = 2;
			test();

			map6.get("player2pane").getChildren().addAll(map4.get("ask"));
			map6.get("player1pane").getChildren().addAll(map4.get("wait"));
		});

		map3.get("player1call").setOnAction(e -> {
			map3.get("player1fold").setDisable(true);
			map3.get("player1call").setDisable(true);
			map3.get("player1raise").setDisable(true);
			map3.get("player1bet").setDisable(true);
			map3.get("player1check").setDisable(true);
			map6.get("player1pane").getChildren().addAll(map4.get("wait"));
			map3.get("player2call").setDisable(false);
			map3.get("player2fold").setDisable(false);
			map3.get("player2raise").setDisable(false);
			map3.get("player2bet").setDisable(false);
			map3.get("player2check").setDisable(false);
			map6.get("player2pane").getChildren().addAll(map4.get("ask"));
		});

		mainScene = new Scene(map6.get("player1pane"), 1366, 720);
		stage.setTitle("Poker - " + player1Name);

		stage.setScene(mainScene);
	}

	public void go() {
		if (raiseId == 1) {
			map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("help"), raiseAmount);

			raiseId = 0;
		} else {
			map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("help"), raiseAmount);

			raiseId = 1;
		}
	}

	private int bet;

	public void player2Conditions() {

		map6.get("player2pane").setId("background");
		map6.get("player2pane").getChildren().addAll(map.get("player2bigFace1"), map.get("player2bigFace2"),
				map.get("player2smallBack1"), map.get("player2smallBack2"), map3.get("player2mainMenu"),
				map3.get("player2raise"), map3.get("player2fold"), map3.get("player2call"), map3.get("player2bet"),
				map3.get("player2check"), map3.get("player2manual"), map4.get("player2turn"),
				map4.get("player2numberTurn"));
		playe2scene = new Scene(map6.get("player2pane"), 1366, 720);

		if (turnId == 1) {
			map3.get("player2fold").setOnAction(e -> {

				swoosh.play();
				map3.get("player1fold").setDisable(true);
				map3.get("player1call").setDisable(true);
				map3.get("player1raise").setDisable(true);
				map3.get("player1bet").setDisable(true);
				map3.get("player1check").setDisable(true);

				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);
				map3.get("player2bet").setDisable(true);
				map3.get("player2check").setDisable(true);

				map6.get("player1pane").getChildren().removeAll(map.get("player1bigFace1"), map.get("player1bigFace2"));
				map6.get("player1pane").getChildren().addAll(map.get("player1bigBack1"), map.get("player1bigBack2"),
						map.get("player1smallFace1"), map.get("player1smallFace2"));

				map6.get("player2pane").getChildren().removeAll(map.get("player2bigFace1"), map.get("player2bigFace2"));
				map6.get("player2pane").getChildren().addAll(map.get("player2bigBack1"), map.get("player2bigBack2"),
						map.get("player2smallFace1"), map.get("player2smallFace2"));

			});
			map3.get("player2bet").setOnAction(e -> {
				go();
			});
			map3.get("player2raise").setOnAction(e -> {
				go();
			});
		} else if (turnId == 2) {

		} else if (turnId == 3) {

		} else if (turnId == 4) {

		} else if (turnId == 5) {

		}

		player2stage = new Stage();
		player2stage.setScene(playe2scene);
		player2stage.setTitle("Poker - " + player2Name);
		player2stage.setResizable(false);
		player2stage.getIcons().add(applicationIcon);
		player2stage.show();
	}

	public void firstTurn() {
		map6.get("player1pane").getChildren().addAll(map.get("dealer1Card1"), map.get("dealer1Card2"),
				map.get("dealer1Card3"));
		map6.get("player2pane").getChildren().addAll(map.get("dealer2Card1"), map.get("dealer2Card3"),
				map.get("dealer2Card2"));
	}

	public void secondTurn() {
		for (int i = 1; i < 3; i++) {
			map.get("dealer" + i + "Card1").setLayoutX(367.2857143);
			map.get("dealer" + i + "Card2").setLayoutX(530.1428571);
			map.get("dealer" + i + "Card3").setLayoutX(693);
		}
		map6.get("player1pane").getChildren().addAll(map.get("dealer1Card4"));
		map6.get("player2pane").getChildren().addAll(map.get("dealer2Card4"));
	}

	public void thirdTurn() {
		for (int i = 1; i < 3; i++) {
			map.get("dealer" + i + "Card1").setLayoutX(285.8571428);
			map.get("dealer" + i + "Card2").setLayoutX(448.7142857);
			map.get("dealer" + i + "Card3").setLayoutX(611.57142857142857142857142857143);
			map.get("dealer" + i + "Card4").setLayoutX(774.4285714);
		}
		map6.get("player1pane").getChildren().addAll(map.get("dealer1Card5"));
		map6.get("player2pane").getChildren().addAll(map.get("dealer2Card5"));
	}

	// ArrayLists for players tokens
	private Map<String, ArrayList<Integer>> map9;

	public void tokens() {

		numberPanes();
		map9 = new HashMap<String, ArrayList<Integer>>();
		map7 = new HashMap<String, ImageView>();
		String[] tokensVarNames = new String[] { "white", "red", "blue", "green", "black" };
		int[] numbersTokens = new int[] { 10, 14, 12, 12, 5 };
		int[] tokensLayoutX = new int[] { 90, 160, 230, 300, 370 };
		ArrayList<Integer> tokensLayoutY = new ArrayList<Integer>();

		for (int i = 1; i <= totalPlayers; i++) {
			map9.put("player" + i + "numbersToken", new ArrayList<Integer>());
			for (int j = 0; j < numbersTokens.length; j++) {
				map9.get("player" + i + "numbersToken").add(numbersTokens[j]);
			}
		}

		int max = 0;

		for (int e : numbersTokens) {
			if (e > max) {
				max = e;
			}
		}

		int inital = 672;
		for (int i = 0; i < max; i++) {
			tokensLayoutY.add(inital - 9);
			inital = inital - 9;
		}

		for (int e : tokensLayoutY) {
			System.out.println(e);
		}

		for (int j = 0; j < tokensVarNames.length; j++) {
			for (int i = 1; i <= totalPlayers; i++) {
				for (int t = 1; t <= numbersTokens[j]; t++) {
					map7.put("player" + i + tokensVarNames[j] + t,
							new ImageView(new Image(getClass().getResourceAsStream(tokensVarNames[j] + ".png"))));
					System.out.println("player" + i + tokensVarNames[j] + t);
					map7.get("player" + i + tokensVarNames[j] + t).setLayoutY(tokensLayoutY.get(t - 1));

					map7.get("player" + i + tokensVarNames[j] + t).setPreserveRatio(true);
					map7.get("player" + i + tokensVarNames[j] + t).setFitHeight(30);
					map7.get("player" + i + tokensVarNames[j] + t).setLayoutX(tokensLayoutX[j]);
					map6.get("player" + i + "pane").getChildren().add(map7.get("player" + i + tokensVarNames[j] + t));

				}
			}
		}

	}

	private Map<String, Integer> map8;

	public void variables() {
		map8 = new HashMap<String, Integer>();

		for (int i = 0; i <= totalPlayers; i++) {
			map8.put("player" + i + "total", new Integer(limit));
		}
	}

	public void labels() {

		map4 = new HashMap<String, Label>();
		String[] labelVarNames = new String[] { "warning", "turn", "help", "wait", "ask", "numberTurn", "warning1" };
		String[] labelText = new String[] { "The maximum is " + limit, player1Name + "'s turn", "Enter the amount",
				"Waiting for opponent", "The opponent called,\n do you want to call?", "Turn: " + turnId,
				"The minimum is " + bet };
		int[] labelLayoutX = new int[] { 1180, 620, 1187, 1183, 1100, 1300, 1180 };
		int[] labelLayoutY = new int[] { 630, 370, 555, 475, 408, 690, 630 };

		for (int j = 0; j < labelVarNames.length; j++) {
			for (int i = 1; i <= totalPlayers; i++) {
				if (j == 1 || j == 5) {
					map4.put("player" + i + labelVarNames[j], new Label(labelText[j]));
					map4.get("player" + i + labelVarNames[j]).setLayoutX(labelLayoutX[j]);
					map4.get("player" + i + labelVarNames[j]).setLayoutY(labelLayoutY[j]);
				}
			}
			if (j != 1 || j != 5) {
				map4.put(labelVarNames[j], new Label(labelText[j]));
				map4.get(labelVarNames[j]).setLayoutX(labelLayoutX[j]);
				map4.get(labelVarNames[j]).setLayoutY(labelLayoutY[j]);
			}
		}

		map4.get("warning").setId("warning");
		map4.get("warning1").setId("warning");
		map4.get("ask").setId("help");
		map4.get("wait").setId("help");
		map4.get("help").setId("help");
		map4.get("ask").setWrapText(true);
	}

	public void buttons() {
		map3 = new HashMap<String, Button>();
		map5 = new HashMap<String, MediaPlayer>();
		String[] buttonVarNames = new String[] { "mainMenu", "manual", "call", "fold", "bet", "raise", "check" };
		String[] buttonNames = new String[] { "Main Menu", "", "Call", "Fold", "Bet", "Raise", "Check" };

		for (int i = 1; i <= totalPlayers; i++) {

			for (int j = 0; j < buttonVarNames.length; j++) {
				nom = new String("player" + i + buttonVarNames[j]);
				map3.put("player" + i + buttonVarNames[j], new Button(buttonVarNames[j]));
				map3.get("player" + i + buttonVarNames[j]).setText(buttonNames[j]);
				map5.put("player" + i + buttonVarNames[j],
						new MediaPlayer(new Media(new File("click.mp3").toURI().toString())));
				map3.get("player" + i + buttonVarNames[j]).setOnAction(e -> {
					map5.get(nom).play();
				});
			}
			map3.get("player" + i + "mainMenu").setOnAction(e -> {
				map2.get("resultat1").clear();
				map2.get("resultat2").clear();
				map2.get("dealerResultat").clear();
				player2stage.close();
				if (manualStage.isShowing()) {
					manualStage.close();
				}
				start(stage);
				buttonSound.play();
			});
			map3.get("player" + i + "manual").setOnAction(e -> {
				buttonSound.play();
				manualStage.show();
			});

			map3.get("player" + i + "call").setLayoutX(1122);
			map3.get("player" + i + "call").setLayoutY(475);
			map3.get("player" + i + "mainMenu").setLayoutX(15);
			map3.get("player" + i + "mainMenu").setLayoutY(15);
			map3.get("player" + i + "manual").setLayoutX(1300);
			map3.get("player" + i + "manual").setLayoutY(8);
			map3.get("player" + i + "manual").setId("manual");
			map3.get("player" + i + "fold").setLayoutX(1120);
			map3.get("player" + i + "fold").setLayoutY(530);
			map3.get("player" + i + "bet").setLayoutX(1042);
			map3.get("player" + i + "bet").setLayoutY(555);
			map3.get("player" + i + "raise").setLayoutX(1118);
			map3.get("player" + i + "raise").setLayoutY(585);
			map3.get("player" + i + "check").setLayoutX(1035);
			map3.get("player" + i + "check").setLayoutY(500);
		}
	}

	public void cards() {

		premierDebut();

		// Reference to the images variables
		map = new HashMap<String, ImageView>();

		int x = 0;
		for (int j = 1; j <= totalPlayers; j++) {
			for (int i = 1; i < 53; i++) {
				map.put("player" + j + "card" + i,
						new ImageView(new Image(getClass().getResourceAsStream(i + ".png"))));
			}
			for (int i = 1; i <= totalPlayers; i++) {
				map.put("player" + j + "smallBack" + i,
						new ImageView(new Image(getClass().getResourceAsStream("back.png"))));
				map.get("player" + j + "smallBack" + i).setPreserveRatio(true);
				map.get("player" + j + "smallBack" + i).setFitHeight(50);
				map.get("player" + j + "smallBack" + i).setLayoutY(10);
				map.put("player" + j + "bigBack" + i,
						new ImageView(new Image(getClass().getResourceAsStream("back1.png"))));
				map.get("player" + j + "bigBack" + i).setPreserveRatio(true);
				map.get("player" + j + "bigBack" + i).setFitHeight(300);
				map.get("player" + j + "bigBack" + i).setLayoutY(400);
			}
			map.get("player" + j + "smallBack1").setLayoutX(640);
			map.get("player" + j + "smallBack2").setLayoutX(685);
			map.get("player" + j + "bigBack1").setLayoutX(456.3884);
			map.get("player" + j + "bigBack2").setLayoutX(703);

			for (int i = 1; i <= totalPlayers; i++) {
				map.put("player" + j + "bigFace" + i, new ImageView(
						new Image(getClass().getResourceAsStream(map2.get("resultat" + j).get(i - 1) + ".png"))));
				map.get("player" + j + "bigFace" + i).setPreserveRatio(true);
				map.get("player" + j + "bigFace" + i).setFitHeight(300);
				map.get("player" + j + "bigFace" + i).setLayoutY(400);
				map.put("player" + j + "smallFace" + i, new ImageView(
						new Image(getClass().getResourceAsStream(map2.get("resultat" + j).get(i - 1) + ".png"))));
				map.get("player" + j + "smallFace" + i).setPreserveRatio(true);
				map.get("player" + j + "smallFace" + i).setFitHeight(50);
				map.get("player" + j + "smallFace" + i).setLayoutY(10);
			}
			map.get("player" + j + "smallFace1").setLayoutX(640);
			map.get("player" + j + "smallFace2").setLayoutX(685);
			map.get("player" + j + "bigFace1").setLayoutX(456.3884);
			map.get("player" + j + "bigFace2").setLayoutX(703);
			for (int i = 0; i < 5; i++) {
				System.out.println("i + j : " + i + "+" + j);
				x = x + 1;
				dealer = "player" + j + "card" + map2.get("dealerResultat").get(i);
				var = "dealer" + j + "Card" + x;
				System.out.println(var);
				// map.replace(dealer, null);
				System.out.println();
				System.out.println(map.containsValue(map.get(dealer)));
				System.out.println(map.get(var));
				System.out.println(map.get(dealer));
				map.put(var, map.get(dealer));
				map.get(var).setPreserveRatio(true);
				map.get(var).setLayoutY(120);
				map.get(var).setFitHeight(200);
				if (i == 0) {
					map.get(var).setLayoutX(448.7142857);
				} else if (i == 1) {
					map.get(var).setLayoutX(611.57142857142857142857142857143);
				} else if (i == 2) {
					map.get(var).setLayoutX(774.4285714);
				} else if (i == 3) {
					map.get(var).setLayoutX(855.8571429);
				} else if (i == 4) {
					map.get(var).setLayoutX(937.2857143);
				}
			}
			x = 0;
		}
		System.out.println("lenght" + map.size());
	}

	public void selection() {
		// Selection of random number
		rand = 1 + (int) (Math.random() * ((52 - 1) + 1));
		while (map2.get("resultat1").contains(rand) || map2.get("resultat2").contains(rand)
				|| map2.get("dealerResultat").contains(rand)) {
			rand = 1 + (int) (Math.random() * ((52 - 1) + 1));
		}
	}

	public void premierDebut() {
		// Stores the location of the tokens
		map2 = new HashMap<String, ArrayList<Integer>>();
		map2.put("resultat2", new ArrayList<Integer>());
		map2.put("resultat1", new ArrayList<Integer>());
		map2.put("dealerResultat", new ArrayList<Integer>());

		// Distribution of the cards
		selection();
		map2.get("resultat1").add(rand);
		selection();
		map2.get("resultat2").add(rand);
		selection();
		map2.get("resultat1").add(rand);
		selection();
		map2.get("resultat2").add(rand);
		for (int i = 0; i < 5; i++) {
			selection();
			map2.get("dealerResultat").add(rand);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
