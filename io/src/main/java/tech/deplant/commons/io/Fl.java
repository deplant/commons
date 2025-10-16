package tech.deplant.commons.io;

import module java.base;
import tech.deplant.commons.result.Result;

public class Fl {

	public static Result<Path> resourceStringToPath(String resourcePath) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return Result.of(() -> Objects.requireNonNull(classLoader.getResource(resourcePath)))
		             .mapErr("No such resource path: " + resourcePath)
		             .mapResult(url -> Result.of(() -> Paths.get(url.toURI())));
	}

	private static void recursiveDirCreation(Path path) throws IOException {
		Path parentPath = path;
		while (!Objects.isNull(parentPath)) {
			if (!Files.exists(parentPath)) {
				Files.createDirectories(parentPath);
			}
			parentPath = parentPath.getParent();
		}
	}

	public static void write(Path path, byte[] bytes) throws IOException {
		Objects.requireNonNull(path);
		recursiveDirCreation(path.getParent());
		Files.write(path, bytes);
	}

	public static void write(Path path, String str) throws IOException {
		Objects.requireNonNull(path);
		Objects.requireNonNull(str);
		recursiveDirCreation(path.getParent());
		Files.writeString(path, str, StandardCharsets.UTF_8);
	}

	public static Result<String> readString(Path filePath) {
		return Result.of(() -> Files.readString(filePath))
		             .logErrTrace(e -> "Failed reading file: " + filePath + " " + e.getMessage());
	}

	public static Result<Stream<String>> readAsStringStream(Path filePath, String delimiter) {
		return Result.of(() -> new Scanner(filePath.toFile())).map(input -> {
			input.useDelimiter(delimiter);
			return input.tokens();
		});
	}

	static Result<Stream<Result<String>>> readStringAllFilesFromResource(String resourcePath) {
		return resourceStringToPath(resourcePath).mapResult(Fl::readStringAllFiles);
	}

	/**
	 * Returns a Stream of file contents (as Strings) for all regular files in the given folder.
	 *
	 * @param folder the path to the folder
	 * @return where each string is the content of one file
	 */
	public static Result<Stream<Result<String>>> readStringAllFiles(Path folder) {
		return Result.of(() -> Files.list(folder))
		             .logErrTrace(e -> "Failed reading files from folder: " + folder + " " + e.getMessage())
		             .map(path -> path.filter(Files::isRegularFile))
		             .map(dirs -> dirs.map(Fl::readString));

	}

	/**
	 * Returns a Stream of file contents (as Strings) for all regular files in the given folder.
	 *
	 * @param folder the path to the folder
	 * @return where each string is the content of one file
	 */
	public static Result<Stream<Result<Stream<String>>>> readStringAllFilesStreamed(Path folder, String delimiter) {
		return Result.of(() -> Files.list(folder))
		             .logErrTrace(e -> "Failed reading files from folder: " + folder + " " + e.getMessage())
		             .map(path -> path.filter(Files::isRegularFile))
		             .map(dirs -> dirs.map(file -> readAsStringStream(file, delimiter)));

	}

	public static Result<Stream<String>> getFolderNames(Path folder) {
		return Result.of(() -> Files.list(folder))
		             .logErrTrace(e -> "Failed reading files from folder: " + folder + " " + e.getMessage())
		             .map(path -> path.filter(Files::isDirectory))
		             .map(dirs -> dirs.map(path -> path.getFileName().toString()));
	}

	public static Result<Stream<String>> getFolderNamesFromResource(String resourcePath) {
		return resourceStringToPath(resourcePath).mapResult(Fl::getFolderNames);
	}

}
