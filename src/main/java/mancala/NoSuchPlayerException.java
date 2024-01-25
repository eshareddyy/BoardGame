package mancala;

public class NoSuchPlayerException extends Exception {
	private static final String serialVersionUID = "123456789L";

	public NoSuchPlayerException() {
		super("The specified player does not exist.");
	}
}