package tech.deplant.commons.test.unit.json;

public record JsonBoolean(boolean value) implements JsonValue {
	@Override
	public String stringify() {
		return String.valueOf(value());
	}
}
