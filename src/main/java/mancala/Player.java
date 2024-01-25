package mancala;


public class Player implements java.io.Serializable {
	private String name;
	private Store store;
	private UserProfile userProfile;
	private static final String serialVersionUID = "123456789L";

	public Player() {
		this.store = new Store();
		this.userProfile = new UserProfile();
		name = "New Player"; // new player
		userProfile.setName("New Player");
	}

	 /**
     * Initializes a player with a specified user profile.
     *
     * @param userProfile The user profile to associate with the player.
     */
	public Player(final UserProfile userProfile) {
		name = userProfile.getName();
		this.store = new Store();
	}

	  /**
     * Initializes a player with a specified name.
     *
     * @param setName The name to set for the player.
     */
	public Player(final String setName) {
		this.userProfile = new UserProfile();
		name = setName;
		userProfile.setName(setName);
		this.store = new Store();
	}

	  /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
	public String getName() {
		return name;
	}

	/**
     * Gets the user profile associated with the player.
     *
     * @return The user profile of the player.
     */
	public UserProfile getProfile() {
		return userProfile;
	}

	/**
     * Sets the user profile for the player.
     *
     * @param userProfile The user profile to set for the player.
     */
	public void setProfile(final UserProfile userProfile) {
		this.userProfile = userProfile;
		this.name = userProfile.getName();
	}

	  /**
     * Gets the store associated with the player.
     *
     * @return The store of the player.
     */
	public Store getStore() {
		return this.store;
	}

	   /**
     * Gets the stone count in the player's store.
     *
     * @return The stone count in the player's store.
     */
	public int getStoreCount() {
		return this.store.getTotalStones();
	}

	  /**
     * Sets the name for the player.
     *
     * @param setName The name to set for the player.
     */
	public void setName(final String setName) {
		this.name = setName;
		this.userProfile.setName(setName);
	}

	/**
     * Sets the store for the player.
     *
     * @param setStore The store to set for the player.
     */
	public void setStore(final Store setStore) {
		this.store = setStore;
	}

	/**
     * Returns a string representation of the player.
     *
     * @return A string representation of the player.
     */
	@Override
	public String toString() {
		return userProfile.getName();
	}
}