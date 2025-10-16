package tech.deplant.commons.jodan.data;

public sealed interface JsonElement permits JsonArray, JsonObject, JsonValue {

	static JsonElement parse(JsonIterator it) {
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			if (JsonObject.START == c) {
				it.next();
				return JsonObject.parse(it);
			} else if (JsonArray.START == c) {
				it.next();
				return JsonArray.parse(it);
			} else if (JsonObject.END == c) {
				return null;
			} else if (JsonArray.END == c) {
				it.next();
				return null;
			} else if ('t' == c || 'f' == c) {
				return JsonBoolean.parse(it);
			} else if ('n' == c) {
				return JsonNull.parse(it);
			} else if ('"' == c) {
				it.next();
				return JsonString.parse(it);
			} else if ('0' == c || '1' == c || '2' == c || '3' == c || '4' == c || '5' == c || '6' == c || '7' == c ||
			           '8' == c || '9' == c) {
				return JsonNumber.parse(it);
			}
		}
		return null;
	}

}
