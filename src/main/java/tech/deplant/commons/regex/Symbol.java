package tech.deplant.commons.regex;

public record Symbol(char symbol) implements RegExpBuilder {
	@Override
	public String build() {
		String str = String.valueOf(symbol());
		if (RESERVED_SYMBOLS.contains(str)) {
			str = String.format("\\%s", str);
		}
		return str;
	}
}
