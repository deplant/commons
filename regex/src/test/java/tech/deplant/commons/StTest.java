package tech.deplant.commons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tech.deplant.commons.St;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class StTest {

	@Test
	public void null_string_is_detected_as_empty() {
		Assertions.assertTrue(St.isEmpty(null));
		Assertions.assertFalse(St.isNotEmpty(null));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "        "})
	public void empty_string_variants_are_detected_as_empty(String str) {
		Assertions.assertTrue(St.isEmpty(str));
		Assertions.assertFalse(St.isNotEmpty(str));
	}

	@ParameterizedTest
	@ValueSource(strings = {"o","NotNull", "    Surrounded    "})
	public void nonempty_string_variants_are_detected_as_nonempty(String str) {
		Assertions.assertTrue(St.isNotEmpty(str));
		Assertions.assertFalse(St.isEmpty(str));
	}

	@ParameterizedTest
	@ValueSource(strings = {"-0e34a29F", "0e34a29F", "00", "0x00", "01", "abefcd", "0x0e34a29Fb3", "-0x0e34a29Fb3"})
	public void hex_strings_are_detected_as_hexes(String str) {
		Assertions.assertTrue(St.isHexadecimal(str));
	}

	@ParameterizedTest
	@ValueSource(strings = {"-0x", "0x", "-x", "xx", "0", "01zz", "abefc", "0x0e34a29Fb", "-0x-0e34a29Fb3"})
	public void incorrect_hex_strings_are_not_detected_as_hexes(String str) {
		Assertions.assertFalse(St.isHexadecimal(str));
	}

	@Test
	public void toCamelCase_equals_predicted() {
		Assertions.assertEquals("firstWord", St.toCamelCase("first_word"));
		Assertions.assertEquals("firstWord", St.toCamelCase("First.word"));
		Assertions.assertEquals("firstWord", St.toCamelCase("first  word"));
	}

	@Test
	public void toPascalCase_equals_predicted() {
		Assertions.assertEquals("FirstWord", St.toPascalCase("first_word"));
		Assertions.assertEquals("FirstWord", St.toPascalCase("First.word"));
		Assertions.assertEquals("FirstWord", St.toPascalCase("first  word"));
	}

	@Test
	public void toKebabCase_equals_predicted() {
		Assertions.assertEquals("first-word", St.toKebabCase("first_word"));
		Assertions.assertEquals("first-word", St.toKebabCase("First.word"));
		Assertions.assertEquals("first-word", St.toKebabCase("first  word"));
	}

	@Test
	public void toSnakeCase_equals_predicted() {
		Assertions.assertEquals("first_word", St.toSnakeCase("first_word"));
		Assertions.assertEquals("first_word", St.toSnakeCase("First.word"));
		Assertions.assertEquals("first_word", St.toSnakeCase("first  word"));
	}

	@Test
	public void toLowerDotCase_equals_predicted() {
		Assertions.assertEquals("first.word", St.toLowerDotCase("first_word"));
		Assertions.assertEquals("first.word", St.toLowerDotCase("First.word"));
		Assertions.assertEquals("first.word", St.toLowerDotCase("first  word"));
	}


	@ParameterizedTest
	@ValueSource(strings = {"[a-z]*","b+", "10"})
	public void string_matches_patterns(String pattern) {
		Assertions.assertTrue(St.matchesPattern("ab10yum", pattern));
	}

	@ParameterizedTest
	@ValueSource(strings = {"b+", "10"})
	public void string_not_matches_patterns(String pattern) {
		Assertions.assertFalse(St.matchesPattern("A", pattern));
	}

	@ParameterizedTest
	@ValueSource(strings = {"ab    hello!","crab   world", "crab\n\n\t\tworld"})
	public void pattern_matches_strings(String str) {
		Assertions.assertTrue(St.matchesPattern(str, "ab\\s{1,3}[a-z]*"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"crabworld"})
	public void pattern_not_matches_strings(String str) {
		Assertions.assertFalse(St.matchesPattern(str, "ab\\s{1,3}[a-z]*"));
	}

	@Test
	public void check_start_with() {
		Assertions.assertFalse("1".startsWith("0x"));
	}

	@Test
	public void correct_substring_results() {
		String str1 = "MakeWorldBetterPlace";
		Assertions.assertEquals("Make", St.substr(str1, 0, 4, false));
		Assertions.assertEquals("Make", St.substr(str1, 16, true));
		Assertions.assertEquals("Place", St.substr(str1, 0, 5, true));
		Assertions.assertEquals("Place", St.substr(str1, 15));
		Assertions.assertEquals("", St.substr(str1, -1, -2, true));
		Assertions.assertEquals("", St.substr(str1, 5, 0, true));
		Assertions.assertEquals("s", St.substr("s", 0, 10, true));
	}

}
