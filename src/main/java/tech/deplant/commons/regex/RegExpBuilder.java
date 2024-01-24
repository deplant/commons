package tech.deplant.commons.regex;

import tech.deplant.commons.Strings;

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

	default String substr(int startIndex, String source, int... groups) {
		var newSource = Strings.substr(source, startIndex);
		return substr(newSource, groups);
	}

	default String substr(int startIndex, int endIndex, String source, int... groups) {
		var newSource = Strings.substr(source, startIndex, endIndex);
		return substr(newSource, groups);
	}

	default String substr(String source, int... groups) {
		var builder = new StringBuilder();

		var matcher = toPattern().matcher(source);
		if (!matcher.find()) {
			return "";
		}
		if (groups.length == 0) {
			return matcher.group();
		}
		for (int gr : groups) {
			builder.append(matcher.group(gr));
		}
		return builder.toString();
	}

}
