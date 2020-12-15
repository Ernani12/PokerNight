package application;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.RegrasJogo;
import javax.swing.JOptionPane;

public class IniciarProgramas extends Application {

    /*
    

Conhecendo a interface Map do Java
Os objetos “Map” confiam seus dados em um algoritmo hash (hash code). 
Esse algoritmo transforma uma grande quantidade de dados em uma pequena quantidade 
de informações, sendo que o mecanismo de busca se baseia na construção de índices.
A principal aplicaçao aqui é que  o map pode utilizar valores geneicos
e  guardar mais informaçoes para serem manipuladas
 */
    
    
	// icone principal, iagem principal
	private Image applicationIcon;

	// Menu de acoes principais
	private Stage stage;
	private Button comp, net;
	private Label title;
	private Pane root;
	private Scene mainScene;

	// elementos das cenas e controle 
	private Label label2, label3;
	private TextField textField1, textField2;
	private Button next, back;
	private Pane singlePane, networkPane;
	private Scene singleScene, networkScene;

	// Nomes dos jogadores
	private String player1Name, player2Name;

	// Éléments du manuel
	private Image manualIcon;
	private ImageView manualChipRed, manualChipBlack, manualChipWhite, manualChipGreen, manualChipBlue;
	private Stage manualStage;
	private Scene manualScene;
	private Pane manualPane;
	private Button test, test1, test2, test3, test4;
	private Label explanation, explanation1;
	private String manualText1, manualText2;

	// Guarda  imagnes das  cartas Dealer 
	private String dealer, var;
	private Map<String, ImageView> map;
	private HashMap<String, ArrayList<Integer>> map2;
	private int x;

	// Montante de apostas
	private TextField raiseAmount;

	// Numero aletorio 
	private int rand;

	// Botao de som
	private MediaPlayer buttonSound;

	// Segunda Janela Jogador 2
	private Stage player2stage;
	private Scene playe2scene;

	// Mostrar ou remover a opçãode apostas
	private int raiseId = 1;

	// contadores de turno e jogador 
	private int turnId = 1;
	private int playerId = 1;
	private int roundId = 1;

	// Montante inicial
	private int limit = 1000;

	// numeros de jogadores
	private int totalPlayers = 2;

	// Restrições dos  jogadores se podem checkar mao , aguardar  Foldar 
	private Boolean checkWarning = true, checkWarning1 = true, tooMuch = true, empty = true;

	// Variaveis que controlam Rotulos strings que aparecem menssagens
	private String[] labelVarNames, labelText;
	private int[] labelLayoutX, labelLayoutY;
	private Map<String, Label> map4;

	// variaveis de funcao de botoes 
	private Map<String, Button> map3;
	private String[] buttonVarNames, buttonNames;

	// Painel dos Jogadres  Controle
	private Map<String, Pane> map6;

	// Variaveeis tokens
	private int[] numbersTokens, maxTokens, tokensLayoutX;
	private String[] tokensVarNames;
	private ArrayList<Integer> tokensLayoutY;
	private int max;
	private Map<String, ImageView> map7;
	private Map<String, ArrayList<Integer>> map9;

	// Som de Jogo inicio
	private MediaPlayer swoosh;

	// controle de jogador para dar CALL
	private int player1value = 0, player2value = 0;

	// montante no POTE
	private int pot = 0;

	// Receve as informaçoes de regra de vencedor  de RegrasJogo.java
	String[] player1, player2;

	// Valor do montante do jogo dezena , centena unidade.
	private int unites, dizaines, centaines, mille;
	private int value;

	// minimo de uma aposta
	private int bet = 10;

	// quantidade de dinheiro de cada jogador pegando seu nome e quantidade
	private Map<String, Integer> map8;

	// Experimentação (clique no token para diminuir os tokens)
	private Map<String, ImageView> map10;

	// turno de cada jogador 
	private Boolean player2turn = true, player1turn = true;

	// Observar se jogador quer jogar novamente
	private Boolean click1 = false, click2 = false;

	// Determine se verificar ou preencher deve ser usado
	private Boolean check;

	// Som de digitaçao
	private MediaPlayer writeSound;

	// Ir para primeira cena
	private Main demande;

	// limites de jogadores quantidade
	private int p1 = limit, p2 = limit;

	// determinar se botao foi ativado
	private Boolean checkOff1 = true, checkOff2 = true, betOff1 = true, betOff2 = true, callOff1 = true,
			callOff2 = true, raiseOff2 = true;

