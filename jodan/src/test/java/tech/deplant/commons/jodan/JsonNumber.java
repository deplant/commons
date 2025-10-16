package tech.deplant.commons.jodan;

public record JsonNumber(Number value) implements JsonValue {
	@Override
	public String stringify() {
		return String.valueOf(value());
	}
}
