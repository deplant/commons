package tech.deplant.commons.jodan;

public record JsonString(String value) implements JsonValue {
	@Override
	public String stringify() {
		return "\"" + value + "\"";
	}
}
