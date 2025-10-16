package tech.deplant.commons.regex;

public record Occurences(RegExpression expr, Integer min, Integer max) implements RegExpression {
	public Occurences(RegExpression expr, Integer min) {
		this(expr, min, null);
	}

	@Override
	public String build() {
		if (max() == null && Integer.valueOf(1).equals(min())) {
			return expr().build() + "+";
		} else if (this.max == null && Integer.valueOf(0).equals(min())) {
			return expr().build() + "*";
		} else {
			return String.format("%s{%d,%d}", expr().build(), min(), max());
		}
	}
}
