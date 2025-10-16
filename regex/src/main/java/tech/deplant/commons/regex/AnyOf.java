package tech.deplant.commons.regex;

/**
 * A matching list matches a single character represented by one
 * of the list items. You form a matching list by enclosing one or
 * more items within an open-matching-list operator (represented by `[')
 * and a close-list operator (represented by `]').
 *
 * @param item An item is a character, a character class expression, or a range expression
 */
public record AnyOf(Word item) implements RegExpression {
	@Override
	public String build() {
		return String.format("[%s]", item().build());
	}
}
