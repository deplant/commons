package tech.deplant.commons.regex;

public record Special(String sym) implements RegExpBuilder {
	public static Special LATIN_LOWER = new Special("a-z");
	public static Special LATIN_UPPER = new Special("A-Z");
	public static Special DIGIT = new Special("\\d");

	public static Special NOT_DIGIT = new Special("\\D");
	public static Special ALPHANUM = new Special("\\w");

	public static Special NOT_ALPHANUM = new Special("\\W");

	public static Special WHITESPACE = new Special("\\s");

	public static Special NOT_WHITESPACE = new Special("\\S");

	public static Special NUL = new Special("\\0");
	public static Special ANY = new Special(".");
	public static Special PLUS = new Special("+");

	@Override
	public String build() {
		return sym();
	}
}
