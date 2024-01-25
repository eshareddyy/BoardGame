package mancala;

import java.io.Serializable;

public class KalahRules extends GameRules implements Serializable {

	private static final String serialVersionUID = "123456789L";

	private MancalaDataStructure mData;
	private int endPit = 0;
	private Boolean isFreeTurn;
	private int stoneAdded = 0;

	/**
     * Constructor for KalahRules class.
     * Initializes the KalahRules with the data structure required for the game.
     */
	public KalahRules() {
		super();
		mData = getDataStructure();
	}

	/**
     * Captures stones from the opponent's pit based on the stopping point in the Kalah game.
     *
     * @param stoppingPoint The stopping point that determines the opponent's pit to capture stones from.
     * @return The total number of stones captured.
     */
	@Override
	public int captureStones(final int stoppingPoint) {
		int capturePoint;
		int totalStones;

		capturePoint = 13 - stoppingPoint;

		totalStones = mData.removeStones(capturePoint) + mData.removeStones(stoppingPoint);
		
		return totalStones;
	}

	 /**
     * Moves stones in the Kalah game based on the selected starting pit and player number.
     *
     * @param startPit The starting pit from which stones are to be moved.
     * @param playerNum The player number making the move (1 or 2).
     * @return The current stone count in the player's store after the move.
     * @throws InvalidMoveException If the selected starting pit is invalid.
     */
	@Override
	public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {

		if (startPit < 1 || startPit > 12) {
			throw new InvalidMoveException();
		} else {
			isFreeTurn = false;
			//call method to set the current player
			setPlayer(playerNum);
			//call the distribute stones method
			distributeStones(startPit);
			//call helper method to check if stones have been stones in capture
			checkForSteal(playerNum);
		}

		return mData.getStoreCount(playerNum);
	}

	/**
     * Distributes stones from the selected pit according to the Kalah game rules.
     *
     * @param startPit The starting pit from which stones are to be distributed.
     * @return The number of stones moved from the starting pit.
     */

	@Override
	public int distributeStones(final int startPit) {
		final int stonesToMove = mData.removeStones(startPit);

		int playerNum = getCurrentPlayer();
		int pitPos = startPit;
		int count = 0;

		while (count != stonesToMove) {
			count++;
			pitPos++;

			if (pitPos == 7 && playerNum == 1 && stoneAdded == 0) {
				stoneAdded = 1;
				pitPos--;

				mData.addToStore(1, 1);
			} else if (pitPos == 13 && playerNum == 2 && stoneAdded == 0) {
				stoneAdded = 1;

				mData.addToStore(2, 1);
			} else {
				if (pitPos >= 13) {
					pitPos = 1;
				}

				mData.addStones(pitPos, 1);

				stoneAdded = 0;
			}

			endPit = pitPos;
		}
		return stonesToMove;
	}

    private void checkForSteal(int playerNum) {
		if (stoneAdded != 1) {
			if (endPit == 13) {
				endPit = 1;
			}
			if (mData.getNumStones(endPit) == 1) {
				handleSteal(playerNum);
			}
		} else {
			isFreeTurn = true;
		}
	}

	private void handleSteal(int playerNum) {
		if ((endPit > 6 && playerNum == 2) || (endPit <= 6 && playerNum == 1)) {
			mData.addToStore(playerNum, captureStones(endPit));
		}
	}


	/**
     * Gets whether the current player gets a free turn in the Kalah game.
     *
     * @return True if the current player gets a free turn, false otherwise.
     */
	public Boolean getFreeTurn() {
		return isFreeTurn;
	}
}
