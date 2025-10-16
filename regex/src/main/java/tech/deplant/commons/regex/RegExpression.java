package tech.deplant.commons.regex;

import java.util.Set;
import java.util.regex.Pattern;

public sealed interface RegExpression permits AnyOf, GroupOf, NoneOf, Occurences, Special, Symbol, Then, Word {

	Set<String> RESERVED_SYMBOLS = Set.of("\\", "^", "$", "*", "+", "?", ".", "(", ")", "[", "]", "{", "}", "|");

	String build();

	default Pattern toPattern() {
		return Pattern.compile(build());
	}

	/**
	 * Takes a string as a parameter and returns true if source string matches reg expression and false if not.
	 *
	 * @param occurence number of occurence of matching part that should exist (second occurence = 2)
	 * @param source    Text to check against reg expression
	 * @return boolean result if source matches reg expression
	 */
	default boolean matches(int occurence, final String source) {
		final var matcher = toPattern().matcher(source);

		if (occurence < 1) {
			throw new IllegalArgumentException("Occurence should be a positive integer.");
		}

		for (int i = 1; i <= occurence; i++) {
			if (!matcher.find()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Takes a string as a parameter and returns true if source string matches reg expression and false if not.
	 *
	 * @param source Text to check against reg expression
	 * @return boolean result if source matches reg expression
	 */
	default boolean matches(final String source) {
		return matches(1, source);
	}

	/**
	 * Takes a string as a parameter and returns only part that matches regexpression.
	 *
	 * @param occurence number of occurence of matching part (second occurence = 2)
	 * @param source    Text to retrieve substring
	 * @param groups    If reg expression contains groups (see {@link GroupOf}), you can specify group numbers to retrieve
	 * @return substring of the source that matches reg expression
	 */
	default String substr(int occurence, final String source, int... groups) {
		final var matcher = toPattern().matcher(source);

		if (occurence < 1) {
			throw new IllegalArgumentException("Occurence should be a positive integer.");
		}

		for (int i = 1; i <= occurence; i++) {
			if (!matcher.find()) {
				return "";
			}
		}

		if (groups.length == 0) {
			return matcher.group();
		} else {
			final var builder = new StringBuilder();
			for (int gr : groups) {
				builder.append(matcher.group(gr));
			}
			return builder.toString();
		}
	}

	/**
	 * Takes a string as a parameter and returns only part that matches regexpression.
	 *
	 * @param source Text to retrieve substring
	 * @param groups If reg expression contains groups, you can specify group number to retrieve
	 * @return subsctring of source that matches reg expression
	 */
	default String substr(String source, int... groups) {
		return substr(1, source, groups);
	}

}
