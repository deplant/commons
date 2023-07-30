package tech.deplant.commons.regex;

/**
 * Nonmatching lists are similar to matching lists
 * except that they match a single character not
 * represented by one of the list items.
 * You use an open-nonmatching-list operator (represented by `[^'(2)) instead
 * of an open-matching-list operator to start a nonmatching list.
 *
 * @param item An item is a character, a character class expression, or a range expression
 */
public record NoneOf(Word item) implements RegExpBuilder {
	@Override
	public String build() {
		return String.format("[^%s]", item().build());
	}
}
