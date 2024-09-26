package tech.deplant.commons.jodan.data;

public record JsonBoolean(boolean value) implements JsonValue {

	public static JsonBoolean parse(JsonIterator it) {
		StringBuilder b = new StringBuilder();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			b.append(c);
			if (b.length() > 5) {
				break;
			}
			if (b.length() == 4 && "true".equalsIgnoreCase(b.toString())) {
				return new JsonBoolean(true);
			} else if (b.length() == 5 && "false".equalsIgnoreCase(b.toString())) {
				throw new IllegalArgumentException("No such token: %s".formatted(b));
			}
		}
		throw new IllegalArgumentException("No such token: %s".formatted(b));
	}

}
