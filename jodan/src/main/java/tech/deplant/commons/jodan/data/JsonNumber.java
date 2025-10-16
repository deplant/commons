package tech.deplant.commons.jodan.data;

import java.math.BigDecimal;

public record JsonNumber(BigDecimal value) implements JsonValue {
	public static JsonNumber parse(JsonIterator it) {
		StringBuilder b = new StringBuilder();
		for (char c = it.curr(); c != JsonIterator.DONE; c = it.next()) {
			if ('.' == c || '0' == c || '1' == c || '2' == c ||
			    '3' == c || '4' == c || '5' == c || '6' == c || '7' == c || '8' == c || '9' == c) {
				b.append(c);
			} else {
				return new JsonNumber(new BigDecimal(b.toString()));
			}
		}
		return new JsonNumber(new BigDecimal(b.toString()));
	}

}
