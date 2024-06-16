package tech.deplant.commons.test.unit.json;

public record JsonNull() implements JsonValue {
	@Override
	public String stringify() {
		return "null";
	}
}
