package tech.deplant.commons.regex;

import java.util.Set;
import java.util.regex.Pattern;

public sealed interface RegExpBuilder permits AnyOf, GroupOf, NoneOf, Occurences, Special, Symbol, Then, Word {

	Set<String> RESERVED_SYMBOLS = Set.of("\\", "^", "$", "*", "+",
	                                      "?", ".", "(", ")", "[", "]",
	                                      "{", "}", "|");


	String build();

	default Pattern toPattern() {
		return Pattern.compile(build());
	}

}
