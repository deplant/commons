package tech.deplant.commons.jodan;

public record JsonNull() implements JsonValue {
	@Override
	public String stringify() {
		return "null";
	}
}
