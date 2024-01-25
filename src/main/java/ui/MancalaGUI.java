package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


import mancala.GameRules;
import mancala.KalahRules;
import mancala.MancalaDataStructure;
import mancala.MancalaGame;
import mancala.Player;
import mancala.Saver;


public class MancalaGUI extends JFrame {
	private static final String serialVersionUID = "123456789L";
	// MancalaGUI for mancala game
	private static final int WIDTH = 650;
	private static final int HEIGHT = 400;
	private MancalaGame game;
	private GameRules rules;
	private MancalaDataStructure data;
	private ArrayList<JButton> pitList;
	private ArrayList<JLabel> stores;
	private JButton currentBtn;
	private  Player player1;
	private  Player player2;
	JPanel scorePanel = new JPanel(new FlowLayout());
	JPanel scorePane2 = new JPanel(new FlowLayout());


	JPanel pitPanel = new JPanel(new FlowLayout());

	public MancalaGUI() {
		super();

		setSize(WIDTH, HEIGHT);
		setTitle("Mancala Game");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addBothPlayers();
		
		game = new MancalaGame(player1,player2);
		game.setPlayers(player1, player2);
		rules = game.getBoard();
		data = rules.giveGameBoard();

		initializePitButtons();

		setupStores();
		setLayout(new FlowLayout());
		add(createLoadSaveClosePanel());
		
		
		
		JLabel currentLbl = new JLabel("Current Player  :");
		currentLbl.setPreferredSize(new Dimension(100, 50)); 
		currentLbl.setSize(getPreferredSize());
		add(currentLbl);
		currentBtn = new JButton("Current Player  : "+player1.getName());
		add(currentBtn);
		
		add(setBackgroundPits());
		add(setBackgroundPits2());

		add(createFeaturesButtons());
		
		add(addStores());
		//addBothPlayers();
		add(resultsDisplay());
		add(resultsDisplay2());

		validate();
		repaint();

		setVisible(true);
	}

	public void addBothPlayers() {
		
		
		JFrame frame = new JFrame("String Input Dialog");

        // pop up will appear to show user input
        String userInput1 = JOptionPane.showInputDialog(frame, "Enter a First Player Name :");
        String userInput2 = JOptionPane.showInputDialog(frame, "Enter a Second  Player Name  :");
        
        player1  = new Player(userInput1);
		player2 =  new Player(userInput2);
		 
 	    
 	  
 	   
		
	}
	private void gameInitialized(GameRules rules) {
		// Kalah or Ayo gets loaded here
		game.startNewGame(rules); 
		rules = game.getBoard(); 
		data = rules.giveGameBoard(); 
		
	}

	public void gameInitialized() {  //method to start new game
        //adds methods after this
		game.startNewGame(); 
        //methid gets added later
		rules = game.getBoard(); 
		data = rules.giveGameBoard(); 
		updatePits();
		updateStores();
	}

	public void startGameLoaded(MancalaGame gameToLoad) {
		//load game that has already started
		game = gameToLoad;
		rules = game.getBoard();
		data = rules.giveGameBoard();
	}

	//handle the pits
	private JPanel setBackgroundPits() {
		//creating the pit background
		JPanel pitPanel = new JPanel();
		for (int i = 0; i < 6; i++) {
			pitPanel.add(pitList.get(i));
		}
		return pitPanel;
	}

	private JPanel setBackgroundPits2() {
		//creating the pit background
		JPanel pitPanel = new JPanel();
		for (int i = 6; i < 12; i++) {
			pitPanel.add(pitList.get(i));
		}
		return pitPanel;
	}
  
	private void initializePitButtons() {
		pitList = new ArrayList<>();

		for (int i = 1; i <= 6; i++) {
			JButton button = new JButton("PIT " + (i) + ": " + game.getNumStones(i));
            //can edit the height or width here
			button.setPreferredSize(new Dimension(80, 40)); 

            //adjusts the fiont details here 
			Font buttonFont = new Font("Arial", Font.BOLD, 10); 
			button.setFont(buttonFont);

			//set the coloir of the text to pink
			Color buttonColor = Color.PINK; 
			button.setBackground(buttonColor);

			pitList.add(button);
			final int value = i;
			button.addActionListener(e -> handlePit(value));
			pitPanel.add(button);

		}

        //set he height
		pitPanel.add(Box.createHorizontalStrut(100)); 

		//each button for pits 7-12 created below
		for (int i = 7; i <= 12; i++) {
			JButton button = new JButton("PIT " + (i) + ": " + game.getNumStones(i));
			button.setPreferredSize(new Dimension(80, 40)); 
			Font buttonFont = new Font("Arial", Font.BOLD, 10); 
			button.setFont(buttonFont);

			//adjust the text colour here 
			Color buttonColor = Color.CYAN; //set to CYAN
			button.setBackground(buttonColor);
			pitList.add(button);
			final int value = i;
			button.addActionListener(e -> handlePit(value));
			pitPanel.add(button);
		}
	}

