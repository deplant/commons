package tech.deplant.commons.regex;

public record GroupOf(RegExpression expr) implements RegExpression {
	@Override
	public String build() {
		return String.format("(%s)", expr().build());
	}
}
