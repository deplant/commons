package tech.deplant.commons;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Fl {

	private static void recursiveDirCreation(Path path) throws IOException {
		Path parentPath = path;
		while(Obj.isNotNull(parentPath)) {
			if (!Files.exists(parentPath)) {
				Files.createDirectories(parentPath);
			}
			parentPath = parentPath.getParent();
		}
	}

	public static void write(Path path, byte[] bytes) throws IOException {
		Obj.notNull(path);
		recursiveDirCreation(path.getParent());
		Files.write(path, bytes);
	}

	public static void write(Path path, String str) throws IOException {
		Obj.notNull(path);
		Obj.notNull(str);
		recursiveDirCreation(path.getParent());
		Files.writeString(path, str, StandardCharsets.UTF_8);
	}

}
