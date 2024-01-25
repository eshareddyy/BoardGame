package mancala;

/**
 * Abstract class representing the rules of a Mancala game. KalahRules and
 * AyoRules will subclass this class.
 */
public abstract class GameRules {
	private int currPlayer = 1;
	private MancalaDataStructure gameBoard;

	/**
	 * Constructor to initialize the game board.
	 */
	public GameRules() {
		gameBoard = new MancalaDataStructure();
	}

	// This method here returns the data structure for the extending classes
	public MancalaDataStructure giveGameBoard() {
		return gameBoard;
	}

	/**
	 * Get the number of stones in a pit.
	 *
	 * @param pitNum The number of the pit.
	 * @return The number of stones in the pit.
	 */
	public int getNumStones(final int pitNum) {
		return gameBoard.getNumStones(pitNum);
	}

	public int getCurrentPlayer() {
		return currPlayer;
	}

	/**
	 * Get the game data structure.
	 *
	 * @return The MancalaDataStructure.
	 */
	public MancalaDataStructure getDataStructure() {
		return gameBoard;
	}

	/**
	 * Check if a side (player's pits) is empty.
	 *
	 * @param pitNum The number of a pit in the side.
	 * @return True if the side is empty, false otherwise.
	 */
	public boolean isSideEmpty(final int pitNum) {
	
		int startIndex = (pitNum <= 6) ? 0 : 6;
		int endIndex = (pitNum <= 6) ? 6 : 12;

		// return false if stones r there
		for (int i = startIndex; i < endIndex; i++) {
			if (getNumStones(i) != 0) {
				return false;
			}
		}
		///true if none
		return true;
	}

	/**
	 * Set the current player.
	 *
	 * @param playerNum The player number (1 or 2).
	 */
	public void setPlayer(final int playerNum) {
		currPlayer = playerNum;
	}

	/**
	 * Perform a move and return the number of stones added to the player's store.
	 *
	 * @param startPit  The starting pit for the move.
	 * @param playerNum The player making the move.
	 * @return The number of stones added to the player's store.
	 * @throws InvalidMoveException If the move is invalid.
	 */
	public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

	/**
	 * Distribute stones from a pit and return the number distributed.
	 *
	 * @param startPit The starting pit for distribution.
	 * @return The number of stones distributed.
	 */
	public abstract int distributeStones(int startPit);

	/**
	 * Capture stones from the opponent's pit and return the number captured.
	 *
	 * @param stoppingPoint The stopping point for capturing stones.
	 * @return The number of stones captured.
	 */
	public abstract int captureStones(int stoppingPoint); // capture stones

	/**
	 * Register two players and set their stores on the board.
	 *
	 * @param one The first player.
	 * @param two The second player.
	 */
	public void registerPlayers(final Player one, final Player two) {
		// this method can be implemented in the abstract class.
		/*
		 * make a new store in this method, set the owner then use the
		 * setStore(store,playerNum) method of the data structure
		 */

		final Store playerOne = new Store(one);
		final Store playerTwo = new Store(two);

		one.setStore(playerOne);
		two.setStore(playerTwo);

		gameBoard.setStore(playerOne, 1);
		gameBoard.setStore(playerTwo, 2);
	}

	/**
	 * Reset the game board by setting up pits and emptying stores.
	 */
	public void resetBoard() {
		gameBoard.setUpPits();
		gameBoard.emptyStores();
	}

	@Override
	public String toString() {
		// Implement toString() method logic here.
		final StringBuilder board = new StringBuilder();

		board.append("P1 Store: ").append(" | ");
		for (int i = 6; i >= 1; i--) {
			board.append("P1 - ").append(i).append(":").append("|");
		}
		board.append("P2 Store: ");
		board.append("\n");
		board.append(gameBoard.getStoreCount(1));
		board.append("\t");
		for (int i = 6; i >= 1; i--) {
			board.append("\t");
			board.append(gameBoard.getNumStones(i - 1));
		}
		board.append("\t");
		board.append(gameBoard.getStoreCount(2));
		board.append("\n");
		board.append("________________________________________________________________________");
		board.append("\n");
		board.append("\t");
		for (int i = 7; i <= 12; i++) {
			board.append("\t");
			board.append(gameBoard.getNumStones(i - 1));
		}
		board.append("\n");
		board.append("   \t    ");

		for (int i = 7; i <= 12; i++) {
			board.append("P2 - ").append(i).append(":").append("|");
		}

		return board.toString();
	}

}
