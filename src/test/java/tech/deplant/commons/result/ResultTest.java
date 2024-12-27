package tech.deplant.commons.result;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Bt;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class ResultTest {

	static Result<String> OK_RESULT;
	static Result<Path> NULL_RESULT;
	static Result<File> ERR_RESULT;

	@BeforeAll
	static void of_ok() {
		OK_RESULT = Result.of(() -> "test");
		NULL_RESULT = Result.of(() -> Path.of("/no_such_directory"));
		ERR_RESULT = Result.of(() -> new File("/no_such_directory"));
	}

	@Test
	void ofOptional() {
	}

	@Test
	void testOfOptional() {
	}

	@Test
	void isOk() {
		assertTrue(OK_RESULT.isOk());
		assertTrue(NULL_RESULT.isOk());
		assertFalse(ERR_RESULT.isOk());
	}

	@Test
	void isErr() {
		assertFalse(OK_RESULT.isErr());
		assertFalse(NULL_RESULT.isErr());
		assertTrue(ERR_RESULT.isErr());
	}

	@Test
	void orElse() {
	}

	@Test
	void orElseLazy() {
	}

	@Test
	void orElseThrow() {
	}

	@Test
	void testOrElseThrow() {
	}

	@Test
	void testOrElseThrow1() {
	}

	@Test
	void testOrElseThrow2() {
	}
}