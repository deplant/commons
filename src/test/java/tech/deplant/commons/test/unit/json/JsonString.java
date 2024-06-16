package tech.deplant.commons.test.unit.json;

public record JsonString(String value) implements JsonValue {
	@Override
	public String stringify() {
		return "\"" + value + "\"";
	}
}
