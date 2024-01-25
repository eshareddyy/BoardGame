package mancala;

public class GameNotOverException extends Exception {
	private static final String serialVersionUID = "123456789L";

	public GameNotOverException() {
		super("The game is not over yet.");
	}
}