	// PONTO de  Partida da aplicao
	@Override
	public void start(Stage primaryStage) {

		// Mudança de cena entre Os Main Duas aplicações  trocando informações
		demande = new Main();

		// Som para açao do jogador
		swoosh = new MediaPlayer(new Media(getClass().getResource("/application/swoosh.mp3").toExternalForm()));

		// Déclaration de multiples variables importantes
		root = new Pane();
		stage = primaryStage;
		title = new Label("Poker Night Series");
		title.setLayoutY(10);
		title.setLayoutX(10);

		// icones da aplicaçao
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

		// Button back
		back = new Button("Back");
		back.setOnAction(e -> {
			start(stage);
			play();
		});

		// Son des buttons
		buttonSound = new MediaPlayer(new Media(getClass().getResource("/application/click.mp3").toExternalForm()));

		// Son lorsque tu tape
		writeSound = new MediaPlayer(new Media(getClass().getResource("/application/type.mp3").toExternalForm()));

		// TextField1
		textField1 = new TextField();
		textField1.setLayoutX(50.0);
		textField1.setLayoutY(35.0);
		textField1.setAlignment(Pos.CENTER);
		textField1.textProperty().addListener((ov, oldValue, newValue) -> {
			MediaPlayer md1 = new MediaPlayer(new Media(getClass().getResource("/application/type.mp3").toExternalForm()));
			// Joue un son lorsque tu tape
			md1.play();
			// Rends la première majuscule
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
			MediaPlayer md1 = new MediaPlayer(new Media(getClass().getResource("/application/type.mp3").toExternalForm()));
			// Joue un son lorsque tu tape
			md1.play();
			// Rends la première majuscule
			if (textField2.getText().length() == 1) {
				textField2.setText(newValue.toUpperCase());
			}
		});

		// Button multiplayer
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
			singleScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke) {
					if (ke.getCode().equals(KeyCode.ESCAPE)) {
						start(stage);
						play();
					}
				}
			});
			stage.setScene(singleScene);
			play();
		});

		

		// quando tecla enter é tocada
		textField1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					textField2.requestFocus();
				}

			}
		});

		// Quando o botão ENTER é tocado, muda de cena
		textField2.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					player1Name = textField1.getText();
					player2Name = textField2.getText();
					labels();
					play();
					cards();
					player1Conditions();
					player2Conditions();
				}

			}
		});

		// Next Button
		next = new Button("Next");
		next.setOnAction(e -> {
			player1Name = textField1.getText();
			player2Name = textField2.getText();
			labels();
			play();
			cards();
			player1Conditions();
			player2Conditions();
		});

		// TextField de aposta/ aumentar
		raiseAmount = new TextField();
		raiseAmount.setAlignment(Pos.CENTER);
		raiseAmount.setLayoutX(1211);
		raiseAmount.setLayoutY(588);
		raiseAmount.setPrefWidth(90);

		// Funcoes multiplas
		variables();
		numberPanes();
		buttons();
		textRestrictions();
		manual();
		tokens();

		// Disable buttons pour player2
		map3.get("player2call").setDisable(true);
		map3.get("player2raise").setDisable(true);
		map3.get("player2fold").setDisable(true);
		map3.get("player2check").setDisable(true);
		map3.get("player2bet").setDisable(true);

		// Disable buttons pour player1
		map3.get("player1check").setDisable(true);
		map3.get("player1raise").setDisable(true);
		map3.get("player1call").setDisable(true);

		// Links para o CSS da  aplicaçao
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
		Application.setUserAgentStylesheet(getClass().getResource("application.css").toExternalForm());

		// Cria a pagina principal
		root.getChildren().addAll(comp, title);
		mainScene = new Scene(root, 215, 156);
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			// Determina teclas do teclado fazem 
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.DOWN)) {
					net.requestFocus();
				} else if (ke.getCode().equals(KeyCode.UP)) {
					comp.requestFocus();
				} else if (ke.getCode().equals(KeyCode.ENTER)) {
					if (net.isFocused()) {
						stage.setScene(networkScene);
						play();
					} else {
						back.setLayoutX(70.0);
						back.setLayoutY(155.0);
						next.setLayoutX(165.0);
						next.setLayoutY(155.0);
						singlePane.getChildren().addAll(label2, textField1, label3, textField2, next, back);
						singleScene = new Scene(singlePane, 300, 210);
						singleScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
							public void handle(KeyEvent ke) {
								if (ke.getCode().equals(KeyCode.ESCAPE)) {
									start(stage);
									play();
								}
							}
						});
						stage.setScene(singleScene);
						play();
					}
				} else if (ke.getCode().equals(KeyCode.ESCAPE)) {
					demande.start(primaryStage);
				}
			}
		});
		stage.setScene(mainScene);
		stage.setTitle("Poker");
		stage.setResizable(false);
		stage.show();
	}

	// Tocar botao de som
	public void play() {
		buttonSound.seek(Duration.ZERO);
		buttonSound.play();
	}

	// Som digitar
	public void tape() {
		writeSound.seek(Duration.ZERO);
		writeSound.play();
	}

	// Expérimentation --- BETA (N'évalue pas cette fonction)
	public void check() {
		// Place les buttons sur les côtés
		map10 = new HashMap<String, ImageView>();
		String[] test = new String[] { "white", "red", "blue", "green", "black" };
		int[] test1 = new int[] { 500, 540, 580, 620, 660 };

		for (int i = 1; i <= 5; i++) {
			map10.put("token" + i, new ImageView(new Image(getClass().getResourceAsStream(test[i - 1] + ".png"))));
			map10.get("token" + i).setLayoutX(1220);
			map10.get("token" + i).setPreserveRatio(true);
			map10.get("token" + i).setFitHeight(30);
			map10.get("token" + i).setLayoutY(test1[i - 1]);
			int j = i;
			map10.get("token" + i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (String.valueOf(test[j - 1]).equals("white")) {
						one(1);
					} else if (String.valueOf(test[j - 1]).equals("red")) {
						five(1);
					} else if (String.valueOf(test[j - 1]).equals("blue")) {
						ten(1);
					} else if (String.valueOf(test[j - 1]).equals("green")) {
						twentyFive(1);
					} else if (String.valueOf(test[j - 1]).equals("black")) {
						hundred(1);
					}
					event.consume();
				}
			});

		}
	}

	// Restricoes de texto para dizer sobre apostas
	public void textRestrictions() {
		// Vérifie chaque fois textField raiseAmount est changé
		raiseAmount.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tape();
				if (newValue.intValue() > oldValue.intValue()) {

					char ch = raiseAmount.getText().charAt(oldValue.intValue());

					// Vérifie 
					if (!(ch >= '0' && ch <= '9')) {
						// Si ce n'est pas un nombre, remplace ce caractère avec le précédent
						raiseAmount.setText(raiseAmount.getText().substring(0, raiseAmount.getText().length() - 1));
					}

					// Enlève l'avertissement
					if (checkWarning == false) {
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("warning"));
						checkWarning = true;
					}
					if (checkWarning1 == false) {
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("warning1"));
						checkWarning1 = true;
					}
					if (tooMuch == false) {
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("tooMuch"));
						tooMuch = true;
					}
					if (empty == false) {
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("empty"));
						empty = true;
					}
				}

			}

		});

		// Verifica se enter foi pressionado 
		raiseAmount.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					tape();

					if (String.valueOf(raiseAmount.getText()).equals("")) {
						// S'il y a rien, mets la mise
						raiseAmount.setText(String.valueOf(bet));
					} else if (Integer.valueOf(raiseAmount.getText()) > limit && checkWarning) {
						// Vérifie si le nouveau montant est plus que la
						// limite
						raiseAmount.setText(String.valueOf(limit));
						map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("warning"));
						checkWarning = false;
					} else if (Integer.valueOf(raiseAmount.getText()) < bet && checkWarning1) {
						// Vérifie si le nouveau montant est plus que la mise
						raiseAmount.setText(String.valueOf(bet));
						map4.get("warning1").setText("The minimum is " + bet);
						map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("warning1"));
						checkWarning1 = false;
					} else if (playerId == 1 && Integer.valueOf(raiseAmount.getText()) < player2value
							&& player2turn == false) {
						// Vérifie si le nouveau montant est plus que la limite
						raiseAmount.setText(String.valueOf(player2value));
						map4.get("warning1").setText("The minimum is " + player2value);
						map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("warning1"));
						checkWarning1 = false;
					} else if (map8.get("player" + playerId + "total") <= 0) {
						// Vérifie si le joueur a de l'argent

						// Joueur perd
						if (playerId == 1) {
							fold1();
						} else {
							fold2();
						}

						map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("empty"));
						empty = false;
					}  else {

						if (player2turn == false && playerId == 2) {
							// Change la variable 'valeur' pour que le joueur2 ne
							// perd pas de l'argent
							System.out.println("#1");
							value = Integer.valueOf(raiseAmount.getText()) - player2value;
						} else if (player1turn == false && playerId == 1) {
							// Change la variable 'valeur' pour que le joueur1 ne
							// perd pas de l'argent
							System.out.println("#2");
							value = Integer.valueOf(raiseAmount.getText()) - player1value;
						} else {
							// Change la variable 'valeur'
                                                        
                                                     
                                                        
							System.out.println("#3");
                         
                                                        
							value = Integer.valueOf(raiseAmount.getText());
						}
						
						if (value > (map8.get("player" + playerId + "total"))) {
							// Vérifie si le joueur peux se permette ce nouveau montant
							raiseAmount.setText(String.valueOf(player2value));
							map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("tooMuch"));
							tooMuch = false;
						} 
						
						// Enlève l'avertissement
						if (checkWarning == false) {
							map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("warning"));
							checkWarning = true;
						}
						if (checkWarning1 == false) {
							map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("warning1"));
							checkWarning1 = true;
						}
						if (tooMuch == false) {
							map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("tooMuch"));
							tooMuch = true;
						}
						if (empty == false) {
							map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("empty"));
							empty = true;
						}

						// Enlève le textField et le label
						map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("help"), raiseAmount);

						//
						test();
					}
				}
			}
		});

	}

	// Compara mudanças das ações
	// faits
	public void test() {

            
            
            
		// Change le mise si le joueur entre une valeur plus grande
		if (Integer.valueOf(raiseAmount.getText()) > bet) {
			bet = Integer.valueOf(raiseAmount.getText());
			raiseAmount.setText(String.valueOf(bet));
		}
		
		if (player2turn == false && playerId == 2) {
			// Change la variable 'valeur' pour que le joueur2 ne
			// perd pas de l'argent
			System.out.println("#1");
			value = Integer.valueOf(raiseAmount.getText()) - player2value;
		} else if (player1turn == false && playerId == 1) {
			// Change la variable 'valeur' pour que le joueur1 ne
			// perd pas de l'argent
			System.out.println("#2");
			value = Integer.valueOf(raiseAmount.getText()) - player1value;
		} else {
			// Change la variable 'valeur'
			System.out.println("#3");
			value = Integer.valueOf(raiseAmount.getText());
                        
               
		}

		if (turnId == 1) {
			if (playerId == 1) {
				player1turn = false;
				player1value = Integer.valueOf(raiseAmount.getText());

				map3.get("player1fold").setDisable(true);
				map3.get("player1bet").setDisable(true);
				map3.get("player1call").setDisable(true);
				map3.get("player1raise").setDisable(true);
				map3.get("player2call").setDisable(false);
				map3.get("player2fold").setDisable(false);
				map3.get("player2raise").setDisable(false);

				cost();
				if (callOff1) {
					go();
				}
				player2();

				if (p1 - map8.get("player1total") == p2 - map8.get("player2total")) {
					firstTurn();
				} else {
					playerId = 2;
				}
			} else if (playerId == 2) {
				cost();
				player1();
				bet = 10;

				player2turn = false;

				player2value = Integer.valueOf(raiseAmount.getText());

				map3.get("player1fold").setDisable(false);
				map3.get("player1bet").setDisable(false);
				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);

				playerId = 1;

				if (callOff2) {
					go();
				}
				
				System.out.println("p1" + (p1 - map8.get("player1total")));
				System.out.println("p2" + (p2 - map8.get("player2total")));

				if (p1 - map8.get("player1total") == p2 - map8.get("player2total")) {
					firstTurn();
				}

				if (!raiseOff2) {
					map6.get("player2pane").getChildren().removeAll(raiseAmount, map4.get("help"));
				}

				if (!raiseOff2 && !map8.get("player1total").equals(map8.get("player2total"))) {
					map3.get("player1call").setDisable(false);
					map3.get("player1bet").setDisable(true);
					map3.get("player1raise").setDisable(false);
				}
			}
		} else {
			if (playerId == 1) {
				cost();
				player2();

				player1turn = false;
				player1value = Integer.valueOf(raiseAmount.getText());

				if (callOff1) {
					go();
				}

				map3.get("player1call").setDisable(true);
				map3.get("player1fold").setDisable(true);
				map3.get("player1raise").setDisable(true);
				map3.get("player1bet").setDisable(true);
				map3.get("player1check").setDisable(true);

				if (!checkOff1) {
					map3.get("player2call").setDisable(false);
					map3.get("player2fold").setDisable(false);
					map3.get("player2raise").setDisable(false);
				}

				if (!betOff1) {
					map3.get("player2call").setDisable(false);
					map3.get("player2fold").setDisable(false);
					map3.get("player2raise").setDisable(false);
				}

				if (!callOff1) {
					map3.get("player1call").setDisable(false);
					map3.get("player1fold").setDisable(false);
					map3.get("player1raise").setDisable(false);
				}

				if (p1 - map8.get("player1total") == p2 - map8.get("player2total")) {
					if (turnId == 2) {
						secondTurn();
					} else if (turnId == 3) {
                                            
                      
                                            
						thirdTurn();

					} else if (turnId == 4) {

						finish();
					}
				} else {
					playerId = 2;
				}
			} else if (playerId == 2) {
				cost();
				player1();

				map3.get("player2call").setDisable(true);
				map3.get("player2fold").setDisable(true);
				map3.get("player2raise").setDisable(true);
				map3.get("player2bet").setDisable(true);
				map3.get("player2check").setDisable(true);

				player2turn = false;
				player2value = Integer.valueOf(raiseAmount.getText());

				if (callOff2) {
					go();
				}

				if (!checkOff1) {
					map6.get("player2pane").getChildren().remove(map4.get("ask"));
					map6.get("player1pane").getChildren().remove(map4.get("wait"));
				}

				if (!betOff2 && !checkOff1) {

					map3.get("player1call").setDisable(false);
					map3.get("player1fold").setDisable(false);
					map3.get("player1raise").setDisable(false);
				}

				if (!callOff1) {
					map3.get("player1call").setDisable(false);
					map3.get("player1fold").setDisable(false);
					map3.get("player1raise").setDisable(false);
				}

				playerId = 1;

				if (p1 - map8.get("player1total") == p2 - map8.get("player2total")) {
					if (turnId == 2) {
						secondTurn();
					} else if (turnId == 3) {
						thirdTurn();
					} else if (turnId == 4) {
						finish();
					}
				}

				if (!raiseOff2) {
					map6.get("player2pane").getChildren().removeAll(raiseAmount, map4.get("help"));
				}

				if (!raiseOff2 && p1 - map8.get("player1total") != p2 - map8.get("player2total")) {
					map3.get("player1call").setDisable(false);
					map3.get("player1fold").setDisable(false);
					map3.get("player1raise").setDisable(false);
					map3.get("player1bet").setDisable(true);
					map3.get("player1check").setDisable(true);
				}
			}
		}
                
            

       
		// Atualizar pote
		pot = (limit * 2) - (map8.get("player1total") + map8.get("player2total"));
		map4.get("pot1").setText("Pot: " + pot + "$");
		map4.get("pot2").setText("Pot: " + pot + "$");
                
                
	}

	// termina o jogo
	private void finish() {
                  
            
      
                
		// Atauliza pote
		pot = (limit * 2) - (map8.get("player1total") + map8.get("player2total"));
		map4.get("pot1").setText("Pot: " + pot + "$");
		map4.get("pot2").setText("Pot: " + pot + "$");

		// Bloque/débloque les buttons
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

                    // Remove cartas
		map6.get("player2pane").getChildren().addAll(map.get("player1smallFace1"), map.get("player1smallFace2"),
				map3.get("player2nextRound"));
		map6.get("player1pane").getChildren().addAll(map.get("player2smallFace1"), map.get("player2smallFace2"),
				map3.get("player1nextRound"));

                
              
                
		// Évalue la plus haute main
		player1 = new String[] {};
		player1 = RegrasJogo.evaluate(map2.get("resultat1"), map2.get("dealerResultat"));
		player2 = new String[] {};
		player2 = RegrasJogo.evaluate(map2.get("resultat2"), map2.get("dealerResultat"));

		// Compare les mains des joueurs
		String gagnant = repeat(player1[0], player2[0]);

		// Mise à jour des labels de gagnant
		map6.get("player1pane").getChildren().removeAll(map4.get("player1turn"));
		map6.get("player2pane").getChildren().removeAll(map4.get("player2turn"));
		if (String.valueOf(gagnant).equals("player1")) {
			map4.get("player1turn").setText(player1Name + " WON");
			map4.get("player2turn").setText(player1Name + " WON");
		} else if (String.valueOf(gagnant).equals("player2")) {
			map4.get("player1turn").setText(player2Name + " WON");
			map4.get("player2turn").setText(player2Name + " WON");
		} else {
			map4.get("player1turn").setText("TIE");
			map4.get("player2turn").setText("TIE");
		}
		map6.get("player1pane").getChildren().addAll(map4.get("player1turn"));
		map6.get("player2pane").getChildren().addAll(map4.get("player2turn"));
                
               
          
	}

	// Determinaçao de importancia na mao do jogador
	public int check(String sort) {
		int ordre = 0;

		if (String.valueOf(sort).equals("royalFlush")) {
			ordre = 1;
		} else if (String.valueOf(sort).equals("straightFlush")) {
			ordre = 2;
		} else if (String.valueOf(sort).equals("fourOfAKind")) {
			ordre = 3;
		} else if (String.valueOf(sort).equals("fullHouse")) {
			ordre = 4;
		} else if (String.valueOf(sort).equals("flush")) {
			ordre = 5;
		} else if (String.valueOf(sort).equals("straight")) {
			ordre = 6;
		} else if (String.valueOf(sort).equals("threeOfAKind")) {
			ordre = 7;
		} else if (String.valueOf(sort).equals("twoPair")) {
			ordre = 8;
		} else if (String.valueOf(sort).equals("onePair")) {
			ordre = 9;
		} else if (String.valueOf(sort).equals("highCards")) {
			ordre = 10;
		}

		return ordre;
	}

	// Appelle la fonction check deux fois
	public String repeat(String un, String deux) {
		return win(check(un), check(deux));
	}

	// Compare les résultats des joueurs & détermine le gagnant
	public String win(int un, int deux) {

		String name = "";
                
		// Détermine qui gagne
		if (un > deux) {
			name = "player2";
		} else if (un == deux) {
			if (Integer.valueOf(player1[2]) > Integer.valueOf(player2[2])) {
				name = "player1";


			} else if (Integer.valueOf(player1[2]) < Integer.valueOf(player2[2])) {
				name = "player2";

			} else if (Integer.valueOf(player1[1]) < Integer.valueOf(player2[1])) {
				name = "player2";

			} else if (Integer.valueOf(player1[1]) > Integer.valueOf(player2[1])) {
				name = "player1";

			} else {

				name = "tie";
			}
		} else if (un < deux) {
			name = "player1";
		}

		if (String.valueOf(name).equals("player1")) {

			// Ajoute l'argent et change les labels
			map6.get("player1pane").getChildren().removeAll(map4.get("player1cash1"));
			map6.get("player2pane").getChildren().removeAll(map4.get("player1cash2"));
			map8.put("player1total", map8.get("player1total") + pot);

			map4.get("player1cash1").setText("You: " + map8.get("player1total") + "$");
			map4.get("player1cash2").setText("Opponent: " + map8.get("player1total") + "$");
			map6.get("player1pane").getChildren().add(map4.get("player1cash1"));
			map6.get("player2pane").getChildren().add(map4.get("player1cash2"));

			// Mise à jour des jetons
			playerId = 1;
			value(pot);
			add("white", unites);
			dizaines = dizaines / 10;
			add("blue", dizaines);
			centaines = centaines / 100;
			add("black", centaines);
			mille = mille / 1000;
			for (int i = 1; i <= mille; i++) {
				add("black", 10);
			}
		} else if (String.valueOf(name).equals("player2")) {

			// Ajoute l'argent et change les labels
			map6.get("player2pane").getChildren().removeAll(map4.get("player2cash1"));
			map6.get("player1pane").getChildren().removeAll(map4.get("player2cash2"));
			map8.put("player2total", map8.get("player2total") + pot);

			map4.get("player2cash1").setText("You: " + map8.get("player2total") + "$");
			map4.get("player2cash2").setText("Opponent: " + map8.get("player2total") + "$");
			map6.get("player2pane").getChildren().add(map4.get("player2cash1"));
			map6.get("player1pane").getChildren().add(map4.get("player2cash2"));

			// Mise à jour des jetons
			playerId = 2;
			value(pot);
			add("white", unites);
			dizaines = dizaines / 10;
			add("blue", dizaines);
			centaines = centaines / 100;
			add("black", centaines);
			for (int i = 1; i <= mille; i++) {
				add("black", 10);
			}
		}

		// Mise à jour du pot
		pot = 0;
		map4.get("pot1").setText("Pot : " + pot + "$");
		map4.get("pot2").setText("Pot : " + pot + "$");

		return name;
	}

	// Change le nom du joueur courant dans le jeu du 2e joueur
	private void player2() {
		map6.get("player1pane").getChildren().removeAll(map4.get("player1turn"));
		map6.get("player2pane").getChildren().removeAll(map4.get("player2turn"));
		map4.get("player1turn").setText(player2Name + "'s turn");
		map4.get("player2turn").setText(player2Name + "'s turn");
		map6.get("player1pane").getChildren().addAll(map4.get("player1turn"));
		map6.get("player2pane").getChildren().addAll(map4.get("player2turn"));
	}

	// Change le nom du joueur courant dans le jeu du 1er joueur
	private void player1() {
		map6.get("player1pane").getChildren().removeAll(map4.get("player1turn"));
		map6.get("player2pane").getChildren().removeAll(map4.get("player2turn"));
		map4.get("player1turn").setText(player1Name + "'s turn");
		map4.get("player2turn").setText(player1Name + "'s turn");
		map6.get("player1pane").getChildren().addAll(map4.get("player1turn"));
		map6.get("player2pane").getChildren().addAll(map4.get("player2turn"));
	}

	// Change les jetons par rapport au coût dans laquelle le joueur a entré
	public void cost() {
		check = true;

		// Vérifie si le joueur a de l'argent
		if (map8.get("player" + playerId + "total") != 0) {

			// Vérifie si le montant est plus plus petit ou égal au montant
			// d'argent total
			if (value <= (map8.get("player" + playerId + "total"))) {

				map8.put("player" + playerId + "total", map8.get("player" + playerId + "total") - value);
				value(value);

				// Traite les milliers
				if (mille != 0) {
					one(10);
					five(14);
					ten(12);
					twentyFive(12);
					hundred(5);
				}

				// Traite les centaines
				if (centaines != 0) {
					value = value - centaines;
					while (centaines != 0) {
						if (centaines == 100) {
							if (hundredNumber(1)) {
								centaines = centaines - 100;
								hundred(1);
							} else if (tenNumber(3) && oneNumber(10) && fiveNumber(12)) {
								centaines = centaines - 100;
								ten(3);
								five(12);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 200) {
							if (hundredNumber(2)) {
								centaines = centaines - 200;
								hundred(2);
							} else if (hundredNumber(1) && tenNumber(3) && oneNumber(10) && fiveNumber(12)) {
								centaines = centaines - 200;
								hundred(1);
								ten(3);
								five(12);
								one(10);
							} else if (tenNumber(12) && oneNumber(10) && fiveNumber(14)) {
								centaines = centaines - 200;
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 300) {
							if (hundredNumber(3)) {
								centaines = centaines - 300;
								hundred(3);
							} else if (hundredNumber(2) && tenNumber(3) && oneNumber(10) && fiveNumber(12)) {
								centaines = centaines - 300;
								hundred(2);
								ten(3);
								five(12);
								one(10);
							} else if (hundredNumber(1) && tenNumber(12) && oneNumber(10) && fiveNumber(14)) {
								centaines = centaines - 300;
								hundred(1);
								ten(12);
								five(14);
								one(10);
							} else if (tenNumber(12) && oneNumber(10) && fiveNumber(14) && twentyFiveNumber(4)) {
								centaines = centaines - 300;
								twentyFive(4);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 400) {
							if (hundredNumber(4)) {
								centaines = centaines - 400;
								hundred(4);
							} else if (hundredNumber(3) && tenNumber(3) && oneNumber(10) && fiveNumber(12)) {
								centaines = centaines - 400;
								hundred(3);
								ten(3);
								five(12);
								one(10);
							} else if (hundredNumber(2) && tenNumber(12) && oneNumber(10) && fiveNumber(14)) {
								centaines = centaines - 400;
								hundred(2);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(1) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(4)) {
								centaines = centaines - 400;
								hundred(1);
								twentyFive(4);
								ten(12);
								five(14);
								one(10);
							} else if (tenNumber(12) && oneNumber(10) && fiveNumber(14) && twentyFiveNumber(8)) {
								centaines = centaines - 400;
								twentyFive(8);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 500) {
							if (hundredNumber(5)) {
								centaines = centaines - 500;
								hundred(5);
							} else if (hundredNumber(4) && tenNumber(3) && oneNumber(10) && fiveNumber(12)) {
								centaines = centaines - 500;
								hundred(4);
								ten(3);
								five(12);
								one(10);
							} else if (hundredNumber(3) && tenNumber(12) && oneNumber(10) && fiveNumber(14)) {
								centaines = centaines - 500;
								hundred(3);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(2) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(4)) {
								centaines = centaines - 500;
								hundred(2);
								twentyFive(4);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(1) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(8)) {
								centaines = centaines - 500;
								hundred(1);
								twentyFive(8);
								ten(12);
								five(14);
								one(10);
							} else if (tenNumber(12) && oneNumber(10) && fiveNumber(14) && twentyFiveNumber(12)) {
								centaines = centaines - 500;
								twentyFive(12);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 600) {
							if (hundredNumber(5) && twentyFiveNumber(4)) {
								centaines = centaines - 600;
								hundred(5);
								twentyFive(4);
							} else if (hundredNumber(5) && tenNumber(3) && oneNumber(10) && fiveNumber(12)) {
								centaines = centaines - 600;
								hundred(5);
								ten(3);
								five(12);
								one(10);
							} else if (hundredNumber(4) && tenNumber(12) && oneNumber(10) && fiveNumber(14)) {
								centaines = centaines - 600;
								hundred(4);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(3) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(4)) {
								centaines = centaines - 600;
								hundred(3);
								twentyFive(4);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(2) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(8)) {
								centaines = centaines - 600;
								hundred(2);
								twentyFive(8);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(1) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(12)) {
								centaines = centaines - 600;
								hundred(1);
								twentyFive(12);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 700) {
							if (hundredNumber(5) && twentyFiveNumber(8)) {
								centaines = centaines - 700;
								hundred(5);
								twentyFive(8);
							} else if (hundredNumber(5) && tenNumber(12) && oneNumber(10) && fiveNumber(14)) {
								centaines = centaines - 700;
								hundred(5);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(4) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(4)) {
								centaines = centaines - 700;
								hundred(4);
								twentyFive(4);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(3) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(8)) {
								centaines = centaines - 700;
								hundred(3);
								twentyFive(8);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(2) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(12)) {
								centaines = centaines - 700;
								hundred(2);
								twentyFive(12);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 800) {
							if (hundredNumber(5) && twentyFiveNumber(12)) {
								centaines = centaines - 800;
								hundred(5);
								twentyFive(12);
							} else if (hundredNumber(5) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(4)) {
								centaines = centaines - 800;
								hundred(5);
								twentyFive(4);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(4) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(8)) {
								centaines = centaines - 800;
								hundred(4);
								twentyFive(8);
								ten(12);
								five(14);
								one(10);
							} else if (hundredNumber(3) && tenNumber(12) && oneNumber(10) && fiveNumber(14)
									&& twentyFiveNumber(12)) {
								centaines = centaines - 800;
								hundred(3);
								twentyFive(12);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (centaines == 900) {
							if (hundredNumber(5) && twentyFiveNumber(12) && tenNumber(10)) {
								centaines = centaines - 900;
								hundred(5);
								twentyFive(12);
								ten(10);
							} else if (hundredNumber(5) && twentyFiveNumber(8) && tenNumber(12) && fiveNumber(14)
									&& oneNumber(10)) {
								centaines = centaines - 900;
								hundred(5);
								twentyFive(8);
								ten(12);
								five(14);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						}
					}
				}

				// Traite les dizaines
				if (dizaines != 0) {
					value = value - dizaines;
					while (dizaines != 0) {
						if (dizaines == 10) {
							if (tenNumber(1)) {
								dizaines = dizaines - 10;
								System.err.println("10-1");
								ten(1);
							} else if (fiveNumber(2)) {
								dizaines = dizaines - 10;
								System.err.println("10-2");
								five(2);
							} else if (fiveNumber(1) && oneNumber(5)) {
								dizaines = dizaines - 10;
								System.err.println("10-3");
								one(5);
								five(1);
							} else if (oneNumber(10)) {
								dizaines = dizaines - 10;
								System.err.println("10-4");
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 20) {
							if (tenNumber(2)) {
								dizaines = dizaines - 20;
								System.err.println("20-1");
								ten(2);
							} else if (tenNumber(1) && fiveNumber(2)) {
								dizaines = dizaines - 20;
								System.err.println("20-2");
								five(2);
								ten(1);
							} else if (fiveNumber(4)) {
								dizaines = dizaines - 20;
								System.err.println("20-3");
								five(4);
							} else if (oneNumber(5) && fiveNumber(3)) {
								dizaines = dizaines - 20;
								System.err.println("20-4");
								one(5);
								five(3);
							} else if (oneNumber(10) && fiveNumber(2)) {
								dizaines = dizaines - 20;
								System.err.println("20-5");
								one(10);
								five(2);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 30) {
							if (twentyFiveNumber(1) && fiveNumber(1)) {
								dizaines = dizaines - 30;
								System.err.println("30-1");
								five(1);
								twentyFive(1);
							} else if (tenNumber(3)) {
								dizaines = dizaines - 30;
								System.err.println("30-2");
								ten(3);
							} else if (tenNumber(2) && fiveNumber(2)) {
								dizaines = dizaines - 30;
								System.err.println("30-3");
								ten(2);
								five(2);
							} else if (tenNumber(1) && fiveNumber(4)) {
								dizaines = dizaines - 30;
								System.err.println("30-4");
								five(4);
								ten(1);
							} else if (fiveNumber(6)) {
								dizaines = dizaines - 30;
								System.err.println("30-5");
								five(6);
							} else if (oneNumber(5) && fiveNumber(5)) {
								dizaines = dizaines - 30;
								System.err.println("30-6");
								one(5);
								five(5);
							} else if (oneNumber(10) && fiveNumber(4)) {
								dizaines = dizaines - 30;
								System.err.println("30-7");
								one(10);
								five(4);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 40) {
							if (fiveNumber(5) && twentyFiveNumber(1)) {
								dizaines = dizaines - 40;
								System.err.println("40-1");
								five(5);
								twentyFive(1);
							} else if (fiveNumber(2) && twentyFiveNumber(1) && oneNumber(5)) {
								dizaines = dizaines - 40;
								System.err.println("40-2");
								five(2);
								one(5);
								twentyFive(1);
							} else if (fiveNumber(1) && twentyFiveNumber(1) && oneNumber(10)) {
								dizaines = dizaines - 40;
								System.err.println("40-3");
								five(1);
								one(10);
								twentyFive(1);
							} else if (tenNumber(4)) {
								dizaines = dizaines - 40;
								System.err.println("40-4");
								ten(4);
							} else if (fiveNumber(2) && tenNumber(3)) {
								dizaines = dizaines - 40;
								System.err.println("40-5");
								ten(3);
								five(2);
							} else if (fiveNumber(4) && tenNumber(2)) {
								dizaines = dizaines - 40;
								System.err.println("40-6");
								ten(2);
								five(4);
							} else if (fiveNumber(6) && tenNumber(1)) {
								dizaines = dizaines - 40;
								System.err.println("40-7");
								ten(1);
								five(6);
							} else if (fiveNumber(8)) {
								dizaines = dizaines - 40;
								System.err.println("40-8");
								five(8);
							} else if (fiveNumber(7) && oneNumber(5)) {
								dizaines = dizaines - 40;
								System.err.println("40-9");
								one(5);
								five(7);
							} else if (fiveNumber(6) && oneNumber(10)) {
								dizaines = dizaines - 40;
								System.err.println("40-9");
								one(10);
								five(6);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 50) {
							if (twentyFiveNumber(2)) {
								dizaines = dizaines - 50;
								twentyFive(2);
							} else if (twentyFiveNumber(1) && fiveNumber(5)) {
								dizaines = dizaines - 50;
								twentyFive(1);
								five(5);
							} else if (twentyFiveNumber(1) && fiveNumber(4) && oneNumber(5)) {
								dizaines = dizaines - 50;
								twentyFive(1);
								five(4);
								one(5);
							} else if (twentyFiveNumber(1) && fiveNumber(3) && oneNumber(10)) {
								dizaines = dizaines - 50;
								twentyFive(1);
								five(3);
								one(10);
							} else if (tenNumber(5)) {
								dizaines = dizaines - 50;
								ten(5);
							} else if (fiveNumber(2) && tenNumber(4)) {
								dizaines = dizaines - 50;
								ten(4);
								five(2);
							} else if (fiveNumber(4) && tenNumber(3)) {
								dizaines = dizaines - 50;
								ten(3);
								five(4);
							} else if (fiveNumber(6) && tenNumber(2)) {
								dizaines = dizaines - 50;
								ten(2);
								five(6);
							} else if (fiveNumber(8) && tenNumber(1)) {
								dizaines = dizaines - 50;
								ten(1);
								five(8);
							} else if (fiveNumber(10)) {
								dizaines = dizaines - 50;
								five(10);
							} else if (oneNumber(5)) {
								dizaines = dizaines - 50;
								one(5);
							} else if (oneNumber(10)) {
								dizaines = dizaines - 50;
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 60) {
							if (twentyFiveNumber(2) && fiveNumber(2)) {
								dizaines = dizaines - 60;
								twentyFive(2);
								five(2);
							} else if (twentyFiveNumber(2) && fiveNumber(1) && oneNumber(5)) {
								dizaines = dizaines - 60;
								twentyFive(2);
								five(1);
								one(5);
							} else if (twentyFiveNumber(2) && oneNumber(10)) {
								dizaines = dizaines - 60;
								twentyFive(2);
								one(10);
							} else if (twentyFiveNumber(1) && fiveNumber(7)) {
								dizaines = dizaines - 60;
								twentyFive(1);
								five(7);
							} else if (twentyFiveNumber(1) && fiveNumber(6) && oneNumber(5)) {
								dizaines = dizaines - 60;
								twentyFive(1);
								five(6);
								one(5);
							} else if (twentyFiveNumber(1) && fiveNumber(5) && oneNumber(10)) {
								dizaines = dizaines - 60;
								twentyFive(1);
								five(5);
								one(10);
							} else if (tenNumber(6)) {
								dizaines = dizaines - 60;
								ten(6);
							} else if (tenNumber(5) && fiveNumber(2)) {
								dizaines = dizaines - 60;
								ten(5);
								five(2);
							} else if (tenNumber(4) && fiveNumber(4)) {
								dizaines = dizaines - 60;
								ten(4);
								five(4);
							} else if (tenNumber(3) && fiveNumber(6)) {
								dizaines = dizaines - 60;
								ten(3);
								five(6);
							} else if (tenNumber(2) && fiveNumber(8)) {
								dizaines = dizaines - 60;
								ten(2);
								five(8);
							} else if (tenNumber(1) && fiveNumber(10)) {
								dizaines = dizaines - 60;
								ten(1);
								five(10);
							} else if (fiveNumber(12)) {
								dizaines = dizaines - 60;
								five(12);
							} else if (fiveNumber(11) && oneNumber(5)) {
								dizaines = dizaines - 60;
								five(11);
								one(5);
							} else if (fiveNumber(10) && oneNumber(10)) {
								dizaines = dizaines - 60;
								five(10);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 70) {
							if (twentyFiveNumber(2) && fiveNumber(4)) {
								dizaines = dizaines - 70;
								twentyFive(2);
								five(4);
							} else if (twentyFiveNumber(2) && fiveNumber(3) && oneNumber(5)) {
								dizaines = dizaines - 70;
								twentyFive(2);
								five(3);
								one(5);
							} else if (twentyFiveNumber(2) && oneNumber(10) && fiveNumber(2)) {
								dizaines = dizaines - 70;
								twentyFive(2);
								five(2);
								one(10);
							} else if (twentyFiveNumber(1) && fiveNumber(9)) {
								dizaines = dizaines - 70;
								twentyFive(1);
								five(9);
							} else if (twentyFiveNumber(1) && fiveNumber(8) && oneNumber(5)) {
								dizaines = dizaines - 70;
								twentyFive(1);
								five(8);
								one(5);
							} else if (twentyFiveNumber(1) && fiveNumber(7) && oneNumber(10)) {
								dizaines = dizaines - 70;
								twentyFive(1);
								five(7);
								one(10);
							} else if (tenNumber(7)) {
								dizaines = dizaines - 70;
								ten(7);
							} else if (tenNumber(6) && fiveNumber(2)) {
								dizaines = dizaines - 70;
								ten(6);
								five(2);
							} else if (tenNumber(5) && fiveNumber(4)) {
								dizaines = dizaines - 70;
								ten(5);
								five(4);
							} else if (tenNumber(4) && fiveNumber(6)) {
								dizaines = dizaines - 70;
								ten(4);
								five(6);
							} else if (tenNumber(3) && fiveNumber(8)) {
								dizaines = dizaines - 70;
								ten(3);
								five(8);
							} else if (tenNumber(2) && fiveNumber(10)) {
								dizaines = dizaines - 70;
								ten(2);
								five(10);
							} else if (tenNumber(1) && fiveNumber(12)) {
								dizaines = dizaines - 70;
								ten(1);
								five(12);
							} else if (fiveNumber(14)) {
								dizaines = dizaines - 70;
								five(14);
							} else if (fiveNumber(13) && oneNumber(5)) {
								dizaines = dizaines - 70;
								five(13);
								one(5);
							} else if (fiveNumber(12) && oneNumber(10)) {
								dizaines = dizaines - 70;
								five(12);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 80) {
							if (twentyFiveNumber(3) && fiveNumber(1)) {
								dizaines = dizaines - 80;
								twentyFive(3);
								five(1);
							} else if (twentyFiveNumber(3) && oneNumber(5)) {
								dizaines = dizaines - 80;
								twentyFive(3);
								one(5);
							} else if (twentyFiveNumber(2) && fiveNumber(6)) {
								dizaines = dizaines - 80;
								twentyFive(2);
								five(6);
							} else if (twentyFiveNumber(2) && fiveNumber(5) && oneNumber(5)) {
								dizaines = dizaines - 80;
								twentyFive(2);
								five(5);
								one(5);
							} else if (twentyFiveNumber(2) && oneNumber(10) && fiveNumber(4)) {
								dizaines = dizaines - 80;
								twentyFive(2);
								five(4);
								one(10);
							} else if (twentyFiveNumber(1) && fiveNumber(11)) {
								dizaines = dizaines - 80;
								twentyFive(1);
								five(11);
							} else if (twentyFiveNumber(1) && fiveNumber(10) && oneNumber(5)) {
								dizaines = dizaines - 80;
								twentyFive(1);
								five(10);
								one(5);
							} else if (twentyFiveNumber(1) && fiveNumber(9) && oneNumber(10)) {
								dizaines = dizaines - 80;
								twentyFive(1);
								five(9);
								one(10);
							} else if (tenNumber(8)) {
								dizaines = dizaines - 80;
								ten(8);
							} else if (tenNumber(7) && fiveNumber(2)) {
								dizaines = dizaines - 80;
								ten(7);
								five(2);
							} else if (tenNumber(6) && fiveNumber(4)) {
								dizaines = dizaines - 80;
								ten(6);
								five(4);
							} else if (tenNumber(5) && fiveNumber(6)) {
								dizaines = dizaines - 80;
								ten(5);
								five(6);
							} else if (tenNumber(4) && fiveNumber(8)) {
								dizaines = dizaines - 80;
								ten(4);
								five(8);
							} else if (tenNumber(3) && fiveNumber(10)) {
								dizaines = dizaines - 80;
								ten(3);
								five(10);
							} else if (tenNumber(2) && fiveNumber(12)) {
								dizaines = dizaines - 80;
								ten(2);
								five(12);
							} else if (tenNumber(1) && fiveNumber(14)) {
								dizaines = dizaines - 80;
								ten(1);
								five(14);
							} else if (fiveNumber(14) && oneNumber(10)) {
								dizaines = dizaines - 80;
								five(12);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						} else if (dizaines == 90) {
							if (twentyFiveNumber(3) && fiveNumber(3)) {
								dizaines = dizaines - 90;
								twentyFive(3);
								five(3);
							} else if (twentyFiveNumber(3) && fiveNumber(2) && oneNumber(5)) {
								dizaines = dizaines - 90;
								twentyFive(3);
								five(3);
								one(5);
							} else if (twentyFiveNumber(3) && fiveNumber(2) && oneNumber(10)) {
								dizaines = dizaines - 90;
								twentyFive(3);
								one(10);
							} else if (twentyFiveNumber(2) && fiveNumber(8)) {
								dizaines = dizaines - 90;
								twentyFive(2);
								five(8);
							} else if (twentyFiveNumber(2) && fiveNumber(7) && oneNumber(5)) {
								dizaines = dizaines - 90;
								twentyFive(2);
								five(7);
								one(5);
							} else if (twentyFiveNumber(2) && oneNumber(10) && fiveNumber(6)) {
								dizaines = dizaines - 90;
								twentyFive(2);
								five(6);
								one(10);
							} else if (twentyFiveNumber(1) && fiveNumber(13)) {
								dizaines = dizaines - 90;
								twentyFive(1);
								five(13);
							} else if (twentyFiveNumber(1) && fiveNumber(10) && oneNumber(12)) {
								dizaines = dizaines - 90;
								twentyFive(1);
								five(12);
								one(5);
							} else if (twentyFiveNumber(1) && fiveNumber(9) && oneNumber(11)) {
								dizaines = dizaines - 90;
								twentyFive(1);
								five(11);
								one(10);
							} else if (tenNumber(9)) {
								dizaines = dizaines - 90;
								ten(9);
							} else if (tenNumber(8) && fiveNumber(2)) {
								dizaines = dizaines - 90;
								ten(8);
								five(2);
							} else if (tenNumber(7) && fiveNumber(4)) {
								dizaines = dizaines - 90;
								ten(7);
								five(4);
							} else if (tenNumber(6) && fiveNumber(6)) {
								dizaines = dizaines - 90;
								ten(6);
								five(6);
							} else if (tenNumber(5) && fiveNumber(8)) {
								dizaines = dizaines - 90;
								ten(5);
								five(8);
							} else if (tenNumber(4) && fiveNumber(10)) {
								dizaines = dizaines - 90;
								ten(4);
								five(10);
							} else if (tenNumber(3) && fiveNumber(12)) {
								dizaines = dizaines - 90;
								ten(3);
								five(12);
							} else if (tenNumber(2) && fiveNumber(14)) {
								dizaines = dizaines - 90;
								ten(2);
								five(14);
							} else if (fiveNumber(14) && oneNumber(10) && tenNumber(1)) {
								dizaines = dizaines - 90;
								ten(1);
								five(14);
								one(10);
							} else if (fiveNumber(12) && oneNumber(10) && tenNumber(2)) {
								dizaines = dizaines - 90;
								ten(2);
								five(12);
								one(10);
							} else {
								if (check) {
									restock();
								} else {
									fill();
								}
							}
						}
					}
				}
			}

			// Traite les unites
			if (unites != 0) {
				value = value - unites;
				while (unites != 0) {
					if (unites < 5 && oneNumber(1)) {
						one(1);
						unites(1);
					} else if (unites >= 5 && fiveNumber(1)) {
						five(1);
						unites(5);
					} else if (map9.get("player" + playerId + "numbersToken").get(1) == 0 && unites == 5
							&& oneNumber(5)) {
						one(5);
						unites(5);
					} else {
						if (check) {
							restock();
						} else {
							fill();
						}
					}
				}
			}
		}

		// Mise à jour des labels de montants
		map4.get("player" + playerId + "cash1").setText("You: " + map8.get("player" + playerId + "total") + "$");
		map4.get("player" + playerId + "cash2").setText("Opponent: " + map8.get("player" + playerId + "total") + "$");
	}

	// Détermine les valeurs des unités/dizaines/centaines/millers
	private void value(int number) {
		unites = number % 10;
		number = number - unites;
		dizaines = number % 100;
		number = number - dizaines;
		centaines = number % 1000;
		number = number - centaines;
		mille = number % 10000;
		number = number - mille;
	}

	// Remplis les jetons nécessaire pour les conditions prédites
	private void fill() {
		System.out.print("Fill ");
		if (map9.get("player" + playerId + "numbersToken").get(1) != 0
				&& map9.get("player" + playerId + "numbersToken").get(0) <= 15) {
			System.err.println("5 -> 5*1");
			five(1);
			add("white", 5);
		} else if (map9.get("player" + playerId + "numbersToken").get(2) != 0
				&& map9.get("player" + playerId + "numbersToken").get(1) <= 18) {
			System.err.println("10 -> 2*5");
			ten(1);
			add("red", 2);

		} else if (map9.get("player" + playerId + "numbersToken").get(3) != 0
				&& map9.get("player" + playerId + "numbersToken").get(2) <= 18
				&& map9.get("player" + playerId + "numbersToken").get(1) <= 18) {
			System.err.println("25 -> 2*10+5");
			twentyFive(1);
			add("blue", 2);
			add("red", 2);

		} else if (map9.get("player" + playerId + "numbersToken").get(4) != 0
				&& map9.get("player" + playerId + "numbersToken").get(3) <= 16) {
			System.err.println("100 -> 4*25");
			hundred(1);
			add("green", 4);
		}
	}

	// Remplie les jetons manquant
	private void restock() {
		System.out.print("Restock ");
		if (map9.get("player" + playerId + "numbersToken").get(1) != 0
				&& map9.get("player" + playerId + "numbersToken").get(0) == 0) {
			System.err.println("5 -> 5*1");
			five(1);
			add("white", 5);
		} else if (map9.get("player" + playerId + "numbersToken").get(2) != 0
				&& map9.get("player" + playerId + "numbersToken").get(1) == 0) {
			System.err.println("10 -> 2*5");
			ten(1);
			add("red", 2);

		} else if (map9.get("player" + playerId + "numbersToken").get(3) != 0
				&& map9.get("player" + playerId + "numbersToken").get(2) == 0) {
			System.err.println("25 -> 2*10+5");
			twentyFive(1);
			add("blue", 2);
			add("red", 2);

		} else if (map9.get("player" + playerId + "numbersToken").get(4) != 0
				&& map9.get("player" + playerId + "numbersToken").get(3) == 0) {
			System.err.println("100 -> 4*25");
			hundred(1);
			add("green", 4);
		}
	}

	// Ajoute la sorte de jeton et le nombre de ce jeton voulue
	private void add(String name, int number) {
		int nomb = 0;

		// Détermine la sorte du jeton
		if (String.valueOf(name).equals("white")) {
			nomb = 0;
		} else if (String.valueOf(name).equals("red")) {
			nomb = 1;
		} else if (String.valueOf(name).equals("blue")) {
			nomb = 2;
		} else if (String.valueOf(name).equals("green")) {
			nomb = 3;
		} else if (String.valueOf(name).equals("black")) {
			nomb = 4;
		}

                    // Ajoute les jetons
		for (int i = 1; i <= number; i++) {
			map9.get("player" + playerId + "numbersToken").set(nomb,
					map9.get("player" + playerId + "numbersToken").get(nomb) + 1);

			map6.get("player" + playerId + "pane").getChildren().addAll(
					map7.get("player" + playerId + name + (map9.get("player" + playerId + "numbersToken").get(nomb))));
		}
	}

	// Diminue le nombres d'unités
	private void unites(int number) {
		for (int i = 1; i <= number; i++) {
			unites--;
		}
	}

	// Enlève le nombre de jetons blanc (1), entré dans la fonction
	private void one(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(
					map7.get("player" + playerId + "white" + map9.get("player" + playerId + "numbersToken").get(0)));

			map9.get("player" + playerId + "numbersToken").set(0,
					map9.get("player" + playerId + "numbersToken").get(0) - 1);
		}
	}

	// Enlève le nombre de jetons rouge (5), entré dans la fonction
	private void five(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(
					map7.get("player" + playerId + "red" + map9.get("player" + playerId + "numbersToken").get(1)));

			map9.get("player" + playerId + "numbersToken").set(1,
					map9.get("player" + playerId + "numbersToken").get(1) - 1);
		}
	}

	// Enlève le nombre de jetons bleu (10), entré dans la fonction
	private void ten(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(
					map7.get("player" + playerId + "blue" + map9.get("player" + playerId + "numbersToken").get(2)));

			map9.get("player" + playerId + "numbersToken").set(2,
					map9.get("player" + playerId + "numbersToken").get(2) - 1);
		}
	}

	// Enlève le nombre de jetons vert (25), entré dans la fonction
	private void twentyFive(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(
					map7.get("player" + playerId + "green" + map9.get("player" + playerId + "numbersToken").get(3)));

			map9.get("player" + playerId + "numbersToken").set(3,
					map9.get("player" + playerId + "numbersToken").get(3) - 1);
		}
	}

	// Enlève le nombre de jetons noir (100), entré dans la fonction
	private void hundred(int number) {
		for (int i = 1; i <= number; i++) {
			map6.get("player" + playerId + "pane").getChildren().removeAll(
					map7.get("player" + playerId + "black" + map9.get("player" + playerId + "numbersToken").get(4)));

			map9.get("player" + playerId + "numbersToken").set(4,
					map9.get("player" + playerId + "numbersToken").get(4) - 1);
		}
	}

	// Détermine s'il y a assez de jetons blanc (1)
	private boolean oneNumber(int number) {
		return map9.get("player" + playerId + "numbersToken").get(0) >= number;
	}

	// Détermine s'il y a assez de jetons rouge (5)
	private boolean fiveNumber(int number) {
		return map9.get("player" + playerId + "numbersToken").get(1) >= number;
	}

	// Détermine s'il y a assez de jetons bleu (10)
	private boolean tenNumber(int number) {
		return map9.get("player" + playerId + "numbersToken").get(2) >= number;
	}

	// Détermine s'il y a assez de jetons vert (25)
	private boolean twentyFiveNumber(int number) {
		return map9.get("player" + playerId + "numbersToken").get(3) >= number;
	}

	// Détermine s'il y a assez de jetons noir (100)
	private boolean hundredNumber(int number) {
		return map9.get("player" + playerId + "numbersToken").get(4) >= number;
	}

	// Les fonctions nécessaires pour la fenêtre du manuel
	public void manual() {
		// Les jetons (dans le bas)
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

		// Définie chaque button
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

		// Le texte pour l'explication
		manualText1 = new String(
				"If there is no wager on the current betting round, a player may check. The act \nof checking passes the action to the next person, immediately clockwise from \nthe player. A check does not forfeit interest in the pot, only the current right \nto bet. If all active players check during a round of betting, the round is \nconsidered complete."
						+ "\n\n\nIf there has been a bet on the current round of poker play, a player may call. \nThe act of calling requires the player to match the current bet made by his or \nher opponent(s)."
						+ "\n\n\nThe act of folding forfeits all interest in the pot. A player who folds is not \nrequired or allowed to wager any further money during the current poker \nhand, but cannot win that hand either."
						+ "\n\n\nIf there has been a bet on the current betting round, a player may raise. The \nact of raising requires the poker player to match the current bet, and then \nmake a greater one. All subsequent players are required to call the raise or \nraise again (re-raise) to maintain interest in the pot. If there is not yet a wager \non the current betting round, a player may bet. If a player bets, the player \nimmediately clockwise from him or her (and any subsequent players) may fold, \nraise, or call."
						+ "\n\n\nIf there is not yet a wager on the current betting round, a player may bet. \nIf a player bets, the player immediately clockwise from him or her (and any \nsubsequent players) may fold, raise, or call.");

		// Montant des jetons
		manualText2 = new String("1$\t     5$\t10$\t    25$\t100$");

		// Les labels avec le texte
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

		// Layout du manuel
		manualPane = new Pane();
		manualPane.getChildren().addAll(test, test1, test2, test3, test4, explanation, explanation1, manualChipRed,
				manualChipBlack, manualChipGreen, manualChipWhite, manualChipBlue);

		// Pane vais toujours être centré
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		HBox ap = new HBox();
		ap.setAlignment(Pos.CENTER);
		ap.setCenterShape(true);
		ap.getChildren().add(manualPane);
		vb.getChildren().add(ap);
		vb.setId("background");
		manualScene = new Scene(vb, 720, 780);

		// Mets l'icon de l'application
		manualIcon = new Image(getClass().getResourceAsStream("manual.png"));

		// Définie la fenêtre
		manualStage = new Stage();
		manualStage.getIcons().add(manualIcon);
		manualStage.setScene(manualScene);
		manualStage.setAlwaysOnTop(true);
		manualStage.setTitle("Manual");
	}

	// Créer les layouts pour les joueurs
	public void numberPanes() {
		map6 = new HashMap<String, Pane>();
		for (int i = 1; i <= totalPlayers; i++) {
			map6.put("player" + i + "pane", new Pane());
		}
	}

	// Pour folder joueur1
	public void fold1() {
		// Son
		swoosh.play();

		// Bloque/débloque les buttons
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

		// Mise à jour des jetons
		playerId = 2;
		value(pot);
		add("white", unites);
		dizaines = dizaines / 10;
		add("blue", dizaines);
		centaines = centaines / 100;
		add("black", centaines);
		for (int i = 1; i <= mille; i++) {
			add("black", 10);
		}

		// Mise à jour des labels
		map8.put("player2total", map8.get("player2total") + pot);
		map4.get("player2cash1").setText("You: " + map8.get("player2total") + "$");
		map4.get("player2cash2").setText("Opponent: " + map8.get("player2total") + "$");
		map4.get("player1turn").setText(player2Name + " WON");
		map4.get("player2turn").setText(player2Name + " WON");

		// Mise à jour du pot
		pot = 0;
		map4.get("pot1").setText("Pot: " + pot + "$");
		map4.get("pot2").setText("Pot: " + pot + "$");

		// Changer de ronde
		map6.get("player2pane").getChildren().addAll(map3.get("player2nextRound"));
		map6.get("player1pane").getChildren().addAll(map3.get("player1nextRound"));
	}

	// Pour folder joueur1
	public void fold2() {
		// Son
		swoosh.play();

		// Bloque/débloque les buttons
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

		// Mise à jour des jetons
		playerId = 1;
		value(pot);
		add("white", unites);
		dizaines = dizaines / 10;
		add("blue", dizaines);
		centaines = centaines / 100;
		add("black", centaines);
		mille = mille / 1000;
		for (int i = 1; i <= mille; i++) {
			add("black", 10);
		}

		// Mise à jour des labels
		map8.put("player1total", map8.get("player1total") + pot);
		map4.get("player1cash1").setText("You: " + map8.get("player1total") + "$");
		map4.get("player1cash2").setText("Opponent: " + map8.get("player1total") + "$");
		map4.get("player1turn").setText(player1Name + " WON");
		map4.get("player2turn").setText(player1Name + " WON");

		// Mise à jour du pot
		pot = 0;
		map4.get("pot1").setText("Pot: " + pot + "$");
		map4.get("pot2").setText("Pot: " + pot + "$");

		// Changer de ronde
		map6.get("player2pane").getChildren().addAll(map3.get("player2nextRound"));
		map6.get("player1pane").getChildren().addAll(map3.get("player1nextRound"));
	}

	// Définie les conditions du premier joueur
	public void player1Conditions() {

		// Ajoute les éléments de base (au début d'une joute)
		map6.get("player1pane").getChildren().addAll(map.get("player1bigFace1"), map.get("player1bigFace2"),
				map.get("player1smallBack1"), map.get("player1smallBack2"), map3.get("player1mainMenu"),
				map3.get("player1raise"), map3.get("player1fold"), map3.get("player1call"), map3.get("player1bet"),
				map3.get("player1check"), map3.get("player1manual"), map4.get("player1turn"),
				map4.get("player1numberTurn"), map4.get("pot1"), map4.get("player2cash2"), map4.get("player1cash1"),
				map4.get("player1numberRound")  );

		// Expérimentation
		// check();
		// map6.get("player1pane").getChildren().addAll( map10.get("token1"),
		// map10.get("token2"), map10.get("token3"), map10.get("token4"),
		// map10.get("token5"));

		// Définie la fonction du button de manuel
		map3.get("player1manual").setOnAction(e -> {
			if (manualStage.isShowing()) {
				manualStage.close();
			} else {
				manualStage.show();
			}
			play();
		});

		// Définie la fonction du button de fold
		map3.get("player1fold").setOnAction(e -> {
			fold1();
		});

		// Définie la fonction du button de bet
		map3.get("player1bet").setOnAction(e -> {
			go();
			betOff1 = false;
			play();
		});

		// Définie la fonction du button de raise
		map3.get("player1raise").setOnAction(e -> {
			go();
			play();
		});

		// Définie la fonction du button de check
		map3.get("player1check").setOnAction(e -> {
			play();
			checkOff1 = false;
			playerId = 2;

			// Bloque/débloque les buttons
			map3.get("player1fold").setDisable(true);
			map3.get("player1call").setDisable(true);
			map3.get("player1raise").setDisable(true);
			map3.get("player1bet").setDisable(true);
			map3.get("player1check").setDisable(true);
			map3.get("player2fold").setDisable(false);
			map3.get("player2bet").setDisable(false);
			map3.get("player2check").setDisable(false);

			// Se ambos os jogadores fizerem check, mude as jogadas
			if (!checkOff1 && !checkOff2) {
				if (turnId == 2) {
					secondTurn();
				} else if (turnId == 3) {
					thirdTurn();
                             
				} else if (turnId == 4) {
					finish();
				}
				map4.get("player1numberTurn").setText("Turn: " + turnId);
				map4.get("player2numberTurn").setText("Turn: " + turnId);
			}

			// Mise à jour des labels
			map4.get("player1turn").setText(player2Name + "'s turn");
			map4.get("player2turn").setText(player2Name + "'s turn");
			map6.get("player2pane").getChildren().addAll(map4.get("ask"));
			map6.get("player1pane").getChildren().addAll(map4.get("wait"));
		});

		// Définie la fonction du button de call
		map3.get("player1call").setOnAction(e -> {
			callOff1 = false;

			// Copie le montant du joueur 1
			raiseAmount.setText(String.valueOf(player2value));
			test();
			play();
		});

		// Pane vais toujours être centré
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		HBox ap = new HBox();
		ap.setAlignment(Pos.CENTER);
		ap.setCenterShape(true);
		ap.getChildren().add(map6.get("player1pane"));
		vb.getChildren().add(ap);
		vb.setId("background");
		mainScene = new Scene(vb, 1366, 720);

		// Définie la fenêtre
		stage.setTitle("Poker - " + player1Name);
		stage.setResizable(true);
		stage.setScene(mainScene);
	}

	// Définie les conditions du deuxième joueur
	public void player2Conditions() {

		// Ajoute les éléments de base (au début d'une joute)
		map6.get("player2pane").getChildren().addAll(map.get("player2bigFace1"), map.get("player2bigFace2"),
				map.get("player2smallBack1"), map.get("player2smallBack2"), map3.get("player2mainMenu"),
				map3.get("player2raise"), map3.get("player2fold"), map3.get("player2call"), map3.get("player2bet"),
				map3.get("player2check"), map3.get("player2manual"), map4.get("player2turn"),
				map4.get("player2numberTurn"), map4.get("pot2"), map4.get("player2cash1"), map4.get("player1cash2"),
				map4.get("player2numberRound"));

		// Définie la fonction du button de manuel
		map3.get("player2manual").setOnAction(e -> {
			if (manualStage.isShowing()) {
				manualStage.close();
			} else {
				manualStage.show();
			}
			play();
		});

		// Définie la fonction du button de fold
		map3.get("player2fold").setOnAction(e -> {
			fold2();
		});

		// Définie la fonction du button de call
		map3.get("player2call").setOnAction(e -> {
			callOff2 = false;

			// Copie le montant du joueur 1
			raiseAmount.setText(String.valueOf(player1value));
			test();
			play();
		});

		// Définie la fonction du button de raise
		map3.get("player2raise").setOnAction(e -> {
			go();
			raiseOff2 = false;
			play();
		});

		// Définie la fonction du button de bet
		map3.get("player2bet").setOnAction(e -> {
			go();
			betOff2 = false;
			play();
		});

		// Définie la fonction du button de check
		map3.get("player2check").setOnAction(e -> {
			play();
			checkOff2 = false;

			// Bloque/débloque les buttons
			map3.get("player2call").setDisable(true);
			map3.get("player2fold").setDisable(true);
			map3.get("player2raise").setDisable(true);
			map3.get("player2bet").setDisable(true);
			map3.get("player2check").setDisable(true);
			map3.get("player1fold").setDisable(false);
			map3.get("player1bet").setDisable(false);
			map3.get("player1check").setDisable(false);

			// Si les deux joueurs actionne check, change de tour
			if (!checkOff1 && !checkOff2) {
				if (turnId == 2) {
					secondTurn();
				} else if (turnId == 3) {
					thirdTurn();
				} else if (turnId == 4) {
                 
					finish();
                                        
                                        
				}
				map4.get("player1numberTurn").setText("Turn: " + turnId);
				map4.get("player2numberTurn").setText("Turn: " + turnId);
			}

			// Mise à jour des labels
			map4.get("player1turn").setText(player1Name + "'s turn");
			map4.get("player2turn").setText(player1Name + "'s turn");
			map6.get("player2pane").getChildren().remove(map4.get("ask"));
			map6.get("player1pane").getChildren().remove(map4.get("wait"));
		});

		// Pane vais toujours être centré
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		HBox ap = new HBox();
		ap.setAlignment(Pos.CENTER);
		ap.setCenterShape(true);
		ap.getChildren().add(map6.get("player2pane"));
		vb.getChildren().add(ap);
		vb.setId("background");
		playe2scene = new Scene(vb, 1366, 720);

		// Définie la fenêtre
		player2stage = new Stage();
		player2stage.setScene(playe2scene);
		player2stage.setTitle("Poker - " + player2Name);
		player2stage.setResizable(true);
		player2stage.getIcons().add(applicationIcon);
		player2stage.show();
	}

	// Affiche/enlève le textField pour faire un bet ou pour 'raiser'
	public void go() {
		if (raiseId == 1) {
			// Ajoute
			map6.get("player" + playerId + "pane").getChildren().addAll(map4.get("help"), raiseAmount);
			raiseId = 0;
		} else {
			// Enlève
			map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("help"), raiseAmount);
			raiseId = 1;
		}
	}

	// Établie les valeurs lors de changement de tour
	public void restore() {
		callOff1 = true;
		callOff2 = true;
		betOff1 = true;
		betOff2 = true;
		raiseOff2 = true;
		checkOff1 = true;
		checkOff2 = true;
		map3.get("player1fold").setDisable(false);
		map3.get("player1call").setDisable(true);
		map3.get("player1raise").setDisable(true);
		map3.get("player1bet").setDisable(false);
		map3.get("player1check").setDisable(false);

		map3.get("player2call").setDisable(true);
		map3.get("player2fold").setDisable(true);
		map3.get("player2raise").setDisable(true);
		map3.get("player2bet").setDisable(true);
		map3.get("player2check").setDisable(true);

		player2turn = true;
		player1turn = true;

		playerId = 1;

		bet = roundId * 10;
		raiseAmount.setText(String.valueOf(bet));
	}

	// Premier tour pour le dealer
	public void firstTurn() {
		restore();
		turnId = 2;

		map4.get("player1numberTurn").setText("Turn: " + turnId);
		map4.get("player2numberTurn").setText("Turn: " + turnId);

		// Définie l'endroit des 2 cartes
		map6.get("player1pane").getChildren().addAll(map.get("dealer1Card1"), map.get("dealer1Card2"),
				map.get("dealer1Card3"));
		map6.get("player2pane").getChildren().addAll(map.get("dealer2Card1"), map.get("dealer2Card3"),
				map.get("dealer2Card2"));
	}

	// Deuxième tour pour le dealer
	public void secondTurn() {
		turnId = 3;
                
		restore();

		map4.get("player1numberTurn").setText("Turn: " + turnId);
		map4.get("player2numberTurn").setText("Turn: " + turnId);

		// Définie l'endroit des 3 cartes
		for (int i = 1; i < 3; i++) {
			map.get("dealer" + i + "Card1").setLayoutX(367.2857143);
			map.get("dealer" + i + "Card2").setLayoutX(530.1428571);
			map.get("dealer" + i + "Card3").setLayoutX(693);
		}
		map6.get("player1pane").getChildren().addAll(map.get("dealer1Card4"));
		map6.get("player2pane").getChildren().addAll(map.get("dealer2Card4"));
	}

	// Troisième tour pour le dealer
	public void thirdTurn() {
            
                                   
           
            
		restore();
		turnId = 4;

		map4.get("player1numberTurn").setText("Turn: " + turnId);
		map4.get("player2numberTurn").setText("Turn: " + turnId);

		// Définie l'endroit des 4 cartes
		for (int i = 1; i < 3; i++) {
			map.get("dealer" + i + "Card1").setLayoutX(285.8571428);
			map.get("dealer" + i + "Card2").setLayoutX(448.7142857);
			map.get("dealer" + i + "Card3").setLayoutX(611.57142857142857142857142857143);
			map.get("dealer" + i + "Card4").setLayoutX(774.4285714);
		}
		map6.get("player1pane").getChildren().addAll(map.get("dealer1Card5"));
		map6.get("player2pane").getChildren().addAll(map.get("dealer2Card5"));
                
            
                
	}

	// Fait les jetons pour les joueurs
	public void tokens() {

		numberPanes();

		// Définie les propriétés des jetons
		map9 = new HashMap<String, ArrayList<Integer>>();
		map7 = new HashMap<String, ImageView>();
		tokensVarNames = new String[] { "white", "red", "blue", "green", "black" };
		numbersTokens = new int[] { 10, 14, 12, 12, 5 };
		maxTokens = new int[] { 45, 45, 45, 45, 45 };
		tokensLayoutX = new int[] { 90, 160, 230, 300, 370 };
		tokensLayoutY = new ArrayList<Integer>();

		// Créer les ArrayList pour les jetons par rapport aux nombre de jetons
		for (int i = 1; i <= totalPlayers; i++) {
			map9.put("player" + i + "numbersToken", new ArrayList<Integer>());
			for (int j = 0; j < numbersTokens.length; j++) {
				map9.get("player" + i + "numbersToken").add(numbersTokens[j]);
			}
		}

		// Créer le layoutY pour les jetons
		max = 0;
		for (int e : maxTokens) {
			if (e > max) {
				max = e;
			}
		}
		int inital = 672;
		for (int i = 0; i < max; i++) {
			tokensLayoutY.add(inital - 9);
			inital = inital - 9;
		}

		// Créer les jetons
		for (int j = 0; j < tokensVarNames.length; j++) {
			for (int i = 1; i <= totalPlayers; i++) {

				// Créer les jetons pour chaque joueur
				for (int t = 1; t <= maxTokens[j]; t++) {
					map7.put("player" + i + tokensVarNames[j] + t,
							new ImageView(new Image(getClass().getResourceAsStream(tokensVarNames[j] + ".png"))));
					map7.get("player" + i + tokensVarNames[j] + t).setLayoutY(tokensLayoutY.get(t - 1));
					map7.get("player" + i + tokensVarNames[j] + t).setPreserveRatio(true);
					map7.get("player" + i + tokensVarNames[j] + t).setFitHeight(30);
					map7.get("player" + i + tokensVarNames[j] + t).setLayoutX(tokensLayoutX[j]);
				}

				// Place les jetons dans les layouts
				for (int t = 1; t <= numbersTokens[j]; t++) {
					map6.get("player" + i + "pane").getChildren().add(map7.get("player" + i + tokensVarNames[j] + t));
				}
			}
		}

	}

	// Définie le nombre de jetons/argent pour les joueurs
	public void variables() {
		map8 = new HashMap<String, Integer>();
		for (int i = 0; i <= totalPlayers; i++) {
			map8.put("player" + i + "total", new Integer(limit));
		}
	}

	// Fonction jamais utilisé, calcule le montant d'argent avec le nombre de
	// jetons
	public int calculateCash(int player) {
		int total = 0;
		int[] value = new int[] { 1, 5, 10, 25, 100 };
		for (int i = 0; i <= 4; i++) {
			total = total + (map9.get("player" + player + "numbersToken").get(i) * value[i]);
		}
		return total;
	}

	// Définie tout les labels utilisés pour le jeux
	public void labels() {

		// Définie les propriétés des buttons
		map4 = new HashMap<String, Label>();
		labelVarNames = new String[] { "warning", "turn", "help", "wait", "ask", "numberTurn", "numberRound",
				"warning1", "waiting", "check", "pot1", "pot2", "player1cash1", "player1cash2", "player2cash1",
				"player2cash2", "empty", "tooMuch" };
		labelText = new String[] { "The maximum is " + limit, player1Name + "'s turn", "Enter the amount",
				"Waiting for opponent", "The opponent checked", "Turn: " + turnId, "Round: " + roundId,
				"The minimum is " + bet, "Waiting for the opponent to respond",
				"The opponent wants to play another round", "Pot: " + pot + "$", "Pot : " + pot + "$",
				"You: " + map8.get("player" + playerId + "total") + "$",
				"Opponent: " + map8.get("player" + playerId + "total") + "$",
				"You: " + map8.get("player" + playerId + "total") + "$",
				"Opponent: " + map8.get("player" + playerId + "total") + "$", "You have no money!",
				"You do not have enough money!" };
		labelLayoutX = new int[] { 1180, 620, 1187, 1183, 1100, 1300, 1286, 1180, 310, 260, 500, 500, 800, 950, 800,
				950, 1180, 1180 };
		labelLayoutY = new int[] { 630, 370, 555, 475, 408, 690, 670, 630, 200, 200, 15, 15, 15, 15, 15, 15, 630, 630 };

		// Créer chaque labels
		for (int j = 0; j < labelVarNames.length; j++) {
			for (int i = 1; i <= totalPlayers; i++) {
				// Pour chaque joueur (2 fois)
				if (j == 1 || j == 5 || j == 6) {
					map4.put("player" + i + labelVarNames[j], new Label(labelText[j]));
					map4.get("player" + i + labelVarNames[j]).setLayoutX(labelLayoutX[j]);
					map4.get("player" + i + labelVarNames[j]).setLayoutY(labelLayoutY[j]);
				}
			}
			// Pour chaque un joueur (1 fois)
			if (j != 1 || j != 5) {
				map4.put(labelVarNames[j], new Label(labelText[j]));
				map4.get(labelVarNames[j]).setLayoutX(labelLayoutX[j]);
				map4.get(labelVarNames[j]).setLayoutY(labelLayoutY[j]);
			}
		}

		// Mets les ID sur les labels pour le CSS
		map4.get("warning").setId("warning");
		map4.get("warning1").setId("warning");
		map4.get("ask").setId("help");
		map4.get("wait").setId("help");
		map4.get("help").setId("help");
		map4.get("ask").setWrapText(true);
		map4.get("pot1").setId("pot");
		map4.get("pot2").setId("pot");
		map4.get("check").setId("huge");
		map4.get("waiting").setId("huge");
		map4.get("player2cash1").setId("pot");
		map4.get("player2cash2").setId("pot");
		map4.get("player1cash1").setId("pot");
		map4.get("player1cash2").setId("pot");
	}

	// Définie tout les buttons utilisés pour le jeux
	public void buttons() {
		map3 = new HashMap<String, Button>();
		buttonVarNames = new String[] { "mainMenu", "manual", "call", "fold", "bet", "raise", "check", "nextRound" };
		buttonNames = new String[] { "Main Menu", "", "Call", "Fold", "Bet", "Raise", "Check", "Next Round" };

		for (int i = 1; i <= totalPlayers; i++) {
			// Créer chaque button
			for (int j = 0; j < buttonVarNames.length; j++) {
				map3.put("player" + i + buttonVarNames[j], new Button(buttonVarNames[j]));
				map3.get("player" + i + buttonVarNames[j]).setText(buttonNames[j]);
			}

			// Définie les fonctions du button du menu principal
			map3.get("player" + i + "mainMenu").setOnAction(e -> {
				play();
				restart();
				player2stage.close();
				if (manualStage.isShowing()) {
					manualStage.close();
				}
				start(stage);
			});

			// Place les buttons
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
			map3.get("player" + i + "nextRound").setLayoutX(630);
			map3.get("player" + i + "nextRound").setLayoutY(330);
		}

		// Définie les fonctions du button pour jouer une autre fois
		map3.get("player1nextRound").setOnAction(e -> {
			play();
			click1 = true;

			// Enlève l'avertissement
			if (tooMuch == false) {
				map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("tooMuch"));
				tooMuch = true;
			}
			if (empty == false) {
				map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("empty"));
				empty = true;
			}

			// Si l'autre button a été actionné, enlève tout
			if (click2) {
				for (int i = 1; i <= 2; i++) {
					map6.get("player1pane").getChildren().removeAll(map.get("player1smallBack" + i));
					map6.get("player1pane").getChildren().removeAll(map.get("player2smallFace" + i));
					map6.get("player1pane").getChildren().removeAll(map.get("player1bigFace" + i));
				}
				clear();

				map6.get("player2pane").getChildren().remove(map4.get("waiting"));
				map6.get("player1pane").getChildren().remove(map4.get("check"));
			} else {

				// Enlève les cartes du joueur et mets un avis sur chaque écran
				for (int i = 1; i <= 5; i++) {
					map6.get("player1pane").getChildren().removeAll(map.get("dealer1Card" + i));
				}
				for (int i = 1; i <= 2; i++) {
					map6.get("player1pane").getChildren().removeAll(map.get("player1smallBack" + i));
					map6.get("player1pane").getChildren().removeAll(map.get("player2smallFace" + i));
					map6.get("player1pane").getChildren().removeAll(map.get("player1bigFace" + i));
				}
				map6.get("player1pane").getChildren().add(map4.get("waiting"));
				map6.get("player2pane").getChildren().add(map4.get("check"));
			}
		});
		map3.get("player2nextRound").setOnAction(e -> {
			play();
			click2 = true;

			// Enlève l'avertissement
			if (tooMuch == false) {
				map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("tooMuch"));
				tooMuch = true;
			}
			if (empty == false) {
				map6.get("player" + playerId + "pane").getChildren().removeAll(map4.get("empty"));
				empty = true;
			}

			// Si l'autre button a été actionné, enlève tout
			if (click1) {
				for (int i = 1; i <= 2; i++) {
					map6.get("player2pane").getChildren().removeAll(map.get("player2smallBack" + i));
					map6.get("player2pane").getChildren().removeAll(map.get("player1smallFace" + i));
					map6.get("player2pane").getChildren().removeAll(map.get("player2bigFace" + i));
				}
				for (int i = 1; i <= 5; i++) {
					map6.get("player2pane").getChildren().removeAll(map.get("dealer2Card" + i));
				}
				clear();

				map6.get("player1pane").getChildren().remove(map4.get("waiting"));
				map6.get("player2pane").getChildren().remove(map4.get("check"));

			} else {

				// Enlève les cartes du joueur et mets un avis sur chaque écran
				for (int i = 1; i <= 5; i++) {
					map6.get("player2pane").getChildren().removeAll(map.get("dealer2Card" + i));
				}
				for (int i = 1; i <= 2; i++) {
					map6.get("player2pane").getChildren().removeAll(map.get("player2smallBack" + i));
					map6.get("player2pane").getChildren().removeAll(map.get("player1smallFace" + i));
					map6.get("player2pane").getChildren().removeAll(map.get("player2bigFace" + i));
				}
				map6.get("player2pane").getChildren().add(map4.get("waiting"));
				map6.get("player1pane").getChildren().add(map4.get("check"));
			}
		});
	}

	// Rétablie les valeurs lorsque le joueur vais dans le menu principal
	public void restart() {
		pot = 0;
		bet = 10;
		roundId = 1;
		playerId = 1;
		turnId = 1;
		p1 = limit;
		p2 = limit;
		checkWarning = true;
		checkWarning1 = true;
		player1value = 0;
		player2value = 0;
		click1 = false;
		click2 = false;
		player1turn = true;
		player2turn = true;
		callOff1 = true;
		callOff2 = true;
		betOff1 = true;
		betOff2 = true;
		raiseOff2 = true;
		checkOff1 = true;
		checkOff2 = true;
	}

	// Réinitialise les variables pour jouer un autre ronde
	public void clear() {
		restore();
		map3.get("player1check").setDisable(true);
		p1 = map8.get("player1total");
		p2 = map8.get("player2total");
		pot = 0;
		player2stage.close();
		turnId = 1;
		map4.get("player1numberTurn").setText("Turn: " + turnId);
		map4.get("player2numberTurn").setText("Turn: " + turnId);
		roundId++;
		map4.get("player1numberRound").setText("Round: " + roundId);
		map4.get("player2numberRound").setText("Round: " + roundId);
		bet = 10 * roundId;
		raiseAmount.setText(String.valueOf(bet));
		numberPanes();
		for (int i = 1; i <= totalPlayers; i++) {
			int k = 0;
			for (int j = 0; j < tokensVarNames.length; j++) {
				for (int t = 1; t <= map9.get("player" + i + "numbersToken").get(k); t++) {
					map6.get("player" + i + "pane").getChildren().add(map7.get("player" + i + tokensVarNames[j] + t));
				}
				k++;
			}
		}
		cards();
		player1Conditions();
		player2Conditions();
		player1();
		map3.get("player1fold").setDisable(false);
		map3.get("player1bet").setDisable(false);
	}

	// Détermines tout les cartes (devant/arrière/petite/grande) utilisés dans
	// une ronde
	public void cards() {

		premierDebut();

		// Références aux images de cartes
		map = new HashMap<String, ImageView>();

		x = 0;
		for (int j = 1; j <= totalPlayers; j++) {
			// Définie tout les cartes pour tous les joueurs
			for (int i = 1; i < 53; i++) {
				map.put("player" + j + "card" + i,
						new ImageView(new Image(getClass().getResourceAsStream(i + ".png"))));
			}
			// Définie les cartes de dot
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

			// Définie les carte de faces pour les joueurs
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

			// Définie les cartes pour le dealer
			for (int i = 0; i < 5; i++) {
				x = x + 1;
				dealer = "player" + j + "card" + map2.get("dealerResultat").get(i);
				var = "dealer" + j + "Card" + x;
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
	}

	// Selection de nombre aléatoire pour les cartes
	public void selection() {
		rand = 1 + (int) (Math.random() * ((52 - 1) + 1));
		while (map2.get("resultat1").contains(rand) || map2.get("resultat2").contains(rand)
				|| map2.get("dealerResultat").contains(rand)) {
			rand = 1 + (int) (Math.random() * ((52 - 1) + 1));
		}
	}

	// Détermine les cartes des joueurs
	public void premierDebut() {
		// Garde les cartes des joueurs
		map2 = new HashMap<String, ArrayList<Integer>>();
		map2.put("resultat2", new ArrayList<Integer>());
		map2.put("resultat1", new ArrayList<Integer>());
		map2.put("dealerResultat", new ArrayList<Integer>());

		// Distribue les cartes aux joueurs
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

	// Part l'application
	public static void main(String[] args) {
		launch(args);
	}
}
