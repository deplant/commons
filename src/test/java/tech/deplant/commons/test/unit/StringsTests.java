package tech.deplant.commons.test.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Strings;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class StringsTests {

	@Test
	public void notEmpty_shows_correct_booleans() {
		String str1 = "NotNull";
		String str2 = "";
		String str3 = "        ";
		String str4 = null;
		Assertions.assertTrue(Strings.isNotEmpty(str1));
		Assertions.assertFalse(Strings.isNotEmpty(str2));
		Assertions.assertFalse(Strings.isNotEmpty(str3));
		Assertions.assertFalse(Strings.isNotEmpty(str4));
	}

	@Test
	public void correct_substring_results() {
		String str1 = "MakeWorldBetterPlace";
		Assertions.assertEquals("Make", Strings.substr(str1, 0, 4, false));
		Assertions.assertEquals("Make", Strings.substr(str1, 16,true));
		Assertions.assertEquals("Place", Strings.substr(str1, 0, 5, true));
		Assertions.assertEquals("Place", Strings.substr(str1, 15));
		Assertions.assertEquals("",Strings.substr(str1, -1, -2, true));
		Assertions.assertEquals("",Strings.substr(str1, 5, 0, true));
		Assertions.assertEquals("s", Strings.substr("s", 0, 10, true));
	}

}
