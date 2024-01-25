package mancala;

public class InvalidMoveException extends Exception {
	private static final String serialVersionUID = "123456789L";

	public InvalidMoveException() {
		super("Invalid move. Please choose a valid move.");
	}
}