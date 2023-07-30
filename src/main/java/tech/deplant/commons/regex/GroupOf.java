package tech.deplant.commons.regex;

public record GroupOf(RegExpBuilder expr) implements RegExpBuilder {
	@Override
	public String build() {
		return String.format("(%s)", expr().build());
	}
}
