package tech.deplant.commons.regex;

import java.util.Arrays;
import java.util.stream.Collectors;

public record Then(RegExpBuilder... expr) implements RegExpBuilder {
	@Override
	public String build() {
		return Arrays.stream(expr()).map(exp -> exp.build()).collect(Collectors.joining());
	}
}
