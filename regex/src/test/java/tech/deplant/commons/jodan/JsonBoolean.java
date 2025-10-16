package tech.deplant.commons.jodan;

public record JsonBoolean(boolean value) implements JsonValue {
	@Override
	public String stringify() {
		return String.valueOf(value());
	}
}
