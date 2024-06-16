package tech.deplant.commons.test.unit.json;

public record JsonNumber(Number value) implements JsonValue {
	@Override
	public String stringify() {
		return String.valueOf(value());
	}
}
