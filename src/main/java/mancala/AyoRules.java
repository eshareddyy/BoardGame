package mancala;

import java.io.Serializable;

public class AyoRules extends GameRules implements Serializable {
    
	private static final String serialVersionUID = "123456789L";

	private final MancalaDataStructure mData;
	private static final int PLAYER1_STORE_PIT = 7;
	private static final int PLAYER2_STORE_PIT = 13;
	private static final int ONESTONE = 1;
	private int endPit = 0;
	private int skipPit = 0;
	private int stoneAdded = 0;

	public AyoRules() {
		super();
		mData = getDataStructure();
	}

	//method to distrubte stones from selected pit
	/**
     * Distributes stones from the selected pit according to the Ayo game rules.
     *
     * @param startPit The starting pit from which stones are to be distributed.
     * @return The number of stones moved from the starting pit.
     */
	@Override
	public int distributeStones(final int startPit) {
		int ctr = 0;
		int index = startPit;
		final int playerNum = getCurrentPlayer();

		final int stonesToMove = mData.removeStones(startPit);

		while (ctr != stonesToMove) {
			ctr += 1;
			index += 1;

			if (index == skipPit) {
				index += 1;
			}
			// Check conditions for adding to the store and update state accordingly
			if (index == PLAYER1_STORE_PIT && playerNum == 1 && stoneAdded == 0
					|| index == PLAYER2_STORE_PIT && playerNum == 2 && stoneAdded == 0) {

				// Add stones to the store and update state
				if (index == PLAYER1_STORE_PIT) {
					stoneAdded = 1;
					index -= 1;
					mData.addToStore(1, 1);
				} else if (index == PLAYER2_STORE_PIT) {
					stoneAdded = 1;
					mData.addToStore(2, 1);
				}
			} else {
				// Handle a normal move, adjusting the index and state
				if (index >= PLAYER2_STORE_PIT) {
					index = 1;
				}
				mData.addStones(index, 1);

				stoneAdded = 0;
			}
		}

		endPit = index;

		return stonesToMove;
	}

	/**
     * Captures stones from the opponent's pit based on the stopping point.
     *
     * @param stoppingPoint The stopping point that determines the opponent's pit to capture stones from.
     * @return The number of stones captured from the opponent's pit.
     */
	@Override
	public int captureStones(final int stoppingPoint) {
		int oppPit;
		// calculate the opponents pit to capture
		oppPit = 13 - stoppingPoint;

		return mData.removeStones(oppPit);
	}

	private int validateStartPit(final int startPit) throws InvalidMoveException {
		if (startPit < 1 || startPit > 12) {
			throw new InvalidMoveException();
		}
		return startPit;
	}

	/**
     * Moves stones in the Ayo game based on the selected starting pit and player number.
     *
     * @param starterPit The starting pit from which stones are to be moved.
     * @param playerNum  The player number making the move (1 or 2).
     * @return The current stone count in the player's store after the move.
     * @throws InvalidMoveException If the selected starting pit is invalid.
     */
	@Override
	public int moveStones(final int starterPit, final int playerNum) throws InvalidMoveException {
		final int startPit = validateStartPit(starterPit);
		setPlayer(playerNum);
		distributeStonesLoop(startPit);
		handleSteal(playerNum);

		return mData.getStoreCount(playerNum);
	}

	private void handleSteal(final int playerNum) {

		// No need to proceed if only one stone is added
		if (stoneAdded == ONESTONE) {
			return;
		}

		if (endPit >= PLAYER2_STORE_PIT) {
			endPit = 1;
		}

		// No stealing if the end pit does not have one stone
		if (mData.getNumStones(endPit) != ONESTONE) {
			return;
		}

		if (endPit > 6 && playerNum == 2 || endPit <= 6 && playerNum == 1) {
			mData.addToStore(playerNum, captureStones(endPit));
		}
	}

	private void distributeStonesLoop(int startPit) {
		boolean endLoop = false;
		skipPit = startPit;

		while (!endLoop) {
			distributeStones(startPit);

			if (stoneAdded == ONESTONE) {
				return;
			}

			if (endPit >= PLAYER2_STORE_PIT) {
				endPit = 1;
			}

			if (mData.getNumStones(endPit) == ONESTONE) {
				endLoop = true;
			} else {
				startPit = endPit;
			}
		}
	}

}