package tech.deplant.commons.regex;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class RegExpressionTests {

	@Test
	public void test_markdown_links_search() {
		var expr = new Then(new Symbol('['),
		                    new NoneOf(new Word("[]()")),
		                    Special.PLUS,
		                    new Symbol(']'),
		                    new Symbol('('),
		                    new NoneOf(new Word("[]()")),
		                    Special.PLUS,
		                    new Symbol(')'));
		System.out.println(expr.build());
	}

	@Test
	public void test_alpha_digit_search() {
		var expr = new Then(new GroupOf(new Occurences(new AnyOf(new Word("a-zA-Z")), 1)),
		                    new GroupOf(new Occurences(Special.DIGIT, 1, 3)));
		System.out.println(expr.build());
		assertEquals(expr.build(), "([a-zA-Z]+)(\\d{1,3})");
	}

	@Test
	public void test_alpha_digit_search_with_array_brackets() {
		var expr = new Then(new GroupOf(new Then(new Occurences(new AnyOf(new Word("a-zA-Z")), 1),
		                                         new Occurences(Special.DIGIT, 0, 3))),
		                    new GroupOf(new Then(new Symbol('['), new Symbol(']'))));
		System.out.println(expr.build());
		assertEquals(expr.build(), "([a-zA-Z]+\\d{0,3})(\\[\\])");
	}

}
