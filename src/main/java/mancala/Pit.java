package mancala;

import java.io.Serializable;

public class Pit implements Serializable, Countable {

	private int stoneCount = 0;
	private static final String serialVersionUID = "123456789L";

	public Pit() {
		// Initialize a new pit with 0 stones
		this.stoneCount = 4;
	}

	  /**
     * Gets the stone count in the pit.
     *
     * @return The stone count in the pit.
     */
	@Override
	public int getStoneCount() {
		return this.stoneCount;
	}

	/**
     * Adds a stone to the pit.
     */
	@Override
	public void addStone() {
		// Add a stone to the pit
		this.stoneCount++;
	}

	   /**
     * Adds a specified number of stones to the pit.
     *
     * @param numToAdd The number of stones to add.
     */
	@Override
	public void addStones(int numToAdd) {
		// Add multiple stones to the pit
		this.stoneCount += numToAdd;
	}

	 /**
     * Removes and returns all the stones from the pit.
     *
     * @return The number of stones removed from the pit.
     */
	@Override
	public int removeStones() {
		// Remove and return all the stones from the pit
		int removedStones = this.stoneCount;
		this.stoneCount = 0;
		return removedStones;
	}

	/**
     * Returns a string representation of the stone count in the pit.
     *
     * @return A string representation of the stone count.
     */
	@Override
	public String toString() {
		return Integer.toString(stoneCount);
	}
}
