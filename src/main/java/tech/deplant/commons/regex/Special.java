package tech.deplant.commons.regex;

public record Special(String sym) implements RegExpBuilder {
	public static Special LATIN_LOWER = new Special("a-z");
	public static Special LATIN_UPPER = new Special("A-Z");
	public static Special DIGIT = new Special("\\d");
	public static Special ANY = new Special(".");
	public static Special PLUS = new Special("+");

	@Override
	public String build() {
		return sym();
	}
}
