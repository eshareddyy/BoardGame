package mancala;

public class UserProfile implements java.io.Serializable {

	private static final String serialVersionUID = "123456789L";

	private String userName;
	private int kalahGames;
	private int ayoGames;
	private int kalahGamesWon;
	private int ayoGamesWon;

	/**
     * Initializes a user profile with the provided name and default values for game statistics.
     *
     * @param name The name of the user.
     */

	public UserProfile(final String name) {
		// Initialize user profile with provided name
		this.userName = name;
		this.ayoGames = 0;
		this.kalahGames = 0;
		this.kalahGamesWon = 0;
		this.ayoGamesWon = 0;
	}


	/**
     * Initializes a user profile with default values for game statistics and an empty name.
     */
	public UserProfile() {
		// Initialize user profile with default values
		this.ayoGames = 0;
	    this.kalahGames = 0;

        this.userName = "";

	    this.kalahGamesWon = 0;
		this.ayoGamesWon = 0;
	}

	/**
     * Initializes a user profile with the provided values for game statistics.
     *
     * @param name           The name of the user.
     * @param kalahGames     The number of Kalah games played.
     * @param ayoGames       The number of Ayo games played.
     * @param kalahGamesWon  The number of Kalah games won.
     * @param ayoGamesWon    The number of Ayo games won.
     */
	public UserProfile(final String name, final int kalahGames, final int ayoGames, final int kalahGamesWon,
		final int ayoGamesWon) {
		// Initialize user profile with provided values

		this.kalahGames = kalahGames;
		this.ayoGames = ayoGames;

        this.userName = name;

		this.kalahGamesWon = kalahGamesWon;
		this.ayoGamesWon = ayoGamesWon;
	}


    /**
     * Gets the name of the user.
     *
     * @return The user's name.
     */

	public String getName() {
		// Get the user's name
		return this.userName;
	}

	/**
     * Sets the name of the user.
     *
     * @param name The new name for the user.
     */
	public void setName(final String name) {
		// Set the user's name
		this.userName = name;
	}

	/**
     * Gets the number of Kalah games won by the user.
     *
     * @return The number of Kalah games won.
     */
	public int getNumKalahGamesWin() {
		// Get the number of Kalah games won
		return this.kalahGamesWon;
	}

	/**
     * Gets the total number of Kalah games played by the user.
     *
     * @return The total number of Kalah games played.
     */
	public int getKalahGames() {
		// Get the total number of Kalah games played
		return this.kalahGames;
	}


	 /**
     * Records a win in Ayo games by incrementing the count.
     */
	public void recordAyoGamesWin() {
		// Increment the number of Ayo games won
		this.ayoGamesWon = this.ayoGamesWon + 1;
	}

	/**
     * Increments the total number of Ayo games played.
     */
    public void incrementAyoGames() {
		// Increment the total number of Ayo games played
		this.ayoGames = this.ayoGames + 1;
	}

	/**
     * Gets the total number of Ayo games played by the user.
     *
     * @return The total number of Ayo games played.
     */
	public int getAyoGames() {
		// Get the total number of Ayo games played
		return this.ayoGames;
	}

	  /**
     * Gets the number of Ayo games won by the user.
     *
     * @return The number of Ayo games won.
     */
	public int getNumAyoGamesWin() {
		// Get the number of Ayo games won
		return this.ayoGamesWon;
	}

	 /**
     * Increments the total number of Kalah games played.
     */
	public void incrementKalGames() {
		// Increment the total number of Kalah games played
		this.kalahGames = this.kalahGames + 1;
	}

	/**
     * Records a win in Kalah games by incrementing the count.
     */
	public void recordKalahGamesWin() {
		// Increment the number of Kalah games won
		this.kalahGamesWon = this.kalahGamesWon + 1;
	}

}