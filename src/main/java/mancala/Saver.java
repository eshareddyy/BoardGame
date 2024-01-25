package mancala;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver {
	private static final String FOLDER_NAME = "assets";

	  /**
     * Saves a Serializable object to a file.
     *
     * @param toSave   The object to be saved.
     * @param filename The name of the file to save the object to.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
	public static void saveObject(Serializable toSave, String filename) throws IOException {

		Path currentDirectory = Paths.get(System.getProperty("user.dir"));
		Path assetsFolderPath = currentDirectory.resolve(FOLDER_NAME);

		if (!Files.exists(assetsFolderPath)) {
			Files.createDirectories(assetsFolderPath);
		}

		// System.out.println("ASSETS PATAH:" +
		// assetsFolderPath.resolve(filename).toString());

		try (FileOutputStream fileOutputStream = new FileOutputStream(assetsFolderPath+"/"+filename);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(toSave);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + filename);
			throw e;
		}
	}

	/**
     * Loads a Serializable object from a file.
     *
     * @param filename The name of the file to load the object from.
     * @return The loaded Serializable object.
     * @throws IOException            If an I/O error occurs while reading from the file.
     * @throws ClassNotFoundException If the class of the loaded object cannot be found.
     */
	public static Serializable loadObject(String filename) throws IOException, ClassNotFoundException {
		Path currentDirectory = Paths.get(System.getProperty("user.dir"));
		Path assetsFolderPath = currentDirectory.resolve(FOLDER_NAME);

		createFolderIfNotExists(assetsFolderPath);

		try (FileInputStream fileInputStream = new FileInputStream(assetsFolderPath.resolve(filename).toString());
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			return (Serializable) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading from file: " + filename);
			throw e;
		}
	}

	private static void createFolderIfNotExists(Path assetsFolderPath) throws IOException {
		if (!Files.exists(assetsFolderPath)) {
			Files.createDirectories(assetsFolderPath);
		}

	}

}
