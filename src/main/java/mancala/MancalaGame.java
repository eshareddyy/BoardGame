package mancala;

public class MancalaGame implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private MancalaDataStructure board;

	private Boolean finishedGame;
	private Player currentPlayer;
	private GameRules rules;
	private Player player1;
	private Player player2;
	//private UserProfile userProfile;

	 /**
     * Default constructor for MancalaGame.
     * Initializes the game with default KalahRules, a game board, and sets up players.
     */
	public MancalaGame() {
		this.rules = new KalahRules();
		this.board = rules.giveGameBoard();
		finishedGame = false;

		this.setPlayers(new Player("One"), new Player("Two"));
		this.currentPlayer = player1;
	
		
	}

	/**
     * Constructor for MancalaGame with specified players.
     *
     * @param player1 The first player.
     * @param player2 The second player.
     */
	public MancalaGame(final Player player1, final Player player2) {

		this.rules = new KalahRules();
		this.board = rules.giveGameBoard();

		rules.registerPlayers(player1, player2);
		setPlayers(player1, player2);

		this.currentPlayer = player1;
		this.finishedGame = false;
		
	}

	/**
     * Gets the game board.
     *
     * @return The GameRules representing the game board.
     */
	// getter to return the game board
	public GameRules getBoard() {
		return rules;
	}

	/**
     * Sets the game board.
     *
     * @param theBoard The GameRules to set as the game board.
     */
	public void setBoard(final GameRules theBoard) {
		rules = theBoard;
	}

	/**
     * Gets the total number of stones in a player's store.
     *
     * @param player The player whose store count is to be retrieved.
     * @return The total number of stones in the player's store.
     * @throws NoSuchPlayerException If the player is null.
     */
	// get the total # of stones in a player's store
	public int getStoreCount(final Player player) throws NoSuchPlayerException {
		if (player != null) {
			return player.getStoreCount();
		} else {
			throw new NoSuchPlayerException();
		}
	}

	/**
     * Sets the current player for the game.
     *
     * @param playerNow The player to set as the current player.
     */
	public void setCurrentPlayer(final Player playerNow) {

		currentPlayer = playerNow;

		if (playerNow.equals(player1)) {
			rules.setPlayer(1);
		} else if (playerNow.equals(player2)) {
			rules.setPlayer(2);
		}
	}

	/**
     * Gets the current player.
     *
     * @return The current player.
     */
	// getter to return the current player
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
     * Gets the number of stones in a specified pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the specified pit.
     */
	public int getNumStones(final int pitNum) {
		return board.getNumStones(pitNum);
	}

	/**
     * Sets the players for the game.
     *
     * @param onePlayer The first player.
     * @param twoPlayer The second player.
     */
	public void setPlayers(final Player onePlayer, final Player twoPlayer) {

		if ((onePlayer.getName() != null) && !onePlayer.getName().isBlank()) {
			player1 = onePlayer;
			
		} else {
			return;
		}

		if ((twoPlayer.getName() != null) && !twoPlayer.getName().isBlank()) {
			player2 = twoPlayer;
		} else {
			return;
		}

		rules.registerPlayers(onePlayer, twoPlayer);
	}

	/**
     * Gets the winner of the game.
     *
     * @return The winning player.
     * @throws GameNotOverException If the game is not finished.
     */
	public Player getWinner() throws GameNotOverException {

		Player winner = null;

		if (!finishedGame) {
			throw new GameNotOverException();
		}

		int playerOneStones = 0;
		int playerTwoStones = 0;

		if (player1 != null && player2 != null) {

			playerOneStones = player1.getStoreCount();
			playerTwoStones = player2.getStoreCount();

			if (playerOneStones > playerTwoStones) {
				winner = player1;
			} else if (playerOneStones < playerTwoStones) {
				winner = player2;
			}
		}

		return winner;
	}

	// starts a new game
	public void startNewGame() {
		rules.resetBoard();
		board = rules.giveGameBoard();
	}

	// Start a new game by resetting the board.
	 /**
     * Starts a new game with a specified set of rules.
     *
     * @param rules The GameRules to set for the new game.
     */
	public void startNewGame(final GameRules rules) {
		setBoard(rules);
		board = rules.giveGameBoard();
	}

	/**
     * Moves stones in the game based on the selected starting pit.
     *
     * @param startPit The starting pit from which stones are to be moved.
     * @return The number of stones moved, or -1 if the move is invalid.
     */
	public int move(final int startPit) {
		// Determine the player flag based on the current player
		int playerFlag = (currentPlayer.equals(player2)) ? 2 : 1;
		int numStones = -1;

		try {

			if (isValidMove(startPit, playerFlag)) {

				numStones = rules.moveStones(startPit, playerFlag);

				// Check if it's a KalahRules and there is no free turn, then switch players
				if (rules instanceof KalahRules) {
					//seeing if the getFreeTurn is set
					if (!(((KalahRules)rules).getFreeTurn())){
						//switching players
						currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
					}
				} else {
					// Switch players
					currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
				}
			}
		} catch (InvalidMoveException e) {
			// If an invalid move exception occurs, set numStones to -1
			numStones = -1;
		}

		return numStones;
	}

	private boolean isValidMove(final int startPit, final int playerFlag) {

		// Check if the move is valid based on the player's turn and the selected pit
		boolean validMove = false;

		if (!((startPit < 7 && playerFlag == 2) || (startPit > 6 && playerFlag == 1))) {
			validMove = true;
		}
		return validMove;
	}

	@Override
	public String toString() {
		return "GAME:" + rules.toString();
	}
}
