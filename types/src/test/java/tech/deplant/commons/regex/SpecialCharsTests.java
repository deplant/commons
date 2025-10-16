package tech.deplant.commons.regex;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.regex.Special;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class SpecialCharsTests {

	@Test
	public void digits_search_test() {
		var expr = Special.DIGIT;
		assertEquals("7", expr.substr("da dy7%uss8"));
		assertEquals("8", expr.substr(2,"da dy7%uss8"));
		assertEquals("", expr.substr(3,"da dy7%uss8"));
	}

	@Test
	public void not_digits_search_test() {
		var expr = Special.NOT_DIGIT;
		assertEquals("d", expr.substr("da dy7%uss8"));
		assertEquals("y", expr.substr(5,"da dy7%uss8"));
	}

	@Test
	public void alphanum_search_test() {
		var expr = Special.ALPHANUM;
		assertEquals("d", expr.substr("da dy7%uss8"));
		assertEquals("7", expr.substr(5,"da dy7%uss8"));
	}

	@Test
	public void not_alphanum_search_test() {
		var expr = Special.NOT_ALPHANUM;
		assertEquals(" ", expr.substr("da dy7%uss8"));
		assertEquals("%", expr.substr(2,"da dy7%uss8"));
		assertEquals("", expr.substr(3,"da dy7%uss8"));
	}

}