	private void handlePit(int index) {
		
		try {
			game.move(index);
			currentBtn.setText(game.getCurrentPlayer().getName());	
			updatePits();
			updateStores();
			
			chooseTheWinner();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void updatePits() {
		// Update the pits
		for (int i = 1; i <= 12; i++) {
			pitList.get(i - 1).setText("PIT " + (i) + ": " + game.getNumStones(i));
		}
	}
	
	
	private void updateStores() {
		// update all the stores
		stores.get(0).setText("Player 1: " + data.getStoreCount(1));
		stores.get(1).setText("Player 2: " + data.getStoreCount(2));

	}

    // making  new  a load and save panel
	private JPanel createLoadSaveClosePanel() {
	
		JPanel panel = new JPanel();
		panel.add(buttonForAyo());
		panel.add(buttonForKalah());

		return panel;
	}

	
    // making  new  a load and save panel
	private JPanel addStores() {
		
		JPanel panel = new JPanel();
		panel.add(stores.get(1));
		panel.add(stores.get(0));

		return panel;
	}

	private JPanel resultsDisplay() {

		
		
		game.getCurrentPlayer().getProfile().recordAyoGamesWin();
		game.getCurrentPlayer().getProfile().incrementKalGames();
		game.getCurrentPlayer().getProfile().incrementAyoGames();
		game.getCurrentPlayer();
		   
		JButton playerA = new JButton("Player 1  :" + player1.getProfile().getName() + "   ||     Ayo Played :" + player1.getProfile().getAyoGames()
				+ "    Ayo Won : " + player1.getProfile().getNumAyoGamesWin() + " || Kalah Player  : " + player1.getProfile().getKalahGames()
				+ "     Kalah Won : " + player1.getProfile().getNumKalahGamesWin());

		playerA.setBackground(Color.ORANGE);
		scorePanel.add(playerA);

		return scorePanel;
	}

	private JPanel resultsDisplay2() {

			
		
		game.getCurrentPlayer().getProfile().recordAyoGamesWin();
		game.getCurrentPlayer().getProfile().incrementKalGames();
		game.getCurrentPlayer().getProfile().incrementAyoGames();
		
		
		
		JButton playerB = new JButton("Player 2    :" + player2.getProfile().getName() + "   ||     Ayo Played :" + player2.getProfile().getKalahGames()
				+ "    Ayo Won : " + player2.getProfile().getNumAyoGamesWin() + " || Kalah Player  : " + player2.getProfile().getKalahGames()
				+ "     Kalah Won : " + player2.getProfile().getNumKalahGamesWin());

		playerB.setBackground(Color.ORANGE);

		scorePane2.add(playerB);

		return scorePane2;
	}
	
	
	
	

    //method to save to the file here
	private void saveToFile() {

		String filePath = game.getCurrentPlayer().getName().replace(" ", "");

		try {
			Saver.saveObject(game, filePath);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}


    //method to load previous game
	private void loadGame() {
		String filePath = game.getCurrentPlayer().getName().replace(" ", "");
		try {
			game = (MancalaGame) Saver.loadObject(filePath);
			

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Player does not have any backup...", "Save State First",
			        JOptionPane.INFORMATION_MESSAGE);

			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Player does not have any backup...", "Save State First",
			        JOptionPane.INFORMATION_MESSAGE);

		
		}
	}

    //methd ot set up stores for each player 
	private void setupStores() {
		stores = new ArrayList<>();
		playerOneStore();
		playerTwoStore();
	}

    //creates the store for player one 
	private void playerTwoStore() {
		stores.add(1, new JLabel("Player 2     : " + data.getStoreCount(2)));
		Font labelFont = new Font("Arial", Font.BOLD, 30); 
		stores.get(1).setFont(labelFont);
		Color labelColor = Color.RED; 
		stores.get(1).setForeground(labelColor);
	}

    //creates the store for player one 
	private void playerOneStore() {
		stores.add(0, new JLabel("    ||  Player 1      :" + data.getStoreCount(1)));
		Font labelFont = new Font("Arial", Font.BOLD, 30); 
		stores.get(0).setFont(labelFont);
		Color labelColor = Color.RED; 
		stores.get(0).setForeground(labelColor);
	}

	

	private JButton buttonForKalah() {
	
		JButton button = new JButton("New Kalah Game");

		// Change the font and style
		//Font buttonFont = new Font("Arial", Font.BOLD, 16); //

		button.addActionListener(e -> gameInitialized(new KalahRules()));
		game.getCurrentPlayer().getProfile().incrementKalGames();
		//userOne.incrementKalGames();
		//userTwo.incrementKalGames();
		
		return button;
	}

	private JButton buttonForAyo() {
	
		JButton button = new JButton("New Ayo Game");

		// Change the font and style
		//Font buttonFont = new Font("Arial", Font.BOLD, 16); // Replace "Arial" with your desired font
		
			game.getCurrentPlayer().getProfile().incrementAyoGames();
			//if(game.getCurrentPlayer())
			
		return button;
	}

		

	private void closeGame() {
		// close the game
		System.exit(0);
	}

	private void chooseTheWinner() {
		try {
			Player winner = game.getWinner();
			//UserProfile userWinner = winner.getProfile();

			if (rules instanceof KalahRules) {
				game.getCurrentPlayer().getProfile().incrementKalGames();
				
				//incrementKalGames();
				
			} else {
				//incrementAyoGames();
				
			}
			popupWinner(winner);
		} catch (Exception e) {
		}

	}

	private void popupWinner(Player winner) {

		JOptionPane.showMessageDialog(this, "Winner! " + winner.getName(), "Game Over",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private JPanel createFeaturesButtons() {

		JPanel buttonPanel = new JPanel(new FlowLayout());

		// Options buttons
		JButton closeButton = new JButton("Close Game");
		closeButton.setBackground(Color.GREEN);

		closeButton.addActionListener(e -> closeGame());

		JButton saveButton = new JButton("Save Game State");
		saveButton.setBackground(Color.GREEN);
		saveButton.addActionListener(e -> saveToFile());

		JButton loadButton = new JButton("Load Previous State");
		loadButton.setBackground(Color.GREEN);
		loadButton.addActionListener(e -> loadGame());

		buttonPanel.add(closeButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);

		return buttonPanel;
	}

	

	public static void main(String[] args) {
		// Main
		SwingUtilities.invokeLater(() -> new MancalaGUI());

	}
}
