package tech.deplant.commons.jodan.data;

public record JsonNull() implements JsonValue {

	public static JsonNull parse(JsonIterator it) {
		StringBuilder b = new StringBuilder();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			b.append(c);
			if (b.length() > 4) {
				break;
			}
			if (b.length() == 4 && "null".equalsIgnoreCase(b.toString())) {
				return new JsonNull();
			}
		}
		throw new IllegalArgumentException("No such token: %s".formatted(b));
	}

}
