package tech.deplant.commons.jodan.data;

import java.util.ArrayList;
import java.util.List;

public record JsonString(String value) implements JsonValue {

	public static JsonString parse(JsonIterator it) {
		StringBuilder b = new StringBuilder();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
            if ('"' == c) {
				return new JsonString(b.toString());
			} else {
	            b.append(c);
			}
		}
		throw new IllegalArgumentException("JSON String end token not found!");
	}
}
