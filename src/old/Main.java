package old;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

	// List of the cards taken
	private ArrayList<Integer> resultat1, resultat2, dealerResultat;

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

	// Images of the cars
	private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13,
			card14, card15, card16, card17, card18, card19, card20, card21, card22, card23, card24, card25, card26,
			card27, card28, card29, card30, card31, card32, card33, card34, card35, card36, card37, card38, card39,
			card40, card41, card42, card43, card44, card45, card46, card47, card48, card49, card50, card51, card52,
			backCard1, backCard2, bigBackCard1, bigBackCard2, bigBackCard3, bigBackCard4;
	private Map<String, ImageView> map;

	// TextField to raise or to bet
	private TextField raiseAmount;

	// Random number
	private int rand;

	// Sound of Buttons
	private MediaPlayer buttonSound;

	@Override
	public void start(Stage primaryStage) {

		// Declaration of multiple important variables
		root = new Pane();
		stage = primaryStage;
		title = new Label("Poker Night Series");
		title.setLayoutY(10);
		title.setLayoutX(10);

		// Puts the application icon
		applicationIcon = new Image(getClass().getResourceAsStream("poker.png"));
		stage.getIcons().add(applicationIcon);

		// To get names
		singlePane = new Pane();
		singlePane.setId("back");
		networkPane = new Pane();
		networkScene = new Scene(networkPane, 100, 200);
		label2 = new Label("What is the first player's name?");
		label2.setLayoutX(30.0);
		label2.setLayoutY(5.0);
		label3 = new Label("What is the second player's name?");
		label3.setLayoutX(15.0);
		label3.setLayoutY(70.0);
		textField1 = new TextField();
		textField1.setLayoutX(50.0);
		textField1.setLayoutY(35.0);
		textField2 = new TextField();
		textField2.setLayoutX(50.0);
		textField2.setLayoutY(100.0);
		next = new Button("Next");

		// Back button
		back = new Button("Back");
		back.setOnAction(e -> start(stage));

		// Stores the location of the tokens
		resultat1 = new ArrayList<Integer>();
		resultat2 = new ArrayList<Integer>();
		dealerResultat = new ArrayList<Integer>();

		// Sound of button clicks
		buttonSound = new MediaPlayer(new Media(new File("click.mp3").toURI().toString()));

		// TextField1 sound and automatic upper case
		textField1.textProperty().addListener((ov, oldValue, newValue) -> {
			MediaPlayer md1 = new MediaPlayer(new Media(new File("type.mp3").toURI().toString()));
			md1.play();
			if (textField1.getText().length() == 1) {
				textField1.setText(newValue.toUpperCase());
			}
		});

		// TextField2 sound and automatic upper case
		textField2.textProperty().addListener((ov, oldValue, newValue) -> {
			MediaPlayer md1 = new MediaPlayer(new Media(new File("type.mp3").toURI().toString()));
			md1.play();
			if (textField2.getText().length() == 1) {
				textField2.setText(newValue.toUpperCase());
			}
		});

		// Multiplayer button
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

		

		// Main Menu button
		mainMenu = new Button("Main Menu");
		mainMenu.setLayoutX(15);
		mainMenu.setLayoutY(15);
		mainMenu.setOnAction(e -> {
			resultat1.clear();
			resultat2.clear();
			dealerResultat.clear();
			stage2.close();
			start(stage);
			buttonSound.play();
		});

		call = new Button("Call");
		call.setLayoutX(1122);
		call.setLayoutY(475);
		call.setOnAction(e -> {
			buttonSound.play();
		});

		fold = new Button("Fold");
		fold.setLayoutX(1120);
		fold.setLayoutY(530);
		fold.setOnAction(e -> {
			buttonSound.play();
		});

		raise = new Button("Raise");
		raise.setLayoutX(1118);
		raise.setLayoutY(585);
		raise.setOnAction(e -> {
			buttonSound.play();
		});

		bet = new Button("Bet");
		bet.setLayoutX(1042);
		bet.setLayoutY(555);

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

		manual = new Button();
		manual.setLayoutX(1300);
		manual.setLayoutY(8);
		manual.setOnAction(e -> {
			buttonSound.play();
			manualStage.show();
		});

		check = new Button("Check");
		check.setLayoutX(1035);
		check.setLayoutY(500);

		call.setDisable(true);
		raise.setDisable(true);
		fold.setDisable(true);
		check.setDisable(true);
		bet.setDisable(true);

		mainMenu1 = new Button("Main Menu");
		mainMenu1.setLayoutX(15);
		mainMenu1.setLayoutY(15);
		mainMenu1.setOnAction(e -> {
			resultat1.clear();
			resultat2.clear();
			dealerResultat.clear();
			stage2.close();
			if (manualStage.isShowing()) {
				manualStage.close();
			}
			start(stage);
			buttonSound.play();
		});

		call1 = new Button("Call");
		call1.setLayoutX(1122);
		call1.setLayoutY(475);
		call1.setOnAction(e -> {
			buttonSound.play();
		});

		fold1 = new Button("Fold");
		fold1.setLayoutX(1120);
		fold1.setLayoutY(530);
		fold1.setOnAction(e -> {
			buttonSound.play();
		});

		raise1 = new Button("Raise");
		raise1.setLayoutX(1118);
		raise1.setLayoutY(585);
		raise1.setOnAction(e -> {
			buttonSound.play();
		});

		bet1 = new Button("Bet");
		bet1.setLayoutX(1042);
		bet1.setLayoutY(555);

		check1 = new Button("Check");
		check1.setLayoutX(1035);
		check1.setLayoutY(500);

		manual1 = new Button();
		manual1.setLayoutX(1300);
		manual1.setLayoutY(8);
		manual1.setId("manual");
		manual1.setOnAction(e -> {
			buttonSound.play();

			manualStage.show();
		});

		next.setOnAction(e -> {
			player1Name = textField1.getText();
			player2Name = textField2.getText();
			buttonSound.play();
			cards();
			//dealerCard();
			
			//premierDebut();
			
			
			baseFirst();
			player2Conditions();
		});

		raiseAmount = new TextField();
		raiseAmount.setAlignment(Pos.CENTER);
		raiseAmount.setLayoutX(1211);
		raiseAmount.setLayoutY(588);
		raiseAmount.setPrefWidth(90);
		help = new Label("Enter the amount");
		help.setLayoutX(1187);
		help.setLayoutY(555);

		wait = new Label("Waiting for opponent");
		wait.setLayoutX(1183);
		wait.setLayoutY(475);

		ask = new Label("The opponent called,\n do you want to call?");
		ask.setWrapText(true);
		ask.setLayoutX(1100);
		ask.setLayoutY(408);

		int limit = 1000;
		warning = new Label("The maximum is " + limit);
		warning.setLayoutX(1180);
		warning.setLayoutY(630);

		textField1.setAlignment(Pos.CENTER);
		textField2.setAlignment(Pos.CENTER);

		raiseAmount.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > oldValue.intValue()) {
					char ch = raiseAmount.getText().charAt(oldValue.intValue());
					System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

					// Check if the new character is the number or other's
					if (!((ch >= '0' && ch <= '9') || ch == '.')) {

						// if it's not number then just setText to previous one
						raiseAmount.setText(raiseAmount.getText().substring(0, raiseAmount.getText().length() - 1));
					}

					// Check if the new character is greater than the limit
					if (Integer.valueOf(raiseAmount.getText()) >= limit && checkWarning) {
						raiseAmount.setText(String.valueOf(limit));
						group.getChildren().addAll(warning);
						checkWarning = false;
					} else if (Integer.valueOf(raiseAmount.getText()) >= limit) {
						raiseAmount.setText(String.valueOf(limit));
						group.getChildren().remove(warning);
						checkWarning = true;
					}
				}
			}

		});

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
		manual.setId("manual");
		explanation1.setId("dollars");
		manualPane.setId("background");
		help.setId("help");
		wait.setId("help");
		ask.setId("help");
		warning.setId("warning");
		Application.setUserAgentStylesheet(getClass().getResource("application.css").toExternalForm());

		root.getChildren().addAll(net, comp, title);
		mainScene = new Scene(root, 200, 156);
		stage.setScene(mainScene);
		stage.setTitle("Poker");
		stage.setResizable(false);
		stage.show();
	}

	private Boolean checkWarning = true;
	private Label turn1, turn, warning;

	private Pane group, group3, group4;

	private Label help, wait;

	public void baseFirst() {
		group = new Pane();
		group3 = new Pane();
		group4 = new Pane();

		turn1 = new Label(player1Name + "'s turn");
		turn1.setLayoutY(380);
		turn1.setLayoutX(600);

		

		group.setId("background");
		group3.setId("background");
		group4.setId("background");

		ImageView back = map.get("back1");
		ImageView back2 = map.get("back2");

		back.setPreserveRatio(true);
		back.setFitHeight(50);
		back.setLayoutX(685);
		back.setLayoutY(10);

		back2.setPreserveRatio(true);
		back2.setFitHeight(50);
		back2.setLayoutX(640);
		back2.setLayoutY(10);

		ImageView bigBack1 = map.get("bigBack1");
		ImageView bigBack2 = map.get("bigBack2");
		bigBack1.setPreserveRatio(true);
		bigBack1.setFitHeight(300);
		bigBack1.setLayoutX(456.3884);
		bigBack1.setLayoutY(400);

		bigBack2.setPreserveRatio(true);
		bigBack2.setFitHeight(300);
		bigBack2.setLayoutX(703);
		bigBack2.setLayoutY(400);

		ImageView bigBack3 = map.get("bigBack3");
		ImageView bigBack4 = map.get("bigBack4");
		bigBack3.setPreserveRatio(true);
		bigBack3.setFitHeight(300);
		bigBack3.setLayoutX(456.3884);
		bigBack3.setLayoutY(400);

		bigBack4.setPreserveRatio(true);
		bigBack4.setFitHeight(300);
		bigBack4.setLayoutX(703);
		bigBack4.setLayoutY(400);

		player1 = "player1card" + resultat1.get(0);
		System.out.println(player1);
		ImageView image = map.get(player1);
		image.setPreserveRatio(true);
		image.setFitHeight(300);
		image.setLayoutX(456.3884);
		image.setLayoutY(400);

		player1 = "player1card" + resultat1.get(1);
		System.out.println(player1);
		ImageView image2 = map.get(player1);
		image2.setPreserveRatio(true);
		image2.setFitHeight(300);
		image2.setLayoutX(703);
		image2.setLayoutY(400);
/*
		player1 = "card" + dealerResultat.get(0);
		System.out.println(player1);
		ImageView image5 = map.get(player1);
		image5.setPreserveRatio(true);
		image5.setFitHeight(200);
		image5.setLayoutX(448.7142857);
		image5.setLayoutY(120);

		player1 = "card" + dealerResultat.get(1);
		System.out.println(player1);
		ImageView image6 = map.get(player1);
		image6.setPreserveRatio(true);
		image6.setFitHeight(200);
		image6.setLayoutX(611.57142857142857142857142857143);
		image6.setLayoutY(120);

		player1 = "card" + dealerResultat.get(2);
		System.out.println(player1);
		ImageView image7 = map.get(player1);
		image7.setPreserveRatio(true);
		image7.setFitHeight(200);
		image7.setLayoutX(774.4285714);
		image7.setLayoutY(120);

		player1 = "card" + dealerResultat.get(3);
		System.out.println(player1);
		ImageView image8 = map.get(player1);
		image8.setPreserveRatio(true);
		image8.setFitHeight(200);
		image8.setLayoutX(855.8571429);
		image8.setLayoutY(120);

		player1 = "card" + dealerResultat.get(4);
		System.out.println(player1);
		ImageView image9 = map.get(player1);
		image9.setPreserveRatio(true);
		image9.setFitHeight(200);
		image9.setLayoutX(937.2857143);
		image9.setLayoutY(120);
*/
		// firstTurn();

		player2Layout();
		
		group.getChildren().addAll(image, image2, player2SmallBack1, player2SmallBack2, mainMenu1, raise1, fold1, call1, bet1, check1, manual1,
				turn1, map.get("dealer1Card1"), map.get("dealer1Card2"), map.get("dealer1Card3"), map.get("dealer1Card4"), map.get("dealer1Card5"));
		/*
		 * fold1.setOnAction(e->{
		 * 
		 * group.getChildren().removeAll( image5, image6, image7);
		 * 
		 * image8.setLayoutX(855.8571429); image7.setLayoutX(693);
		 * image6.setLayoutX(530.1428571); image5.setLayoutX(367.2857143);
		 * group.getChildren().addAll( image5, image6, image7, image8); });
		 * 
		 * 
		 * 
		 * call1.setOnAction(e ->{
		 * 
		 * group.getChildren().removeAll( image5, image6, image7, image8);
		 * 
		 * image8.setLayoutX(774.4285714);
		 * image7.setLayoutX(611.57142857142857142857142857143);
		 * image6.setLayoutX(448.7142857); image5.setLayoutX(285.8571428);
		 * group.getChildren().addAll( image5, image6, image7,image8, image9);
		 * });
		 */

		fold1.setOnAction(e -> {
			fold1.setDisable(true);
			call1.setDisable(true);
			raise1.setDisable(true);
			bet1.setDisable(true);
			check1.setDisable(true);

			call.setDisable(true);
			fold.setDisable(true);
			raise.setDisable(true);
			bet.setDisable(true);
			check.setDisable(true);

			image.setPreserveRatio(true);
			image.setFitHeight(50);
			image.setLayoutX(640);
			image.setLayoutY(10);

			image2.setPreserveRatio(true);
			image2.setFitHeight(50);
			image2.setLayoutX(685);
			image2.setLayoutY(10);

			player2Card1.setPreserveRatio(true);
			player2Card1.setFitHeight(50);
			player2Card1.setLayoutX(685);
			player2Card1.setLayoutY(10);

			player2Card2.setPreserveRatio(true);
			player2Card2.setFitHeight(50);
			player2Card2.setLayoutX(640);
			player2Card2.setLayoutY(10);

			group.getChildren().removeAll(image, image2);
			group.getChildren().addAll(bigBack1, bigBack2, player2Card1, player2Card2);

			group2.getChildren().removeAll(image, image2);
			group2.getChildren().addAll(bigBack3, bigBack4, image, image2);
		});

		raise1.setOnAction(e -> {
			go();
		});

		bet1.setOnAction(e -> {
			go();
		});

		check1.setOnAction(e -> {

		});

		call1.setOnAction(e -> {
			fold1.setDisable(true);
			call1.setDisable(true);
			raise1.setDisable(true);
			bet1.setDisable(true);
			check1.setDisable(true);
			group.getChildren().addAll(wait);
			call.setDisable(false);
			fold.setDisable(false);
			raise.setDisable(false);
			bet.setDisable(false);
			check.setDisable(false);
			group2.getChildren().addAll(ask);
		});

		mainScene = new Scene(group, 1366, 720);
		stage.setTitle("Poker - " + player1Name);

		stage.setScene(mainScene);
	}

	private int raiseId = 1;

	public void go() {

		if (raiseId == 1) {
			group.getChildren().addAll(help, raiseAmount);
			raiseId = 0;
		} else {
			group.getChildren().removeAll(help, raiseAmount);
			raiseId = 1;
		}
	}

	private Label ask;

	private Pane group2;
	private Stage stage2;
	private Scene baseSecond;

	private Button mainMenu, call, fold, raise, mainMenu1, call1, fold1, raise1, bet, bet1, check, check1, manual,
			manual1;

	private ImageView player2Card1, player2Card2;

	private ImageView dealer1Card1, dealer1Card2, dealer1Card3, dealer1Card4, dealer1Card5, dealer2Card1, dealer2Card2,
			dealer2Card3, dealer2Card4, dealer2Card5;

	private String dealer;

	private void dealerCard() {
		
		
		/*
		dealer = "card" + dealerResultat.get(0);
		dealer1Card1 = map.get(dealer);
		dealer1Card1.setPreserveRatio(true);
		dealer1Card1.setFitHeight(200);
		dealer1Card1.setLayoutX(448.7142857);
		dealer1Card1.setLayoutY(120);
		dealer2Card1 = dealer1Card1;

		dealer = "card" + dealerResultat.get(1);
		dealer1Card2 = map.get(dealer);
		dealer1Card2.setPreserveRatio(true);
		dealer1Card2.setFitHeight(200);
		dealer1Card2.setLayoutX(611.57142857142857142857142857143);
		dealer1Card2.setLayoutY(120);
		dealer2Card2 = dealer1Card2;

		dealer = "card" + dealerResultat.get(2);
		dealer1Card3 = map.get(dealer);
		dealer1Card3.setPreserveRatio(true);
		dealer1Card3.setFitHeight(200);
		dealer1Card3.setLayoutX(774.4285714);
		dealer1Card3.setLayoutY(120);
		dealer2Card3 = dealer1Card3;
		*/
	}

	private ImageView player2SmallBack1, player2SmallBack2;
	private String player2, player1;
	
	public void player2Layout(){
		player2SmallBack1 = map.get("back1");
		player2SmallBack1.setPreserveRatio(true);
		player2SmallBack1.setFitHeight(50);
		player2SmallBack1.setLayoutX(685);
		player2SmallBack1.setLayoutY(10);
		
		player2SmallBack2 = map.get("back2");
		player2SmallBack2.setPreserveRatio(true);
		player2SmallBack2.setFitHeight(50);
		player2SmallBack2.setLayoutX(640);
		player2SmallBack2.setLayoutY(10);

		player2 = "player2card" + resultat2.get(0);
		player2Card1 = map.get(player2);
		player2Card1.setPreserveRatio(true);
		player2Card1.setFitHeight(300);
		player2Card1.setLayoutX(703);
		player2Card1.setLayoutY(400);

		player2 = "player2card" + resultat2.get(1);
		player2Card2 = map.get(player2);
		player2Card2.setPreserveRatio(true);
		player2Card2.setFitHeight(300);
		player2Card2.setLayoutX(456.3884);
		player2Card2.setLayoutY(400);
	}
	
	public void player2Conditions() {
		group2 = new Pane();
		

		turn = new Label(player1Name + "'s turn");

		group2.setId("background");
		
		player2Layout();

		// firstTurn();
/*
		player2 = "card" + dealerResultat.get(0);
		dealer2Card1 = map.get(player2);
		dealer2Card1.setPreserveRatio(true);
		dealer2Card1.setFitHeight(200);
		dealer2Card1.setLayoutX(448.7142857);
		dealer2Card1.setLayoutY(120);

		player2 = "card" + dealerResultat.get(1);
		dealer2Card2 = map.get(player2);
		dealer2Card2.setPreserveRatio(true);
		dealer2Card2.setFitHeight(200);
		dealer2Card2.setLayoutX(611.57142857142857142857142857143);
		dealer2Card2.setLayoutY(120);

		player2 = "card" + dealerResultat.get(2);
		dealer2Card3 = map.get(player2);
		dealer2Card3.setPreserveRatio(true);
		dealer2Card3.setFitHeight(200);
		dealer2Card3.setLayoutX(774.4285714);
		dealer2Card3.setLayoutY(120);
*/
		group2.getChildren().addAll(player2Card2, player2Card1, player2SmallBack1, player2SmallBack2, mainMenu, raise, fold, call, bet, check,
				manual, map.get("dealer2Card1"), map.get("dealer2Card3"), map.get("dealer2Card2"), map.get("dealer2Card4"), map.get("dealer2Card5"));
		baseSecond = new Scene(group2, 1366, 720);

		stage2 = new Stage();
		stage2.setScene(baseSecond);
		stage2.setTitle("Poker - " + player2Name);
		stage2.setResizable(false);
		stage2.getIcons().add(applicationIcon);
		stage2.show();
	}

	private int turnId = 1;

	/*
	 * public void firstTurn() { dealerCard();
	 * group.getChildren().addAll(dealer1Card1, dealer1Card2, dealer1Card3);
	 * group2.getChildren().addAll(dealer2Card1, dealer2Card2, dealer2Card3); }
	 */
	public void otherTurns() {

	}

	public void lastTurn() {

	}

	public void cards() {
		
		premierDebut();
		/*
		// Mapping the image of the cards with a variable
		card1 = new ImageView(new Image(getClass().getResourceAsStream("1.png")));
		card2 = new ImageView(new Image(getClass().getResourceAsStream("2.png")));
		card3 = new ImageView(new Image(getClass().getResourceAsStream("3.png")));
		card4 = new ImageView(new Image(getClass().getResourceAsStream("4.png")));
		card5 = new ImageView(new Image(getClass().getResourceAsStream("5.png")));
		card6 = new ImageView(new Image(getClass().getResourceAsStream("6.png")));
		card7 = new ImageView(new Image(getClass().getResourceAsStream("7.png")));
		card8 = new ImageView(new Image(getClass().getResourceAsStream("8.png")));
		card9 = new ImageView(new Image(getClass().getResourceAsStream("9.png")));
		card10 = new ImageView(new Image(getClass().getResourceAsStream("10.png")));
		card11 = new ImageView(new Image(getClass().getResourceAsStream("11.png")));
		card12 = new ImageView(new Image(getClass().getResourceAsStream("12.png")));
		card13 = new ImageView(new Image(getClass().getResourceAsStream("13.png")));
		card14 = new ImageView(new Image(getClass().getResourceAsStream("14.png")));
		card15 = new ImageView(new Image(getClass().getResourceAsStream("15.png")));
		card16 = new ImageView(new Image(getClass().getResourceAsStream("16.png")));
		card17 = new ImageView(new Image(getClass().getResourceAsStream("17.png")));
		card18 = new ImageView(new Image(getClass().getResourceAsStream("18.png")));
		card19 = new ImageView(new Image(getClass().getResourceAsStream("19.png")));
		card20 = new ImageView(new Image(getClass().getResourceAsStream("20.png")));
		card21 = new ImageView(new Image(getClass().getResourceAsStream("21.png")));
		card22 = new ImageView(new Image(getClass().getResourceAsStream("22.png")));
		card23 = new ImageView(new Image(getClass().getResourceAsStream("23.png")));
		card24 = new ImageView(new Image(getClass().getResourceAsStream("24.png")));
		card25 = new ImageView(new Image(getClass().getResourceAsStream("25.png")));
		card26 = new ImageView(new Image(getClass().getResourceAsStream("26.png")));
		card27 = new ImageView(new Image(getClass().getResourceAsStream("27.png")));
		card28 = new ImageView(new Image(getClass().getResourceAsStream("28.png")));
		card29 = new ImageView(new Image(getClass().getResourceAsStream("29.png")));
		card30 = new ImageView(new Image(getClass().getResourceAsStream("30.png")));
		card31 = new ImageView(new Image(getClass().getResourceAsStream("31.png")));
		card32 = new ImageView(new Image(getClass().getResourceAsStream("32.png")));
		card33 = new ImageView(new Image(getClass().getResourceAsStream("33.png")));
		card34 = new ImageView(new Image(getClass().getResourceAsStream("34.png")));
		card35 = new ImageView(new Image(getClass().getResourceAsStream("35.png")));
		card36 = new ImageView(new Image(getClass().getResourceAsStream("36.png")));
		card37 = new ImageView(new Image(getClass().getResourceAsStream("37.png")));
		card38 = new ImageView(new Image(getClass().getResourceAsStream("38.png")));
		card39 = new ImageView(new Image(getClass().getResourceAsStream("39.png")));
		card40 = new ImageView(new Image(getClass().getResourceAsStream("40.png")));
		card41 = new ImageView(new Image(getClass().getResourceAsStream("41.png")));
		card42 = new ImageView(new Image(getClass().getResourceAsStream("42.png")));
		card43 = new ImageView(new Image(getClass().getResourceAsStream("43.png")));
		card44 = new ImageView(new Image(getClass().getResourceAsStream("44.png")));
		card45 = new ImageView(new Image(getClass().getResourceAsStream("45.png")));
		card46 = new ImageView(new Image(getClass().getResourceAsStream("46.png")));
		card47 = new ImageView(new Image(getClass().getResourceAsStream("47.png")));
		card48 = new ImageView(new Image(getClass().getResourceAsStream("48.png")));
		card49 = new ImageView(new Image(getClass().getResourceAsStream("49.png")));
		card50 = new ImageView(new Image(getClass().getResourceAsStream("50.png")));
		card51 = new ImageView(new Image(getClass().getResourceAsStream("51.png")));
		card52 = new ImageView(new Image(getClass().getResourceAsStream("52.png")));
		backCard1 = new ImageView(new Image(getClass().getResourceAsStream("back.png")));
		backCard2 = new ImageView(new Image(getClass().getResourceAsStream("back.png")));
		bigBackCard1 = new ImageView(new Image(getClass().getResourceAsStream("back1.png")));
		bigBackCard2 = new ImageView(new Image(getClass().getResourceAsStream("back1.png")));
		bigBackCard3 = new ImageView(new Image(getClass().getResourceAsStream("back1.png")));
		bigBackCard4 = new ImageView(new Image(getClass().getResourceAsStream("back1.png")));
*/
		// Reference to the images variables
		map = new HashMap<String, ImageView>();/*
		map.put("card1", new ImageView(new Image(getClass().getResourceAsStream("1.png"))));
		map.put("card2", new ImageView(new Image(getClass().getResourceAsStream("2.png"))));
		map.put("card3", new ImageView(new Image(getClass().getResourceAsStream("3.png"))));
		map.put("card4", new ImageView(new Image(getClass().getResourceAsStream("4.png"))));
		map.put("card5", new ImageView(new Image(getClass().getResourceAsStream("5.png"))));
		map.put("card6", new ImageView(new Image(getClass().getResourceAsStream("6.png"))));
		map.put("card7", new ImageView(new Image(getClass().getResourceAsStream("7.png"))));
		map.put("card8", new ImageView(new Image(getClass().getResourceAsStream("8.png"))));
		map.put("card9", new ImageView(new Image(getClass().getResourceAsStream("9.png"))));
		map.put("card10", new ImageView(new Image(getClass().getResourceAsStream("10.png"))));
		map.put("card11", new ImageView(new Image(getClass().getResourceAsStream("11.png"))));
		map.put("card12", new ImageView(new Image(getClass().getResourceAsStream("12.png"))));
		map.put("card13", new ImageView(new Image(getClass().getResourceAsStream("13.png"))));
		map.put("card14", new ImageView(new Image(getClass().getResourceAsStream("14.png"))));
		map.put("card15", new ImageView(new Image(getClass().getResourceAsStream("15.png"))));
		map.put("card16", new ImageView(new Image(getClass().getResourceAsStream("16.png"))));
		map.put("card17", new ImageView(new Image(getClass().getResourceAsStream("17.png"))));
		map.put("card18", new ImageView(new Image(getClass().getResourceAsStream("18.png"))));
		map.put("card19", new ImageView(new Image(getClass().getResourceAsStream("19.png"))));
		map.put("card20", new ImageView(new Image(getClass().getResourceAsStream("20.png"))));
		map.put("card21", new ImageView(new Image(getClass().getResourceAsStream("21.png"))));
		map.put("card22", new ImageView(new Image(getClass().getResourceAsStream("22.png"))));
		map.put("card23", new ImageView(new Image(getClass().getResourceAsStream("23.png"))));
		map.put("card24", new ImageView(new Image(getClass().getResourceAsStream("24.png"))));
		map.put("card25", new ImageView(new Image(getClass().getResourceAsStream("25.png"))));
		map.put("card26", new ImageView(new Image(getClass().getResourceAsStream("26.png"))));
		map.put("card27", new ImageView(new Image(getClass().getResourceAsStream("27.png"))));
		map.put("card28", new ImageView(new Image(getClass().getResourceAsStream("28.png"))));
		map.put("card29", new ImageView(new Image(getClass().getResourceAsStream("29.png"))));
		map.put("card30", new ImageView(new Image(getClass().getResourceAsStream("30.png"))));
		map.put("card31", new ImageView(new Image(getClass().getResourceAsStream("31.png"))));
		map.put("card32", new ImageView(new Image(getClass().getResourceAsStream("32.png"))));
		map.put("card33", new ImageView(new Image(getClass().getResourceAsStream("33.png"))));
		map.put("card34", new ImageView(new Image(getClass().getResourceAsStream("34.png"))));
		map.put("card35", new ImageView(new Image(getClass().getResourceAsStream("35.png"))));
		map.put("card36", new ImageView(new Image(getClass().getResourceAsStream("36.png"))));
		map.put("card37", new ImageView(new Image(getClass().getResourceAsStream("37.png"))));
		map.put("card38", new ImageView(new Image(getClass().getResourceAsStream("38.png"))));
		map.put("card39", new ImageView(new Image(getClass().getResourceAsStream("39.png"))));
		map.put("card40", new ImageView(new Image(getClass().getResourceAsStream("40.png"))));
		map.put("card41", new ImageView(new Image(getClass().getResourceAsStream("41.png"))));
		map.put("card42", new ImageView(new Image(getClass().getResourceAsStream("42.png"))));
		map.put("card43", new ImageView(new Image(getClass().getResourceAsStream("43.png"))));
		map.put("card44", new ImageView(new Image(getClass().getResourceAsStream("44.png"))));
		map.put("card45", new ImageView(new Image(getClass().getResourceAsStream("45.png"))));
		map.put("card46", new ImageView(new Image(getClass().getResourceAsStream("46.png"))));
		map.put("card47", new ImageView(new Image(getClass().getResourceAsStream("47.png"))));
		map.put("card48", new ImageView(new Image(getClass().getResourceAsStream("48.png"))));
		map.put("card49", new ImageView(new Image(getClass().getResourceAsStream("49.png"))));
		map.put("card50", new ImageView(new Image(getClass().getResourceAsStream("50.png"))));
		map.put("card51", new ImageView(new Image(getClass().getResourceAsStream("51.png"))));
		map.put("card52", new ImageView(new Image(getClass().getResourceAsStream("52.png"))));
		*/map.put("back1", new ImageView(new Image(getClass().getResourceAsStream("back.png"))));
		map.put("back2", new ImageView(new Image(getClass().getResourceAsStream("back.png"))));
		map.put("bigBack1", bigBackCard1);
		map.put("bigBack2", bigBackCard2);
		map.put("bigBack3", bigBackCard3);
		map.put("bigBack4", bigBackCard4);
		
		
		
		String var;
		ImageView img;
		int x = 0;
		for (int j = 1; j < 3; j++){
			for(int i = 1; i < 53; i++){
				map.put("player"+ j +"card" + i, new ImageView(new Image(getClass().getResourceAsStream(i+".png"))));
			}
			for (int i = 1; i <3;i++){
				map.put("player"+ j +"smallBack" + i,new ImageView(new Image(getClass().getResourceAsStream("back.png"))));
			}
		for( int i = 0; i < 5; i++){
			System.out.println("i + j : "+ i + "+" + j);
			x = x +1;
			dealer = "player"+ j + "card" + dealerResultat.get(i);
				var = "dealer" + j + "Card" + x;
				System.out.println(var);
				//map.replace(dealer, null);
				System.out.println();
				System.out.println(map.containsValue(map.get(dealer)));
				System.out.println(map.get(var));
				System.out.println(map.get(dealer));
				map.put(var, map.get(dealer));
				map.get(var).setPreserveRatio(true);
				map.get(var).setLayoutY(120);
				map.get(var).setFitHeight(200);
				if (i == 0){
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
	}

	public void selection() {
		
		// Selection of random number
		rand = 1 + (int) (Math.random() * ((52 - 1) + 1));
		while (resultat1.contains(rand) || resultat2.contains(rand) || dealerResultat.contains(rand)) {
			rand = 1 + (int) (Math.random() * ((52 - 1) + 1));
		}
	}

	public void premierDebut() {
		
		// Distribution of the cards
		selection();
		resultat1.add(rand);
		selection();
		resultat2.add(rand);
		selection();
		resultat1.add(rand);
		selection();
		resultat2.add(rand);
		for (int i = 0; i < 5; i++) {
			selection();
			dealerResultat.add(rand);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
