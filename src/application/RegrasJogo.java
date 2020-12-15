package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class RegrasJogo {

	// Verificar Royal Flussh
	private static Boolean royalStraighFlush = false;

	// Numero de cartas com naipes
	private static int clubsNumber = 0, spadesNumber = 0, heartsNumber = 0, diamondsNumber = 0;

	// Controle de Numero de cartas de Tipo de Naipe
	private static int number = 0;
	private static String kind = "";

	// Onde  guardo o valor das cartas 
	private static ArrayList<Integer> straightRepeat, straightNoRepeat;
	
	// strign que guarda a vitoria ou nao
	private static String win;

	// Valores  ou array que guarda o naipe das cartas
	private static ArrayList<Integer> clubs, spades, hearts, diamonds;
	
	// Array  principal que guarda as cartas
	private static ArrayList<Integer> cartes;
	
	// Pour créer la liste straightNoRepeat && trouver la somme
	private static int current, count = 0, sum = 0;
	private static LinkedHashSet<Integer> set;
	
	// Entrepose le nombre de carte entre 2-14
	private static Map<String, Integer> map;
	private static String[] names;
	
	// vetor de controle que diz em qual turno está
	private static String[] turn;
	
	public static String[] evaluate(ArrayList<Integer> player, ArrayList<Integer> dealer) {

		// Para cada jogador  crio  as cartas dealer é Mesa
		cartes = new ArrayList<Integer>();
		cartes.addAll(player);
		cartes.addAll(dealer);

		// Soteia   as cartas
		Collections.sort(cartes);

		// Definicao das cartas e quantidade de tipos para cada espada, ouros, copas..
		clubs = new ArrayList<Integer>();
		spades = new ArrayList<Integer>();
		hearts = new ArrayList<Integer>();
		diamonds = new ArrayList<Integer>();
		for (int i = 1; i <= 13; i++) {
			clubs.add(i);
			spades.add(13 + i);
			hearts.add(26 + i);
			diamonds.add(39 + i);
		}

		// Veifica o numeo de cartas de cada tipo Sondar a Mesa e verificar quem ganha depois
		for (int i = 0; i <= 12; i++) {
			if (cartes.contains(clubs.get(i))) {
				clubsNumber = clubsNumber + 1;
			}
			if (cartes.contains(spades.get(i))) {
				spadesNumber = spadesNumber + 1;
			}
			if (cartes.contains(hearts.get(i))) {
				heartsNumber = heartsNumber + 1;
			}
			if (cartes.contains(diamonds.get(i))) {
				diamondsNumber = diamondsNumber + 1;
			}
		}

		// Quais foram os tipos encontrados mais
		if (number < clubsNumber) {
			number = clubsNumber;
			kind = "clubs";
		}
		if (number < spadesNumber) {
			number = spadesNumber;
			kind = "spades";
		}
		if (number < heartsNumber) {
			number = heartsNumber;
			kind = "hearts";
		}
		if (number < diamondsNumber) {
			number = diamondsNumber;
			kind = "diamonds";
		}

		// verificar uma sequencia de cartas seqguem ordem
		if (number >= 5) {
			for (int i = 0; i < cartes.size() - 2; i++) {
				try {
				if (cartes.get(i + 1) == cartes.get(i) + 1 && cartes.get(i + 2) == cartes.get(i) + 2
						&& cartes.get(i + 3) == cartes.get(i) + 3 && cartes.get(i + 4) == cartes.get(i) + 4) {
					for (int j = 0; j < 5; j++) {
						hand = hand + cartes.get(i + j);
					}
					royalStraighFlush = true;
					break;
				}
				} catch (IndexOutOfBoundsException e) {
					break;
				}
			}
		}
		
		// Verificaça de cartas na Mesa e mao e calcular o total 2 players 
		straightRepeat = new ArrayList<Integer>();
		for (int e : cartes) {
			current = cartes.get(count);
			current++;
			if (e <= 13) {
			} else if (e <= 26) {
				current = current - 13;
			} else if (e <= 39) {
				current = current - 26;
			} else if (e <= 52) {
				current = current - 39;
			}
			straightRepeat.add(current);
			count++;
			sum = sum + current;
		}

		// Remover cartas duplocadas e  colocar de volta no ArrayList
		set = new LinkedHashSet<Integer>();
		set.addAll(straightRepeat);
		straightNoRepeat = new ArrayList<Integer>();
		straightNoRepeat.addAll(set);

		// Classificar o tipo de Straight  mao maior
		Collections.sort(straightNoRepeat);
		Collections.sort(straightRepeat);

		// Compara a sequencia para ver  ordem do straight 
		map = new HashMap<String, Integer>();
		names = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], 0);
		}
		for (int e : straightRepeat) {
			map.replace(String.valueOf(e), map.get(String.valueOf(e)) + 1);
		}

		main: while (true) {
			// Definiçao de qual mao é maior   royalStraighFlush 1°
			if (royalStraighFlush) {
				if (String.valueOf(kind).equals("clubs")) {
					if (cartes.contains(13)) {
						win = "royalFlush";
					} else {
						win = "straightFlush";
					}
				} else if (String.valueOf(kind).equals("spades")) {
					if (cartes.contains(26)) {
						win = "royalFlush";
					} else {
						win = "straightFlush";
					}
				} else if (String.valueOf(kind).equals("hearts")) {
					if (cartes.contains(39)) {
						win = "royalFlush";
					} else {
						win = "straightFlush";
					}
				} else if (String.valueOf(kind).equals("diamonds")) {
					if (cartes.contains(52)) {
						win = "royalFlush";
					} else {
						win = "straightFlush";
					}
				}
				break main;
			}

			// Determinar se é par de 4  fourKind 2°
			for (String e : names) {
				if (map.get(e) == 4) {
					hand = Integer.valueOf(e) * 4;
					win = "fourOfAKind";
					break main;
				}
			}

			// Determinar um Full House °3
			for (String e : names) {
				if (map.get(e) == 3) {
					for (String f : names) {
						if (map.get(f) == 2) {
							hand = (Integer.valueOf(e) * 3) + (Integer.valueOf(f) * 2);
							win = "fullHouse";
							break main;
						}
					}
				}
			}

			// Determinar se é um flush 4°
			if (number >= 5) {
				int i = 0;
				if (String.valueOf(kind).equals("clubs")){
				} else if (String.valueOf(kind).equals("spades")){
					i = 13;
				} else if (String.valueOf(kind).equals("hearts")){
					i = 26;
				} else if (String.valueOf(kind).equals("diamonds")){
					i = 39;
				}
				for (int e : cartes){
					if (e > i && e <= i + 13){
						hand = hand + (e - i);
					}
				}
				win = "flush";
				break main;
			}

			// Determina se é uma sequencia 5 cartas 5
			if (straightNoRepeat.size() >= 5) {
				for (int i = 0; i < straightNoRepeat.size() - 2; i++) {
					try {
						if (straightNoRepeat.get(i + 1) == straightNoRepeat.get(i) + 1
								&& straightNoRepeat.get(i + 2) == straightNoRepeat.get(i) + 2
								&& straightNoRepeat.get(i + 3) == straightNoRepeat.get(i) + 3
								&& straightNoRepeat.get(i + 4) == straightNoRepeat.get(i) + 4) {

							for (int j = 0; j < 5; j++) {
								hand = hand + straightNoRepeat.get(i + j);
							}
							win = "straight";
							break main;
						}
					} catch (IndexOutOfBoundsException e) {
						break;
					}

				}
			}

			// Drtermina se sao dois pares
			for (String e : names) {
				if (map.get(e) >= 2) {
					for (String f : names) {
						if (map.get(f) >= 2 && f != e) {
							hand = (Integer.valueOf(e) * 2) + (Integer.valueOf(f) * 2);
							win = "twoPair";
							break main;
						}
					}
				}
			}

			// Determine se é uma trinca
			for (String e : names) {
				if (map.get(e) == 3) {
					hand = Integer.valueOf(e) * 3;
					win = "threeOfAKind";
					break main;
				}
			}

			// Determine se é par
			for (String e : names) {
				if (map.get(e) == 2) {
					hand = Integer.valueOf(e) * 2;
					win = "onePair";
					break main;
				}
			}

			// Determina se nao há nenhuma e ganha maiores cartas
			hand = sum;
			win = "highCards";
			break main;

		}

		// pego tudo do turno e retorno vencedor , soma valores  de mao e pote
		turn = new String[] { win, String.valueOf(sum), String.valueOf(hand) };
		clear();
		return turn;
	}

	private static int hand = 0;;

	// Restauro as variaveis para proximo turno
	public static void clear() {
		set.clear();
		straightNoRepeat.clear();
		straightRepeat.clear();
		win = "";
		number = 0;
		kind = "";
		clubsNumber = 0;
		spadesNumber = 0;
		heartsNumber = 0;
		diamondsNumber = 0;
		royalStraighFlush = false;
		hand = 0;
		sum = 0;
		count = 0;
	}
}
