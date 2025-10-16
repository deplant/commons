package tech.deplant.commons;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Fl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class FlTest {

	@Test
	public void can_create_file_even_if_dir_doesnt_exist() throws IOException {
		Fl.write(Paths.get("create_test/create_test_2/something.json"), "{\"yes\":\"go\"}");
		assertTrue(Files.exists(Paths.get("create_test/create_test_2/something.json")));
		Files.delete(Paths.get("create_test/create_test_2/something.json"));
		Files.delete(Paths.get("create_test/create_test_2"));
		Files.delete(Paths.get("create_test"));
	}

}
