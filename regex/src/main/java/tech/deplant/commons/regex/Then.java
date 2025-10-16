package tech.deplant.commons.regex;

import java.util.Arrays;
import java.util.stream.Collectors;

public record Then(RegExpression... expr) implements RegExpression {
	@Override
	public String build() {
		return Arrays.stream(expr()).map(exp -> exp.build()).collect(Collectors.joining());
	}
}
