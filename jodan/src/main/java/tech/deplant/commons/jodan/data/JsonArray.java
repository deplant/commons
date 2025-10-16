package tech.deplant.commons.jodan.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record JsonArray(JsonElement[] elements) implements JsonElement {
	public static final char START = '[';
	public static final char END = ']';

	public static JsonArray parse(JsonIterator it) {
		List<JsonElement> elements = new ArrayList<>();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			if (JsonArray.END == c) {
				return new JsonArray(elements.toArray(JsonElement[]::new));
			} else if (',' == c) {
				continue;
			} else {
				var elem = JsonElement.parse(it);
				if (Objects.nonNull(elem)) {
					elements.add(elem);
				} else {
					return new JsonArray(elements.toArray(JsonElement[]::new));
				}
			}
		}
		throw new IllegalArgumentException("JSON array end token not found!");
	}
}
