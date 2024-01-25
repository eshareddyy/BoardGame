package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable {

	private Player owner;
	private int stones;
	private int freeTurn;

	public Store() {
		// Initialize store
		stones = 0;
	}

	/**
     * Initializes an empty store.
     */
	public void setOwner(Player player) {
		this.owner = player;
	}

	  /**
     * Initializes a store with a specified owner.
     *
     * @param player The owner of the store.
     */
	public Store(Player player) {
		owner = player;
	}

	  /**
     * Gets the owner of the store.
     *
     * @return The owner of the store.
     */
	public Player getOwner() {
		return owner;
	}

	 /**
     * Gets the current stone count in the store.
     *
     * @return The number of stones in the store.
     */
	@Override
	public int getStoneCount() {
		return stones;
	}

	/**
     * Adds a stone to the store.
     */
	@Override
	public void addStone() {
		stones++;
	}

	 /**
     * Adds a specified number of stones to the store.
     *
     * @param amount The number of stones to add.
     */
	@Override
	public void addStones(int amount) {
		stones += amount;
	}

	  /**
     * Removes all stones from the store and returns the removed count.
     *
     * @return The number of stones removed.
     */
	@Override
	public int removeStones() {
		int removedStones = stones;
		stones = 0;
		return removedStones;
	}

	 /**
     * Gets the total stone count in the store.
     *
     * @return The total number of stones in the store.
     */
	public int getTotalStones() {
		return stones;
	}

	/*
	 * boolean isFreeTurn() { boolean flag = false; if (this.freeTurn == 1) {
	 * this.freeTurn = 0; flag = true; System.out.println("Free turn !!"); } return
	 * flag; }
	 *
	 * void setFreeTurn() { this.freeTurn = 1; }//
	 */

	@Override
	public String toString() {
		return super.toString();
	}
}
