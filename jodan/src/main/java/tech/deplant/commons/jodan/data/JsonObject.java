package tech.deplant.commons.jodan.data;

import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

public record JsonObject(Map<String, JsonElement> fields) implements JsonElement {
	public static final char START = '{';
	public static final char END = '}';

	public static JsonObject parse(JsonIterator it) {
		Map<String, JsonElement> fields = new HashMap<>();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			if ('"' == c) {
				it.next();
				var name = parseName(it);
				it.next();
				var field = parseField(it);
				fields.put(name, field);
			} else if (JsonObject.END == c) {
				return new JsonObject(fields);
			}
		}
		throw new IllegalArgumentException("JSON object end token not found!");
	}

	public static String parseName(JsonIterator it) {
		StringBuilder b = new StringBuilder();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			if ('"' == c) {
				return b.toString();
			} else {
				b.append(c);
			}
		}
		throw new IllegalArgumentException("Field name token can't be empty!");
	}

	public static JsonElement parseField(JsonIterator it) {
		StringBuilder b = new StringBuilder();
		boolean divFound = false;
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			if (':' == c) {
				divFound = true;
			} else if (divFound) {
				divFound = false;
				return JsonElement.parse(it);
			}
		}
		throw new IllegalArgumentException("Field name token can't be empty!");
	}
}
