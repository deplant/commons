package tech.deplant.commons.regex;

/**
 * Word is some sequence of chars
 *
 * @param word
 */
public record Word(CharSequence word) implements RegExpBuilder {
	@Override
	public String build() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < word().length(); i++) {
			builder.append(new Symbol(word().charAt(i)).build());
		}
		return builder.toString();
	}
}
